package com.example.fitnesstracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fitness_activities")
data class FitnessActivity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,        // e.g., "Walking", "Running"
    val steps: Int = 0,
    val calories: Int = 0,
    val timestamp: Long = System.currentTimeMillis()
)