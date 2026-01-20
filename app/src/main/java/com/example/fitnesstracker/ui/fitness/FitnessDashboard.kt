package com.example.fitnesstracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fitnesstracker.viewmodel.FitnessViewModel

@Composable
fun FitnessDashboard(viewModel: FitnessViewModel = viewModel()) {
    val activities by viewModel.activities.collectAsState()

    // Dialog ko control karne ke liye state
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+", style = MaterialTheme.typography.headlineSmall)
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Fitness Dashboard", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // --- Summary Card ---
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    val totalSteps = activities.sumOf { it.steps }
                    Text("Total Steps: $totalSteps / 10000", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { (totalSteps / 10000f).coerceAtMost(1f) },
                        modifier = Modifier.fillMaxWidth().height(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Activities List ---
            Text("Recent Activities", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(activities) { activity ->
                    ListItem(
                        headlineContent = { Text(activity.type) },
                        supportingContent = { Text("${activity.steps} steps | ${activity.calories} kcal") },
                        trailingContent = {
                            // Time dikhane ke liye optional
                            Text(java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault()).format(activity.timestamp))
                        }
                    )
                    HorizontalDivider()
                }
            }
        }

        // --- Dialog Logic ---
        if (showDialog) {
            AddActivityDialog(
                onDismiss = { showDialog = false },
                onSave = { type, steps, calories ->
                    viewModel.addActivity(type, steps, calories)
                    showDialog = false // Save karne ke baad dialog band ho jaye
                }
            )
        }
    }
}