package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Routine(
    @PrimaryKey val id: Int,
    val workout: Workout,
    val exercise: Exercise,
    val sets: List<Set>
)
