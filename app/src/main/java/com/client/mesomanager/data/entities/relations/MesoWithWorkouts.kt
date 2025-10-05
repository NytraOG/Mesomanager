package com.client.mesomanager.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.entities.Workout

data class MesoWithWorkouts(
    @Embedded val mesocycle: Mesocycle,
    @Relation(
        parentColumn = "id",
        entityColumn = "mesocycleId"
    )
    val workouts: List<Workout>
)
