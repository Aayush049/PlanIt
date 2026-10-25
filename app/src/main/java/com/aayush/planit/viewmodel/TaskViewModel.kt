package com.aayush.planit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.aayush.planit.data.room_database.TaskDatabase
import com.aayush.planit.data.room_database.TaskItem
import com.aayush.planit.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// ViewModel manages task-related UI state and operations.
// It survives configuration changes such as screen rotation.
class TaskViewModel(application: Application) : AndroidViewModel(application) {

    // Gets the DAO from the Room database.
    private val dao = TaskDatabase.getDatabase(application).taskDao()

    // Repository acts as the middle layer between the ViewModel and DAO.
    private val repository = TaskRepository(dao)

    // Converts the database Flow into a StateFlow exposing a sealed TaskUiState.
    val uiState: StateFlow<TaskUiState> = repository.getAllTasks()
        .map<List<TaskItem>, TaskUiState> { tasks ->
            TaskUiState.Success(tasks)
        }
        .catch { exception ->
            emit(TaskUiState.Error(exception.message ?: "Failed to load tasks from local storage"))
        }
        .stateIn(
            // Ties the StateFlow to the ViewModel's lifecycle.
            scope = viewModelScope,

            // Keeps collecting while there are active UI subscribers.
            // Stops collection 5 seconds after the last subscriber disappears.
            started = SharingStarted.WhileSubscribed(5000),

            // Initial value before the database emits its first result.
            initialValue = TaskUiState.Loading
        )

    // Adds a new task to the database.
    fun addTask(task: TaskItem) {
        // Runs the database operation inside the ViewModel's coroutine scope.
        viewModelScope.launch {
            repository.insert(task)
        }
    }

    // Updates an existing task in the database.
    fun updateTask(task: TaskItem) {
        viewModelScope.launch {
            repository.update(task)
        }
    }

    // Deletes a task from the database.
    fun deleteTask(task: TaskItem) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}
