package com.example.careercheer.ui

//import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
import com.example.careercheer.ui.theme.CareerCheerTheme
import com.example.careercheer.ui.theme.applicationFieldColors
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.careercheer.data.Application
import com.example.careercheer.data.ApplicationStatus
//import java.text.SimpleDateFormat
//import java.util.*

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
    var applicationDate by remember { mutableLongStateOf(applicationToEdit?.applicationDate ?: System.currentTimeMillis()) }
    var expanded by remember { mutableStateOf(false) }

    CareerCheerTheme {
        Column(Modifier.fillMaxSize().padding(10.dp).background(MaterialTheme.colorScheme.background)) {
            Text(
                text = if (applicationToEdit == null) "Add New Application" else "Edit Application",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Position / Title") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = applicationFieldColors()
            )
            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = company,
                onValueChange = { company = it },
                label = { Text("Company") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = applicationFieldColors()
            )
            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = applicationFieldColors()
            )
            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5,
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = applicationFieldColors()
            )
            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = requirements,
                onValueChange = { requirements = it },
                label = { Text("Requirements (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5,
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = applicationFieldColors()
            )
            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = link,
                onValueChange = { link = it },
                label = { Text("Link (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = applicationFieldColors()
            )
            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = techStack,
                onValueChange = { techStack = it },
                label = { Text("Tech Stack (comma separated)") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = applicationFieldColors()
            )
            Spacer(Modifier.height(10.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {
                OutlinedTextField(
                    value = status.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Application Status") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier.fillMaxWidth().menuAnchor(),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    colors = applicationFieldColors()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                ) {
                    ApplicationStatus.entries.forEach { statusOption ->
                        DropdownMenuItem(
                            text = { Text(statusOption.name) },
                            onClick = {
                                status = statusOption
                                expanded = false
                            },
                            colors = MenuDefaults.itemColors(
                                textColor = MaterialTheme.colorScheme.onBackground,
                                disabledTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                            )
                        )
                    }
                }
            }

            Spacer(Modifier.height(10.dp))

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5,
                textStyle = MaterialTheme.typography.bodyLarge,
                colors = applicationFieldColors()
            )

            Spacer(Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
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
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Save", color = MaterialTheme.colorScheme.onPrimary)
                }
                Spacer(Modifier.width(10.dp))
                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}