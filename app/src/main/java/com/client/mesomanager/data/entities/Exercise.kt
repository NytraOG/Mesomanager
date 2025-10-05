package com.client.mesomanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.client.mesomanager.data.enums.ExerciseType
import com.client.mesomanager.data.enums.MuscleGroup
import java.util.UUID

@Entity
data class Exercise(
    @PrimaryKey val id: UUID,
    val name: String,
    val muscleGroupPrimary: MuscleGroup,
    val muscleGroupSecondary: MuscleGroup,
    val type: ExerciseType,
    val videoId: String?,
    val routines: List<Routine>
)