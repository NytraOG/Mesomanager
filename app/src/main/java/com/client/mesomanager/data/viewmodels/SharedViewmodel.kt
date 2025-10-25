package com.client.mesomanager.data.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.enums.MuscleGroup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewmodel : ViewModel() {

    private val _currentMeso = MutableStateFlow<Mesocycle?>(null)
    val currentMeso = _currentMeso.asStateFlow()

    private val _muscleGroupSelectionOfDay = MutableStateFlow<MutableMap<Int, List<MuscleGroup>>>(mutableMapOf())
    val muscleGroupSelectionOfDay = _muscleGroupSelectionOfDay.asStateFlow()


    fun setMeso(meso: Mesocycle?) {
        _currentMeso.value = meso
    }

    fun insertMuscleGroupSelection(day: Int, muscleGroup: MuscleGroup){
        val existing = _muscleGroupSelectionOfDay.value[day] ?: emptyList()
        val newList = existing + muscleGroup
        val newDict = _muscleGroupSelectionOfDay.value.toMutableMap()
        newDict[day] = newList

        _muscleGroupSelectionOfDay.value = newDict
    }

    fun removeMuslceGroupSelection(day: Int, muscleGroup: MuscleGroup){
        val muscleGroupsOfDay = _muscleGroupSelectionOfDay.value[day] ?: emptyList()
        val newList = muscleGroupsOfDay - muscleGroup
        val newDict = _muscleGroupSelectionOfDay.value.toMutableMap()
        newDict[day] = newList

        _muscleGroupSelectionOfDay.value = newDict
    }

    fun finalizeMesocycle(){

        resetViewModel()
    }

    fun resetViewModel(){

    }
}