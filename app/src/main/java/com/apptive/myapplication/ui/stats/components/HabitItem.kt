package com.apptive.myapplication.ui.stats.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apptive.myapplication.model.HabitStatus

@Composable
fun HabitItem(
    habitName: String,
    status: HabitStatus
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.3.dp,
                color = Color.Gray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = habitName,
                fontSize = 17.sp,
            )
            Text(
                text = status.displayText,
                fontSize = 13.sp,
                color = status.textColor,
                modifier = Modifier
                    .background(
                        color = status.bgColor,
                        shape = RoundedCornerShape(50.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }
    }
}