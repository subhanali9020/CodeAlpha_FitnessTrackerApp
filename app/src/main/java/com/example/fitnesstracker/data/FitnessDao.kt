package com.example.fitnesstracker.data

import androidx.room.*
import com.example.fitnesstracker.model.FitnessActivity
import kotlinx.coroutines.flow.Flow

@Dao
interface FitnessDao {
    @Query("SELECT * FROM fitness_activities ORDER BY timestamp DESC")
    fun getAllActivities(): Flow<List<FitnessActivity>>

    @Insert
    suspend fun insertActivity(activity: FitnessActivity)

    @Delete
    suspend fun deleteActivity(activity: FitnessActivity)
}