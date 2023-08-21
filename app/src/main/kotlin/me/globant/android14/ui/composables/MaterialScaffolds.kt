package me.globant.android14.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import me.globant.android14.nav.Destination


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun SetupM3Scaffold(destination: Destination, content: @Composable (PaddingValues) -> Unit = {}) {
    Surface {
        Scaffold(
            topBar = { TopAppBar(destination.title) },
            content = content
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(title: String) {
    LargeTopAppBar(
        title = { Text(text = title) },
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
    )
}