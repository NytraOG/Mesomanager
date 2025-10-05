package com.client.mesomanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.client.mesomanager.ui.composables.BottomNavigationBar
import com.client.mesomanager.ui.theme.MesomanagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MesomanagerTheme {
                BottomNavigationBar()
            }
        }
    }
}