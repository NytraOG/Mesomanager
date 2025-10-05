package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Set(
    @PrimaryKey val id: Int,
    val exercise: Exercise,
    val repetitionsTarget: Int,
    val repetitionsDone: Int
)