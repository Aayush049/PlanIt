package com.aayush.planit.data.room_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// DAO (Data Access Object) defines the operations used to interact with the database.
// It acts as the interface between the application and Room database.

@Dao
interface TaskDao {

    // Inserts a new task into the "tasks" table.
    // REPLACE updates the existing row if a conflict occurs with the primary key.
    // suspend allows the operation to run safely with Kotlin Coroutines.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskItem)

    // Updates an existing task using its primary key.
    // The task object must already exist in the database.
    @Update
    suspend fun update(task: TaskItem)

    // Deletes the specified task from the database.
    @Delete
    suspend fun delete(task: TaskItem)

    // Retrieves all tasks from the database, with the newest task first.
    // Flow automatically emits a new list whenever the table changes.
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): Flow<List<TaskItem>>
}