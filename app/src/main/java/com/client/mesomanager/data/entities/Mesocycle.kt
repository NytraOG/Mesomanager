package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mesocycle(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String = ""
)