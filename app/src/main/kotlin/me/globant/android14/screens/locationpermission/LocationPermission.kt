package me.globant.android14.screens.locationpermission

import android.Manifest
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
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

@Composable
fun LocationPermissionScreen(
    paddingValues: PaddingValues,
    locationPermissionRequest: ActivityResultLauncher<Array<String>>,
    permissionStatus: Boolean
) {


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
                text = "Permission status : $permissionStatus",
                style = MaterialTheme.typography.titleLarge
            )
            
            Button(onClick = {
                locationPermissionRequest.launch(arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION))
            }) {
                Text(text = "Ask Permission")
            }
            val context = LocalContext.current
            Button(onClick = {
                context.revokeSelfPermissionOnKill(Manifest.permission.ACCESS_FINE_LOCATION)
                context.revokeSelfPermissionOnKill(Manifest.permission.ACCESS_COARSE_LOCATION)
            }) {
                Text(text = "Remove Self permission")
            }
        }
    }
}
