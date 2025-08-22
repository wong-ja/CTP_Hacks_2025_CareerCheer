package com.example.careercheer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "applications")
data class Application(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val company: String,
    val location: String? = null,
    val description: String? = null,
    val requirements: String? = null,
    val link: String? = null,
    val techStack: String? = null,
    val notes: String? = null,
    val status: ApplicationStatus = ApplicationStatus.Applied,
    val applicationDate: Long
)

enum class ApplicationStatus {
    Applied,
    BehavioralInterview,
    TechnicalInterview,
    TakeHomeAssessment,
    Onsite,
    Whiteboard,
    Offer,
    Accepted,
    Rejected,
    PositionFilled
}
