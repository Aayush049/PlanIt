package com.aayush.planit.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aayush.planit.data.room_database.TaskItem
import com.aayush.planit.ui.theme.MyDarkGrey
import com.aayush.planit.ui.theme.MyLightGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditor(
    task: TaskItem?,
    onSaveClick: (String) -> Unit,
    onCancelClick: () -> Unit
) {

    var taskName by rememberSaveable { mutableStateOf(task?.taskName ?: "") }

    ModalBottomSheet(
        onDismissRequest = onCancelClick,
        containerColor = Color.White,

    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .navigationBarsPadding()
        ) {
            Text(
                text = if(task == null) "Create New Task" else "Edit Task",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MyDarkGrey
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Enter task name"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MyDarkGrey,
                    unfocusedBorderColor = MyLightGrey
                ),
                shape = RoundedCornerShape(13.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onSaveClick(taskName.trim()) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MyDarkGrey
                ),
                enabled = taskName.isNotBlank()
            ) {
                Text(
                    text = "Save Task",
                    fontSize = 26.sp
                )
            }
        }
    }
}