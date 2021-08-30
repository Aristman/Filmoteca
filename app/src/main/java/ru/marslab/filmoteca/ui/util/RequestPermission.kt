package ru.marslab.filmoteca.ui.util

import android.app.AlertDialog
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.marslab.filmoteca.R

class RequestPermission(
    private val fragment: Fragment,
    private val permissionDialogTitle: Int,
    private val permissionDialogMessage: Int
) {
    private var requestedPermission: String = ""
    private val _permission: MutableLiveData<PermissionAccessLevel> = MutableLiveData()
    val permission: LiveData<PermissionAccessLevel>
        get() = _permission

    private val permissionRequest =
        fragment.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                _permission.postValue(PermissionAccessLevel.Granted)
            } else {
                _permission.postValue(PermissionAccessLevel.Denied)
            }
        }

    fun getPermission(permission: String) {
        try {
            requestedPermission = permission
            when {
                fragment.shouldShowRequestPermissionRationale(permission) -> {
                    requestPermission()
                }
                isPermissionGranted() -> {
                    _permission.postValue(PermissionAccessLevel.Granted)
                }
                else -> {
                    showRequestPermissionDialog()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun requestPermission() {
        permissionRequest.launch(requestedPermission)
    }

    private fun showRequestPermissionDialog() {
        AlertDialog.Builder(fragment.requireContext())
            .setTitle(permissionDialogTitle)
            .setMessage(permissionDialogMessage)
            .setPositiveButton(R.string.ok) { _, _ ->
                requestPermission()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                _permission.postValue(PermissionAccessLevel.Denied)
                dialog.dismiss()
            }
            .create()
            .show()
    }

    fun isPermissionGranted() = ContextCompat.checkSelfPermission(
        fragment.requireContext(),
        requestedPermission
    ) == PackageManager.PERMISSION_GRANTED
}

sealed class PermissionAccessLevel {
    object Granted : PermissionAccessLevel()
    object Denied : PermissionAccessLevel()
    object Error : PermissionAccessLevel()
}