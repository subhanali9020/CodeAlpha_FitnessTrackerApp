package com.example.fitnesstracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.fitnesstracker.data.AppDatabase
import com.example.fitnesstracker.model.FitnessActivity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FitnessViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "fitness_tracker_db"
    ).build()

    private val dao = db.fitnessDao()

    val activities = dao.getAllActivities().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addActivity(type: String, steps: Int, calories: Int) {
        viewModelScope.launch {
            dao.insertActivity(FitnessActivity(type = type, steps = steps, calories = calories))
        }
    }
}