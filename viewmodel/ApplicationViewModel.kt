package com.example.careercheer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.careercheer.data.Application
import com.example.careercheer.data.ApplicationRepository
import com.example.careercheer.data.ApplicationStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class ApplicationViewModel(private val repository: ApplicationRepository) : ViewModel() {

    private val _applications = MutableStateFlow<List<Application>>(emptyList())
    val applications: StateFlow<List<Application>> = _applications

    // state flow changes for search query and status filter
    private val _searchQuery = MutableStateFlow("")
    private val _statusFilter = MutableStateFlow<ApplicationStatus?>(null)

    init {
        loadApplications()
    }

    fun loadApplications() {
        viewModelScope.launch {
            val allApps = repository.getAllApplications()
            Log.d("ApplicationViewModel", "Loaded ${allApps.size} applications")

            val filteredApps = allApps.filter { app ->
                (_statusFilter.value == null || app.status == _statusFilter.value) &&
                        (_searchQuery.value.isBlank() || app.title.contains(_searchQuery.value, ignoreCase = true) ||
                                app.company.contains(_searchQuery.value, ignoreCase = true) ||
                                (app.techStack?.contains(_searchQuery.value, ignoreCase = true) ?: false))
            }
            _applications.value = filteredApps
        }
    }

    fun setFilterStatus(status: ApplicationStatus?) {
        _statusFilter.value = status
        loadApplications()
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        loadApplications()
    }

    fun addApplication(application: Application) {
        viewModelScope.launch {
            repository.insertApplication(application)
            loadApplications()
        }
    }

    fun updateApplication(application: Application) {
        viewModelScope.launch {
            repository.updateApplication(application)
            loadApplications()
        }
    }

    fun deleteApplication(application: Application) {
        viewModelScope.launch {
            repository.deleteApplication(application)
            loadApplications()
        }
    }

    fun insertSampleData() {
        viewModelScope.launch {
            val sampleApps = listOf(
                Application(
                    title = "Test Developer",
                    company = "Test Company",
                    applicationDate = System.currentTimeMillis(),
                    status = ApplicationStatus.Applied,
                    notes = "Sample notes"
                ),
                Application(
                    title = "Frontend Engineer",
                    company = "Example Corp",
                    applicationDate = System.currentTimeMillis() - 86400000L,
                    status = ApplicationStatus.TechnicalInterview,
                    notes = "Second round interview"
                )
            )
            sampleApps.forEach { repository.insertApplication(it) }
            loadApplications()
            Log.d("ApplicationViewModel", "Loaded after insert: ${_applications.value.size} applications")
        }
    }

}
