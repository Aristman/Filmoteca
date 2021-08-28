package ru.marslab.filmoteca.ui.googlemap

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import ru.marslab.filmoteca.ui.util.PermissionAccessLevel
import ru.marslab.filmoteca.ui.util.RequestPermission
import ru.marslab.filmoteca.ui.util.showMessage
import java.io.IOException

class MapsFragment : Fragment() {

    private val requestLocationPermission: RequestPermission by lazy {
        RequestPermission(
            this,
            R.string.permission_location_dialog_title,
            R.string.permission_location_dialog_message
        )
    }

    private var _binding: FragmentMapsBinding? = null
    private val binding: FragmentMapsBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_not_init) }
    private var googleMap: GoogleMap? = null
    private val geocoder: Geocoder by lazy { Geocoder(requireContext()) }
    private var mapLocation: MutableLiveData<LatLng?> = MutableLiveData(null)
    private var isStartLocation: Boolean = true
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
            myLocation()
            googleMap?.moveCamera(CameraUpdateFactory.zoomBy(10f))
        }
        requestLocationPermission.getPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        mapLocation.observeForever { location ->
            if (isStartLocation) {
                location?.let {
                    googleMap?.moveCamera(CameraUpdateFactory.newLatLng(it))
                    isStartLocation = false
                }
            }
        }
        requestLocationPermission.permission.observeForever { permission ->
            when (permission) {
                PermissionAccessLevel.Granted -> {
                    getUserLocation()
                }
                PermissionAccessLevel.Denied -> {
                    requireView().showMessage(R.string.permission_location_dialog_message)
                }
                PermissionAccessLevel.Error -> {
                }
            }
        }
    }

    private fun initListeners() {
        binding.addressFindButton.setOnClickListener {
            val findFromLocationName =
                geocoder.getFromLocationName(binding.addressFindField.text.toString(), 3)
            if (findFromLocationName.size > 0) {
                googleMap?.moveCamera(CameraUpdateFactory.newLatLng(findFromLocationName.map {
                    LatLng(
                        it.latitude,
                        it.longitude
                    )
                }.first()))
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun myLocation() {
        googleMap?.let { map ->
            if (requestLocationPermission.isPermissionGranted()) {
                map.isMyLocationEnabled = true
                map.uiSettings.isMyLocationButtonEnabled = true
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        if (requestLocationPermission.isPermissionGranted()) {
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
            } else {
                requireView().showMessage(R.string.not_enebled_gps)
            }
        } else {
            requireView().showMessage(R.string.permission_location_dialog_message)
        }
    }

    private val onLocationListener = LocationListener { location ->
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
                mapLocation.postValue(latLng)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}