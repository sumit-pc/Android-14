package me.globant.android14.screens.notification

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import me.globant.android14.R
import me.globant.android14.activities.NotificationActivity.Companion.CHANNEL_ID

@Composable
fun DummyNotification(
    paddingValues: PaddingValues,
    locationPermissionRequest: ActivityResultLauncher<Array<String>>,
    permissionStatus: Boolean,
    activity: ComponentActivity,
) {

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                start = 16.dp,
                end = 16.dp,
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {

        item {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                text = "Notification Permission status : $permissionStatus",
                style = MaterialTheme.typography.titleLarge
            )
            
            Button(onClick = {
                locationPermissionRequest.launch(arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS))
            }) {
                Text(text = "Ask Notification Permission")
            }
            
            Button(onClick = {
                context.revokeSelfPermissionOnKill(Manifest.permission.POST_NOTIFICATIONS)
            }) {
                Text(text = "Remove Self permission")
            }
            
            Button(onClick = {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS,
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    showDummyNotification(context)
                }
            }) {
                Text(text = "Send Dummy Notification")
            }
        }
    }

}


/**
 * Shows a notification to user.
 *
 * The notification won't appear if the user doesn't grant notification permission first.
 */
private fun showDummyNotification(context: Context) {
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Congratulations! 🎉🎉🎉")
        .setContentText("You have post a notification to Android 13!!!")
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    with(NotificationManagerCompat.from(context)) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) { return }
        notify(1, builder.build())
    }
}