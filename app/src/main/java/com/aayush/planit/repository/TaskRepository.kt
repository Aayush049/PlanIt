package com.aayush.planit.repository

import com.aayush.planit.data.room_database.TaskDao
import com.aayush.planit.data.room_database.TaskItem
import kotlinx.coroutines.flow.Flow

// Repository acts as a middle layer between the ViewModel and the data source.
// It provides a clean API for the ViewModel to access task data.
class TaskRepository(private val taskDao: TaskDao) {

    // Observes all tasks from the database.
    // Flow automatically emits updated data whenever the tasks table changes.
    fun getAllTasks(): Flow<List<TaskItem>> {
        return taskDao.getAllTasks()
    }

    // Inserts a new task into the database.
    // suspend allows the operation to be called from a coroutine.
    suspend fun insert(task: TaskItem) {
        taskDao.insert(task)
    }

    // Updates an existing task in the database.
    suspend fun update(task: TaskItem) {
        taskDao.update(task)
    }

    // Deletes a task from the database.
    suspend fun delete(task: TaskItem) {
        taskDao.delete(task)
    }
}