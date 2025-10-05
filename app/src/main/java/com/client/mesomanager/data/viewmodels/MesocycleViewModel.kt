package com.client.mesomanager.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.mesomanager.data.daos.MesocycleDao
import com.client.mesomanager.data.entities.Mesocycle
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MesocycleViewModel @Inject constructor(
    private val mesocycleDao: MesocycleDao
) : ViewModel() {
    private val _mesocycle = MutableStateFlow<Mesocycle?>(null)
    val mesocycle = _mesocycle.asStateFlow()

    fun createMesocycle() {
        viewModelScope.launch(Dispatchers.IO) {
            val newMeso = Mesocycle(1)

            mesocycleDao.insert(newMeso)
        }
    }
}