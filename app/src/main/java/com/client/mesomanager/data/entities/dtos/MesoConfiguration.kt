package com.client.mesomanager.data.entities.dtos

import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.enums.MuscleGroup

data class MesoConfiguration(
    val meso: Mesocycle,
    val muscleGroupsOfDay: MutableMap<Int, List<MuscleGroup>> = mutableMapOf()
)