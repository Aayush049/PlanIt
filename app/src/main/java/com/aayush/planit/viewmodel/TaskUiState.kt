package com.aayush.planit.viewmodel

import com.aayush.planit.data.room_database.TaskItem

sealed interface TaskUiState {
    data object Loading : TaskUiState
    data class Success(val tasks: List<TaskItem>) : TaskUiState
    data class Error(val message: String) : TaskUiState
}
