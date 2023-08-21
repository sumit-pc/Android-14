package me.globant.android14.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import me.globant.android14.nav.Destination
import me.globant.android14.screens.locationpermission.LocationPermissionScreen
import me.globant.android14.ui.composables.SetupM3Scaffold
import me.globant.android14.ui.theme.Android14Theme

class LocationPermissionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                } else -> {
                // No location access granted.
            }
            }
        }

        val permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED

        setContent {
            Android14Theme {
                SetupM3Scaffold(Destination.LocationPermission) { paddingValues ->
                    LocationPermissionScreen(paddingValues, locationPermissionRequest, permissionStatus)
                }
            }
        }
    }

    companion object {
        fun start(activity: ComponentActivity) {
            val intent = Intent(activity, LocationPermissionActivity::class.java)
            activity.startActivity(intent)
        }
    }

}