package com.aayush.planit.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Factory responsible for creating TaskViewModel instances.
// It is needed because TaskViewModel requires an Application parameter.
class TaskViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    // Creates and returns the requested ViewModel instance.
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        // Checks whether the requested ViewModel is TaskViewModel
        // or a compatible class.
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {

            // Kotlin cannot automatically verify this generic cast at compile time.
            @Suppress("UNCHECKED_CAST")

            // Creates TaskViewModel using the Application instance
            // and casts it to the requested ViewModel type.
            return TaskViewModel(application) as T
        }

        // Prevents creation of unsupported ViewModel types.
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}