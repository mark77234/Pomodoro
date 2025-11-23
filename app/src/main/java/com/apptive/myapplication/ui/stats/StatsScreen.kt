package com.apptive.myapplication.ui.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apptive.myapplication.ui.stats.components.HabitItem
import com.apptive.myapplication.ui.theme.backgroundColor

@Composable
fun StatsTab(modifier: Modifier = Modifier) {
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
                    shape = RoundedCornerShape(16.dp))
                .border(
                    width = 0.3.dp,
                    color = Color.Gray.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp)
                )
        ){
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Text(
                    text = "습관 달성 현황",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(70.dp))
                HabitItem("공부 30분")
                Spacer(modifier = Modifier.height(13.dp))
                HabitItem("노트 정리")
                Spacer(modifier = Modifier.height(13.dp))
                HabitItem("문제 풀기")
            }
        }
    }
}

