package com.client.mesomanager.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.client.mesomanager.data.entities.Exercise

@Dao
interface BaseDao<T> {
    @Insert
    fun insert(vararg objects: T)

    @Update
    fun update(vararg objects: T)

    @Delete
    fun delete(obj: Exercise)
}