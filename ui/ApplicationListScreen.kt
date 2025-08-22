package com.example.careercheer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.careercheer.data.Application
import com.example.careercheer.data.ApplicationStatus
import com.example.careercheer.ui.theme.CareerCheerTheme
import com.example.careercheer.ui.theme.StatusColorMap
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationListScreen(
    applications: List<Application>,
    onAddClick: () -> Unit,
    onEditClick: (Application) -> Unit,
    onDeleteClick: (Application) -> Unit,
    onFilterChange: (ApplicationStatus?) -> Unit,
    onSearchChange: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf<ApplicationStatus?>(null) }

    CareerCheerTheme {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text("CareerCheer - Applications") },
                    actions = {
                        IconButton(onClick = { /* TODO: search/filter dialog/focus functionality */ }) {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = onAddClick) {
                    Icon(Icons.Default.Add, contentDescription = "Add application")
                }
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                // search text
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        onSearchChange(it)
                    },
                    label = { Text("Search by title, company, tech stack...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                // filter applciation status
                Row(Modifier.padding(8.dp)) {
                    FilterChip(
                        selected = selectedFilter == null,
                        onClick = {
                            selectedFilter = null
                            onFilterChange(null)
                        },
                        modifier = Modifier.padding(end = 4.dp),
                        label = { Text("All") }
                    )
                    ApplicationStatus.values().forEach { status ->
                        FilterChip(
                            selected = selectedFilter == status,
                            onClick = {
                                selectedFilter = status
                                onFilterChange(status)
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = StatusColorMap[status]?.copy(alpha = 0.3f)
                                    ?: MaterialTheme.colorScheme.primary,
                                labelColor = StatusColorMap[status]?.copy(alpha = 1f)
                                    ?: MaterialTheme.colorScheme.onPrimary
                            ),
                            modifier = Modifier.padding(end = 4.dp),
                            label = { Text(status.name) }
                        )
                    }
                }

                if (applications.isEmpty()) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No applications found.", style = MaterialTheme.typography.bodyLarge)
                    }
                } else {
                    LazyColumn(Modifier.fillMaxSize()) {
                        items(applications) { application ->
                            ApplicationItem(
                                application = application,
                                onEditClick = onEditClick,
                                onDeleteClick = onDeleteClick
                            )
                            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBar(
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = title,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        actions = actions
    )
}

@Composable
fun ApplicationItem(
    application: Application,
    onEditClick: (Application) -> Unit,
    onDeleteClick: (Application) -> Unit
) {
    val dateFormat = remember { SimpleDateFormat("MMM d, yyyy", Locale.getDefault()) }
    val statusColor = StatusColorMap[application.status] ?: MaterialTheme.colorScheme.primary

    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onEditClick(application) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = "${application.title} @ ${application.company}",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Location: ${application.location ?: "N/A"}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
            Text(
                text = "Applied: ${dateFormat.format(Date(application.applicationDate))}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
            Text(
                text = "Tech stack: ${application.techStack ?: "N/A"}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
            application.notes?.let {
                Text(
                    text = "Notes: $it",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
        }
        Spacer(Modifier.width(12.dp))
        // Status with colored indicator
        Box(
            Modifier
                .size(24.dp)
                .background(statusColor, shape = MaterialTheme.shapes.small)
        )
        Spacer(Modifier.width(12.dp))
        IconButton(onClick = { onDeleteClick(application) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete application",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}
