package me.globant.android14.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * PURPOSE : <Enter Purpose of the class>
 * Additional details, if Any
 * Created by Sumit Patil on 30/11/23 5:22 pm
 * Copyright @ Globant
 */

class AuthBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("MyReceiver", "MyAction received!")
    }
}