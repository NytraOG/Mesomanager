package com.client.mesomanager.data.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.client.mesomanager.data.Database
import com.client.mesomanager.data.entities.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExercisesViewModel(
    application: Application
) : AndroidViewModel(application) {
    val db = Database.getInstance(application.applicationContext)

    fun LoadExercises(){
        viewModelScope.launch(Dispatchers.IO) {
            val exercise = Exercise(0, "Curls")
            val dao = db.exerciseDao()

            dao.insert(exercise)

            val reload = dao.get(exercise.id)

        }
    }
}