package com.client.mesomanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.client.mesomanager.data.daos.ExerciseDao
import com.client.mesomanager.data.entities.Exercise

@Database(entities = [Exercise::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}