package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: Date,
    val mesocycleId: Int,
)
