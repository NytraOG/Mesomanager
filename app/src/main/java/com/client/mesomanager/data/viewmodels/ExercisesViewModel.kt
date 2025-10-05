package com.client.mesomanager.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.mesomanager.data.daos.ExerciseDao
import com.client.mesomanager.data.entities.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesViewModel @Inject constructor(
    private val exerciseDao: ExerciseDao
) : ViewModel() {
    private val _exercise = MutableStateFlow<Exercise?>(null)
    val exercise = _exercise.asStateFlow()

    fun createExercises() {
        viewModelScope.launch(Dispatchers.IO) {
            val objToInsert = Exercise(0, "Curls")
            exerciseDao.insert(objToInsert)
        }
    }

    fun loadExercise(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = exerciseDao.get(id)
            _exercise.value = result
        }
    }
}