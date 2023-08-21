package me.globant.android14.activities

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import me.globant.android14.utils.AuthBroadcastReceiver
import me.globant.android14.nav.Destination
import me.globant.android14.ui.composables.SetupM3Scaffold
import me.globant.android14.ui.theme.Android14Theme

class BroadcastReceiverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Android14Theme {
                SetupM3Scaffold(Destination.BroadcastReceiver) { paddingValues ->
                    Column(
                        modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Broadcast send")
                        val context = LocalContext.current
                        Button(onClick = {
                            val intent = Intent("MyAction")
                            intent.action = "MyAction"
                            context.sendBroadcast(intent)
                        }) {
                            Text(text = "Click here to send Broadcast")
                        }
                    }

                }
            }
        }

        val intentFilter = IntentFilter("MyAction") // configure IntentFilter
        registerReceiver(AuthBroadcastReceiver(), intentFilter, Context.RECEIVER_NOT_EXPORTED)
        //unregisterReceiver(AuthBroadcastReceiver())
    }

    companion object {
        fun start(activity: ComponentActivity) {
            val intent = Intent(activity, BroadcastReceiverActivity::class.java)
            activity.startActivity(intent)
        }
    }

}