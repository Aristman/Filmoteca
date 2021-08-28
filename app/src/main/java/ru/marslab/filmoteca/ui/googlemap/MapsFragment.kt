package ru.marslab.filmoteca.ui.googlemap

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.coroutineScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentMapsBinding
import ru.marslab.filmoteca.domain.repository.Constants
import ru.marslab.filmoteca.ui.util.showMessage
import java.io.IOException

private const val REQUEST_CODE = 12

class MapsFragment : Fragment() {
    private var _binding: FragmentMapsBinding? = null
    private val binding: FragmentMapsBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_not_init) }
    private var googleMap: GoogleMap? = null
    private var mapLocation: MutableLiveData<LatLng> = MutableLiveData()
    private var startLocation: MutableLiveData<LatLng?> = MutableLiveData(null)
    private var address: MutableLiveData<List<Address>> = MutableLiveData()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(binding.map.id) as SupportMapFragment?
        lifecycle.coroutineScope.launchWhenCreated {
            googleMap = mapFragment?.awaitMap()
            googleMap?.moveCamera(CameraUpdateFactory.zoomBy(10f))
        }
        checkPermission()
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        startLocation.observeForever { location ->
            location?.let { googleMap?.moveCamera(CameraUpdateFactory.newLatLng(it)) }
        }
    }

    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                getLocation()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.permission_location_dialog_title)
                    .setMessage(R.string.permission_location_dialog_message)
                    .setPositiveButton(R.string.ok) { _, _ ->
                        requestPermission()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            }
            else -> {
                requestPermission()
            }
        }
    }

    private fun requestPermission() {
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                getLocation()
            } else {
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.permission_location_dialog_title)
                    .setMessage(R.string.permission_location_dialog_message)
                    .setPositiveButton(R.string.ok) { _, _ ->
                        requestPermission()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            }
        }
            .launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    getLocation()
                } else {
                    requireView().showMessage(R.string.permission_location_dialog_message)
                }
            }
        }
    }

    private fun getLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val locationService =
                requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (locationService.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                val provider = locationService.getProvider(LocationManager.GPS_PROVIDER)
                provider?.let {
                    locationService.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        Constants.GPS_REFRESH_PERIOD,
                        Constants.GPS_DISTANCE,
                        onLocationListener
                    )
                }
            }
        }
    }

    private val onLocationListener = LocationListener { location ->
        val geocoder = Geocoder(requireContext())
        lifecycle.coroutineScope.launch(Dispatchers.IO) {
            try {
                address.postValue(
                    geocoder.getFromLocation(
                        location.latitude,
                        location.longitude,
                        1
                    )
                )
                val latLng = LatLng(location.latitude, location.longitude)
                lifecycle.coroutineScope.launch(Dispatchers.Main) {
                    googleMap?.let {
                        it.clear()
                        it.addMarker(
                            MarkerOptions()
                                .position(latLng)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        )
                    }
                }
                if (startLocation.value == null) {
                    startLocation.postValue(latLng)
                }
                mapLocation.postValue(latLng)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun initListeners() {
        binding.addressFindButton.setOnClickListener {
//            googleMap?.
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}