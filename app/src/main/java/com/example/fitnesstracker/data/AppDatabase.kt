package com.example.fitnesstracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fitnesstracker.model.FitnessActivity

@Database(entities = [FitnessActivity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fitnessDao(): FitnessDao
}