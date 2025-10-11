package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.client.mesomanager.data.enums.TrainingIntent

@Entity
data class Mesocycle(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "",
    val days: Int,
    val weeks: Int,
    val intent: TrainingIntent
)