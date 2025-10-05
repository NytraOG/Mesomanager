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
        executeAsync {
            val newMeso = Mesocycle()
            _mesocycle.value = newMeso
        }
    }

    fun saveNewMesocycle(meso: Mesocycle) {
        executeAsync {
            mesocycleDao.insert(meso)
        }
    }

    fun updateMesocycle(meso: Mesocycle) {
        executeAsync {
            mesocycleDao.update(meso)
        }
    }

    private fun executeAsync(action: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            action()
        }
    }
}