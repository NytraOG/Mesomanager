package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Routine(
    @PrimaryKey val id: UUID,
    val workout: Workout,
    val exercise: Exercise,
    val sets: List<Set>
)
