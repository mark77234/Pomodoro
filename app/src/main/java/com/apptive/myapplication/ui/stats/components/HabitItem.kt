package com.apptive.myapplication.ui.stats.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apptive.myapplication.ui.stats.HabitStatus

@Composable
fun HabitItem(habitName: String) {
    var currentStatus by remember {
        mutableStateOf(HabitStatus.NOT_COMPLETED)
    }

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
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = habitName,
                fontSize = 17.sp,
            )
            Text(
                text = currentStatus.displayText,
                fontSize = 13.sp,
                color = currentStatus.textColor,
                modifier = Modifier
                    .background(
                        color = currentStatus.bgColor,
                        shape = RoundedCornerShape(50.dp)
                    )
                    .clickable{
                        currentStatus = when (currentStatus) {
                            HabitStatus.NOT_COMPLETED -> HabitStatus.IN_PROGRESS
                            HabitStatus.IN_PROGRESS -> HabitStatus.COMPLETED
                            HabitStatus.COMPLETED -> HabitStatus.NOT_COMPLETED
                        }
                    }
                    .padding(
                        horizontal = 10.dp,
                        vertical = 4.dp
                    )
            )
        }
    }
}
