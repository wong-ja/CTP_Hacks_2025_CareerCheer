package com.example.careercheer.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.careercheer.data.Application
import com.example.careercheer.data.ApplicationStatus
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditApplicationScreen(
    applicationToEdit: Application? = null,
    onSave: (Application) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf(applicationToEdit?.title ?: "") }
    var company by remember { mutableStateOf(applicationToEdit?.company ?: "") }
    var location by remember { mutableStateOf(applicationToEdit?.location ?: "") }
    var description by remember { mutableStateOf(applicationToEdit?.description ?: "") }
    var requirements by remember { mutableStateOf(applicationToEdit?.requirements ?: "") }
    var link by remember { mutableStateOf(applicationToEdit?.link ?: "") }
    var techStack by remember { mutableStateOf(applicationToEdit?.techStack ?: "") }
    var notes by remember { mutableStateOf(applicationToEdit?.notes ?: "") }
    var status by remember { mutableStateOf(applicationToEdit?.status ?: ApplicationStatus.Applied) }
    var applicationDate by remember { mutableStateOf(applicationToEdit?.applicationDate ?: System.currentTimeMillis()) }

    var expanded by remember { mutableStateOf(false) }

    val dateFormat = remember { SimpleDateFormat("MMM d, yyyy", Locale.getDefault()) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Position / Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        TextField(
            value = company,
            onValueChange = { company = it },
            label = { Text("Company") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        TextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location (Optional)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description (Optional)") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )
        Spacer(Modifier.height(8.dp))

        TextField(
            value = requirements,
            onValueChange = { requirements = it },
            label = { Text("Requirements (Optional)") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )
        Spacer(Modifier.height(8.dp))

        TextField(
            value = link,
            onValueChange = { link = it },
            label = { Text("Link (Optional)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri)
        )
        Spacer(Modifier.height(8.dp))

        TextField(
            value = techStack,
            onValueChange = { techStack = it },
            label = { Text("Tech Stack (comma separated)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = status.name,
                onValueChange = {},
                readOnly = true,
                label = { Text("Application Status") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                ApplicationStatus.values().forEach { statusOption ->
                    DropdownMenuItem(
                        text = { Text(statusOption.name) },
                        onClick = {
                            status = statusOption
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        TextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes (Optional)") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 4
        )

        Spacer(Modifier.height(16.dp))

        Row {
            Button(onClick = {
                if (title.isNotBlank() && company.isNotBlank()) {
                    val app = Application(
                        id = applicationToEdit?.id ?: 0,
                        title = title,
                        company = company,
                        location = location.takeIf { it.isNotBlank() },
                        description = description.takeIf { it.isNotBlank() },
                        requirements = requirements.takeIf { it.isNotBlank() },
                        link = link.takeIf { it.isNotBlank() },
                        techStack = techStack.takeIf { it.isNotBlank() },
                        notes = notes.takeIf { it.isNotBlank() },
                        status = status,
                        applicationDate = applicationDate
                    )
                    onSave(app)
                }
            }) {
                Text("Save")
            }
            Spacer(Modifier.width(16.dp))
            OutlinedButton(onClick = onCancel) {
                Text("Cancel")
            }
        }
    }
}
