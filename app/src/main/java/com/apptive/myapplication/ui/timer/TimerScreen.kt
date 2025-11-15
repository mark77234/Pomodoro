package com.apptive.myapplication.ui.timer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerTab(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        Text(
            text = "뽀모도로 타이머",
            modifier = Modifier
                .padding(bottom = 8.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold

        )
        Text(
            text = "타이머를 시작하세요",
            color = Color.Gray
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            contentAlignment = Alignment.Center
        ) {

        }

    }








}
