package com.client.mesomanager.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.mesomanager.data.daos.MesocycleDao
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.entities.dtos.NewMesoDto
import com.client.mesomanager.data.enums.TrainingIntent
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

    private val _mesocycles = MutableStateFlow<List<Mesocycle>>(emptyList())
    val mesocycles = _mesocycles.asStateFlow()

    fun createMesocycle(dto: NewMesoDto) {
        executeAsync {
            val newMeso = Mesocycle(
                name = dto.name,
                days = dto.days,
                weeks = dto.weeks,
                intent = TrainingIntent.Strength
            )

            saveNewMesocycle(newMeso)
            _mesocycles.value = _mesocycles.value + newMeso
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

    fun deleteMesocycle(meso: Mesocycle){
        executeAsync {
            _mesocycles.value = _mesocycles.value - meso
            mesocycleDao.delete(meso)
        }
    }

    fun getAllMesocycles() {
        executeAsync {
            val allMesos = mesocycleDao.getMesocycleWithWorkouts().map { it.mesocycle }
            _mesocycles.value = allMesos
        }
    }

    private fun executeAsync(action: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            action()
        }
    }
}