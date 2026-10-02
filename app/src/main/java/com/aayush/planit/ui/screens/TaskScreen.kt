package com.aayush.planit.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aayush.planit.data.room_database.TaskItem
import com.aayush.planit.ui.components.TaskItemCard
import com.aayush.planit.ui.components.TaskTopAppBar
import com.aayush.planit.ui.theme.MyDarkGrey
import com.aayush.planit.ui.theme.MyLightGrey
import com.aayush.planit.viewmodel.TaskViewModel

@Composable
fun TaskScreen(viewModel: TaskViewModel) {

    val tasks by viewModel.allTasks.collectAsState()

    var taskToEdit by rememberSaveable { mutableStateOf<TaskItem?>(null) }
    var showEditDialog by rememberSaveable { mutableStateOf(false) }

    Scaffold(

        topBar = { TaskTopAppBar(tasks) },

        floatingActionButton = {

            ExtendedFloatingActionButton(
                onClick = {
                    taskToEdit = null
                    showEditDialog = true
                },
                shape = RoundedCornerShape(20.dp),
                containerColor = MyDarkGrey,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 8.dp
                )
            ){

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add task"
                )

                Text(
                    text = "New Task",
                    maxLines = 1,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )

            }

        }

    ) { innerPadding ->

        if (tasks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No Tasks", color = MyLightGrey)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(top = 16.dp, bottom = 80.dp)
            ) {
                items(items = tasks, key = { it.id }) { task ->
                    TaskItemCard(
                        item = task,
                        onEditClick = { taskToEdit = task; showEditDialog = true },
                        onDeleteClick = { viewModel.deleteTask(task) },
                        onCheckboxClick = { checked ->
                            viewModel.updateTask(task.copy(isDone = checked))
                        }
                    )
                }
            }
        }
    }

    if(showEditDialog){
        TaskEditor(
            task = taskToEdit,
            onSaveClick = { newName ->
                if(taskToEdit == null){
                    viewModel.addTask(TaskItem(taskName = newName, isDone = false))
                }
                else{
                    taskToEdit?.let { currentTask ->
                        viewModel.updateTask(currentTask.copy(taskName = newName))
                    }
                }
                showEditDialog = false
                taskToEdit = null
            },
            onCancelClick = {
                showEditDialog = false
                taskToEdit = null
            }
        )
    }
}