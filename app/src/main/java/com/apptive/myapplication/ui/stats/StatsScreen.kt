package com.apptive.myapplication.ui.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apptive.myapplication.model.HabitStatus
import com.apptive.myapplication.ui.stats.components.HabitItem
import com.apptive.myapplication.ui.theme.backgroundColor
import com.apptive.myapplication.viewmodel.MainViewModel
import java.time.LocalDate

@Composable
fun StatsTab(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val habitList = viewModel.habits
    val today = LocalDate.now()

    val totalCount = habitList.size
    val completedCount = habitList.count { it.isCompletedOn(today) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
    ) {
        Text(
            text = "학습 통계",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "오늘의 습관 달성 현황",
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = 0.3.dp,
                    color = Color.Gray.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Text(
                    text = "습관 달성 현황",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                AchievementSection(totalCount, completedCount)

                Spacer(modifier = Modifier.height(24.dp))

                habitList.forEach { habit ->
                    val isCompleted = habit.isCompletedOn(today)
                    val status = if (isCompleted) HabitStatus.COMPLETED else HabitStatus.NOT_COMPLETED

                    HabitItem(
                        habitName = habit.name,
                        status = status
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                if (habitList.isEmpty()) {
                    Text(
                        text = "등록된 습관이 없습니다.",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AchievementSection(totalCount: Int, completedCount: Int) {
    val progress = if (totalCount > 0) completedCount / totalCount.toFloat() else 0f

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "전체 달성률",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "$completedCount/$totalCount",
                fontSize = 14.sp,
                color = Color(0xFFF87171),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .background(Color(0xFFF5F5F5), RoundedCornerShape(50))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .background(Color(0xFFF87171), RoundedCornerShape(50))
            )
        }
    }
}
