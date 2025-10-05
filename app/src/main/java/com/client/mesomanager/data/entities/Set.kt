package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Set(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val routineId: Int,
    val load: Double,
    val repetitionsTarget: Int,
    val repetitionsDone: Int
)