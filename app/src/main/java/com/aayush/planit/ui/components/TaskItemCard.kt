package com.aayush.planit.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration.Companion.LineThrough
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aayush.planit.data.room_database.TaskItem
import com.aayush.planit.ui.theme.MyDarkGrey
import com.aayush.planit.ui.theme.MyLightGrey

@Composable
fun TaskItemCard(
    item: TaskItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onCheckboxClick: (Boolean) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            if (item.isDone) Color.LightGray.copy(0.5f) else Color.White
        ),
        border = BorderStroke(1.dp, MyDarkGrey)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = { onCheckboxClick(!item.isDone) },
                modifier = Modifier.size(24.dp)
            ) {

                Icon(
                    imageVector = if(item.isDone) Icons.Filled.CheckCircle else Icons.Default.RadioButtonUnchecked,
                    contentDescription = null,
                    tint = if(item.isDone) MyDarkGrey.copy(0.8f) else MyDarkGrey,
                )

            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = item.taskName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f),
                color = if(!item.isDone) MyDarkGrey else MyLightGrey,
                textDecoration = if(item.isDone) LineThrough else null
            )

            Row(

            ) {

                IconButton(
                    onClick =  onEditClick
                ){
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Task",
                        tint = MyLightGrey,
                        modifier = Modifier.size(24.dp)
                    )

                }

                IconButton(
                    onClick = onDeleteClick
                ){
                    Icon(
                        imageVector = Icons.Default.DeleteOutline,
                        contentDescription = "Edit Task",
                        tint = MyLightGrey,
                        modifier = Modifier.size(24.dp)
                    )

                }

            }

        }

    }

}