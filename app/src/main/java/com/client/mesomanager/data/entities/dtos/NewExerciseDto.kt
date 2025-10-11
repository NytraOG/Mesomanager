package com.client.mesomanager.data.entities.dtos

import com.client.mesomanager.data.enums.ExerciseType
import com.client.mesomanager.data.enums.MuscleGroup

data class NewExerciseDto(
    val name: String,
    val muscleGroupPrimary: MuscleGroup,
    val muscleGroupSecondary: MuscleGroup,
    val type: ExerciseType,
    val videoId: String?
)
