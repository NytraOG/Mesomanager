package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity
data class Workout(
    @PrimaryKey val id: UUID,
    val date: Date,
    val mesocycleId: UUID,
    val routines: List<Routine>
)
