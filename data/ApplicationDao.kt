package com.example.careercheer.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ApplicationDao {
    @Query("SELECT * FROM applications ORDER BY applicationDate DESC")
    suspend fun getAllApplications(): List<Application>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(application: Application)

    @Update
    suspend fun update(application: Application)

    @Delete
    suspend fun delete(application: Application)
}