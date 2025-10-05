package com.client.mesomanager.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.entities.relations.MesoWithWorkouts

@Dao
interface MesocycleDao : BaseDao<Mesocycle> {

    @Transaction
    @Query("SELECT * FROM mesocycle")
    fun getMesocycleWithWorkouts(): List<MesoWithWorkouts>
}