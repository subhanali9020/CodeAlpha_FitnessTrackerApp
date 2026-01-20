package com.example.fitnesstracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddActivityDialog(onDismiss: () -> Unit, onSave: (String, Int, Int) -> Unit) {
    var type by remember { mutableStateOf("") }
    var steps by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Log Activity") },
        text = {
            Column {
                OutlinedTextField(value = type, onValueChange = { type = it }, label = { Text("Activity (Running, Walking)") })
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = steps, onValueChange = { steps = it }, label = { Text("Steps") })
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = calories, onValueChange = { calories = it }, label = { Text("Calories Burned") })
            }
        },
        confirmButton = {
            Button(onClick = {
                if(type.isNotBlank()) onSave(type, steps.toIntOrNull() ?: 0, calories.toIntOrNull() ?: 0)
            }) { Text("Save") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}