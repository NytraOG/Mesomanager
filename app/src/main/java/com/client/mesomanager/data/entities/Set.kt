package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Set(
    @PrimaryKey val id: UUID,
    val routine: Routine,
    val load: Double,
    val repetitionsTarget: Int,
    val repetitionsDone: Int
)