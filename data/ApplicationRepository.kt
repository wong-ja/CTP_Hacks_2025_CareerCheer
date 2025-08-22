package com.example.careercheer.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApplicationRepository(private val applicationDao: ApplicationDao) {

    suspend fun getAllApplications(): List<Application> = withContext(Dispatchers.IO) {
        applicationDao.getAllApplications()
    }

    suspend fun insertApplication(application: Application) = withContext(Dispatchers.IO) {
        applicationDao.insert(application)
    }

    suspend fun updateApplication(application: Application) = withContext(Dispatchers.IO) {
        applicationDao.update(application)
    }

    suspend fun deleteApplication(application: Application) = withContext(Dispatchers.IO) {
        applicationDao.delete(application)
    }

    // Dummy data input
    suspend fun insertSampleApplications() {
        val sampleApps = listOf(
            Application(
                title = "Software Engineer",
                company = "Google",
                location = "Mountain View, CA",
                description = "Develop scalable backend services",
                requirements = "Kotlin, Java, Cloud",
                link = "https://careers.google.com/jobs",
                techStack = "Kotlin, Java, AWS",
                notes = "Excited for this role",
                status = ApplicationStatus.Applied,
                applicationDate = System.currentTimeMillis()
            ),
            Application(
                title = "Cloud Architect",
                company = "Amazon",
                location = "Seattle, WA",
                description = "Design cloud infrastructure",
                requirements = "AWS, CloudFormation",
                link = "https://amazon.jobs",
                techStack = "AWS, Terraform",
                notes = "",
                status = ApplicationStatus.TechnicalInterview,
                applicationDate = System.currentTimeMillis() - 86400000L
            )
        )
        sampleApps.forEach { insertApplication(it) }
    }
}
