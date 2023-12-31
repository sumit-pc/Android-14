package me.globant.android14.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import me.globant.android14.nav.Destination
import me.globant.android14.screens.screenshot.ScreenshotScreen
import me.globant.android14.ui.composables.SetupM3Scaffold
import me.globant.android14.ui.theme.Android14Theme
import me.globant.android14.viewmodels.ScreenshotActivityViewModel

class ScreenshotActivity : ComponentActivity(), Activity.ScreenCaptureCallback {

    private val viewModel: ScreenshotActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android14Theme {
                SetupM3Scaffold(Destination.ScreenshotDetection) { paddingValues ->
                    ScreenshotScreen(
                        paddingValues,
                        viewModel
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        registerScreenCaptureCallback(mainExecutor, this)
        //window.setFlags(LayoutParams.FLAG_SECURE, LayoutParams.FLAG_SECURE)
    }

    override fun onStop() {
        super.onStop()
        unregisterScreenCaptureCallback(this)
    }

    override fun onScreenCaptured() {
        viewModel.onScreensCaptured()
    }

    companion object {
        fun start(activity: ComponentActivity) {
            val intent = Intent(activity, ScreenshotActivity::class.java)
            activity.startActivity(intent)
        }
    }
}

@Preview
@Composable
fun ScreenshotLightPreview() {
    Android14Theme {
        SetupM3Scaffold(Destination.ScreenshotDetection) { paddingValues ->
            ScreenshotScreen(paddingValues)
        }
    }
}


@Preview
@Composable
fun ScreenshotDarkPreview() {
    Android14Theme(useDarkTheme = true) {
        SetupM3Scaffold(Destination.ScreenshotDetection) { paddingValues ->
            ScreenshotScreen(paddingValues)
        }
    }
}
