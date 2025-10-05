package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mesocycle(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = ""
)