package com.aayush.planit.data.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey

// Marks this data class as a Room entity.
// Room will create a database table named "tasks" for this class.
@Entity(tableName = "tasks")
data class TaskItem(

    // Primary key for uniquely identifying each task.
    // autoGenerate = true makes Room automatically assign a new ID
    // when a task is inserted.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Stores the actual text/name of the task.
    val taskName: String,

    // Stores whether the task has been completed.
    // New tasks are incomplete by default.
    val isDone: Boolean = false
)