package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Mesocycle(
    @PrimaryKey val id: UUID,
    val name: String = ""
)