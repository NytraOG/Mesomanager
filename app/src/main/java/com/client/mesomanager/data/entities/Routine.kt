package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Routine(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val workoutId: Int,
    val exerciseId: Int,
)
