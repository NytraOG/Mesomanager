package com.client.mesomanager.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.client.mesomanager.data.daos.ExerciseDao
import com.client.mesomanager.data.daos.MesocycleDao
import com.client.mesomanager.data.entities.Exercise
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.entities.Routine
import com.client.mesomanager.data.entities.Workout
import com.client.mesomanager.data.entities.Set
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@TypeConverters(Converters::class)
@Database(
    entities = [Exercise::class, Mesocycle::class, Routine::class, Set::class, Workout::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun mesocycleDao(): MesocycleDao
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    const val DATABASE_NAME = "mesomanager_db"

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    fun provideExerciseDao(db: AppDatabase): ExerciseDao = db.exerciseDao()

    @Provides
    fun provideMesocycleDao(db: AppDatabase): MesocycleDao = db.mesocycleDao()
}