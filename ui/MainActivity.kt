package com.example.careercheer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.careercheer.data.AppDatabase
import com.example.careercheer.data.ApplicationRepository
import com.example.careercheer.viewmodel.ApplicationViewModel
import com.example.careercheer.viewmodel.ApplicationViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: ApplicationViewModel by viewModels {
        ApplicationViewModelFactory(
            ApplicationRepository(AppDatabase.getDatabase(applicationContext).applicationDao())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.insertSampleData()
        }

        setContent {
            val navController = rememberNavController()

            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = "list") {

                        composable("list") {
                            val apps = viewModel.applications.collectAsState().value

                            ApplicationListScreen(
                                applications = apps,
                                onAddClick = { navController.navigate("add") },
                                onEditClick = { application ->
                                    navController.navigate("edit/${application.id}")
                                },
                                onDeleteClick = { app ->
                                    viewModel.deleteApplication(app)
                                },
                                onFilterChange = { filter ->
                                    viewModel.setFilterStatus(filter)
                                },
                                onSearchChange = { query ->
                                    viewModel.setSearchQuery(query)
                                }
                            )
                        }

                        composable("add") {
                            AddEditApplicationScreen(
                                onSave = { application ->
                                    viewModel.addApplication(application)
                                    navController.popBackStack()
                                },
                                onCancel = { navController.popBackStack() }
                            )
                        }

                        composable("edit/{appId}") { backStackEntry ->
                            val appId = backStackEntry.arguments?.getString("appId")?.toIntOrNull()
                            val application =
                                viewModel.applications.collectAsState().value.find { it.id == appId }

                            if (application != null) {
                                AddEditApplicationScreen(
                                    applicationToEdit = application,
                                    onSave = { updatedApp ->
                                        viewModel.updateApplication(updatedApp)
                                        navController.popBackStack()
                                    },
                                    onCancel = { navController.popBackStack() }
                                )
                            } else {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}
