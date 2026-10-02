package com.aayush.planit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.aayush.planit.ui.screens.TaskScreen
import com.aayush.planit.ui.theme.PlanItTheme
import com.aayush.planit.viewmodel.TaskViewModel
import com.aayush.planit.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel : TaskViewModel by viewModels {
        TaskViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlanItTheme {
                TaskScreen(viewModel)
            }
        }
    }
}