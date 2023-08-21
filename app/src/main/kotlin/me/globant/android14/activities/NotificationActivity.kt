package me.globant.android14.activities

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import me.globant.android14.nav.Destination
import me.globant.android14.screens.notification.DummyNotification
import me.globant.android14.ui.composables.SetupM3Scaffold
import me.globant.android14.ui.theme.Android14Theme

class NotificationActivity : AppCompatActivity() {

    private val notificationManager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sets up notification channel.
        createNotificationChannel()

        val notificationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.POST_NOTIFICATIONS, false) -> {
                    // Precise location access granted.
                }
                else -> {
                // No location access granted.
            }
            }
        }

        val permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED

        setContent {
            Android14Theme {
                SetupM3Scaffold(Destination.ShowNotification) { paddingValues ->
                    DummyNotification(paddingValues, notificationPermissionRequest, permissionStatus, this)
                }
            }
        }
    }

    companion object {
        const val CHANNEL_ID = "dummy_channel"

        fun start(activity: ComponentActivity) {
            val intent = Intent(activity, NotificationActivity::class.java)
            activity.startActivity(intent)
        }
    }

    /**
     * Creates Notification Channel (required for API level >= 26) before sending any notification.
     */
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Important Notification Channel",
            NotificationManager.IMPORTANCE_HIGH,
        ).apply {
            description = "This notification contains important announcement, etc."
        }
        notificationManager.createNotificationChannel(channel)
    }

}