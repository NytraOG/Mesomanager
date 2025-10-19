package com.client.mesomanager.data.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.client.mesomanager.data.entities.Mesocycle

class SharedViewmodel : ViewModel() {
    var currentMeso by mutableStateOf<Mesocycle?>(null)
}