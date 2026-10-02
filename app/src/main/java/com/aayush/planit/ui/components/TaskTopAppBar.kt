package com.aayush.planit.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aayush.planit.data.room_database.TaskItem
import com.aayush.planit.ui.theme.MyDarkGrey
import com.aayush.planit.ui.theme.MyLightGrey

// Displays the main top app bar for the task screen.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskTopAppBar(tasks: List<TaskItem>) {

    TopAppBar(
        title = {
            Column {
                Text(
                    text = "PlanIt",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = MyDarkGrey
                )
                Text(
                    text = "${tasks.count { !it.isDone }} tasks remaining",
                    color = MyLightGrey,
                    fontSize = 16.sp
                )
            }
        }
    )
}