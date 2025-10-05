package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Workout(
    @PrimaryKey val id: Int,
    val date: Date,
    val mesocycleId: Int,
    val routines: List<Routine>
)
