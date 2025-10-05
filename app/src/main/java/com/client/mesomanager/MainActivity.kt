package com.client.mesomanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.client.mesomanager.data.AppDatabase
import com.client.mesomanager.data.Database
import com.client.mesomanager.data.entities.Exercise
import com.client.mesomanager.ui.composables.BottomNavigationBar
import com.client.mesomanager.ui.theme.MesomanagerTheme

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