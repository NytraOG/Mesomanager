package com.client.mesomanager.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.mesomanager.data.daos.ExerciseDao
import com.client.mesomanager.data.entities.Exercise
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.entities.dtos.NewExerciseDto
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ExercisesViewModel @Inject constructor(
    private val exerciseDao: ExerciseDao
) : ViewModel() {
    private val _selectedExercise = MutableStateFlow<Exercise?>(null)
    val selectedExercise = _selectedExercise.asStateFlow()

    private val _allExercises = MutableStateFlow<List<Exercise>>(emptyList())
    val allExercises = _allExercises.asStateFlow()

    fun loadAllExercises(){
        viewModelScope.launch(Dispatchers.IO){
            val allExercises = exerciseDao.getAll()

            _allExercises.value = allExercises
        }
    }

    fun createExercises(dto: NewExerciseDto) {
        viewModelScope.launch(Dispatchers.IO) {
            val newExercise = Exercise(
                name = dto.name,
                type = dto.type,
                muscleGroupPrimary = dto.muscleGroupPrimary,
                muscleGroupSecondary = dto.muscleGroupSecondary,
                videoId = dto.videoId
            )

            saveNewExercise(newExercise)
            _allExercises.value = _allExercises.value + newExercise
        }
    }

    fun saveNewExercise(exercise: Exercise) {
        viewModelScope.launch(Dispatchers.IO) {
            exerciseDao.insert(exercise)
        }
    }

    fun loadExercise(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = exerciseDao.get(id)
            _selectedExercise.value = result
        }
    }

    fun deleteExercise(exercise: Exercise){
        viewModelScope.launch(Dispatchers.IO){
            exerciseDao.delete(exercise)
        }
    }
}