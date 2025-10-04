package com.client.mesomanager.data.daos

import androidx.room.Dao
import androidx.room.Query
import com.client.mesomanager.data.entities.Exercise

@Dao
interface ExerciseDao : BaseDao<Exercise> {
    @Query("SELECT * FROM exercise")
    fun getAll():List<Exercise>

    @Query("SELECT * FROM exercise WHERE id = (:id)")
    fun get(id: Int): Exercise
}