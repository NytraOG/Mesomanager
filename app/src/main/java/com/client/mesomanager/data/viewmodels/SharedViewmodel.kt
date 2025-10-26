package com.client.mesomanager.data.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.mesomanager.data.daos.MesocycleDao
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.enums.MuscleGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewmodel : ViewModel() {

    private val _currentMeso = MutableStateFlow<Mesocycle?>(null)
    val currentMeso = _currentMeso.asStateFlow()

    private val _muscleGroupSelectionOfDay = MutableStateFlow<MutableMap<Int, List<MuscleGroup>>>(mutableMapOf())
    val muscleGroupSelectionOfDay = _muscleGroupSelectionOfDay.asStateFlow()

    var mesoDao:MesocycleDao? = null

    fun setMeso(meso: Mesocycle?) {
        _currentMeso.value = meso
    }

    fun setDao(dao: MesocycleDao) {
        mesoDao = dao
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
        if(mesoDao == null)
            return

        viewModelScope.launch(Dispatchers.IO) {

        }

        resetViewModel()
    }

    fun resetViewModel(){

    }
}