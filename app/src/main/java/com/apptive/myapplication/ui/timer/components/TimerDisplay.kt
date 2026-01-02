package com.apptive.myapplication.ui.timer.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerDisplay(
    isWorking: Boolean,
    timeLeft: String,
    progress: Float
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(240.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color(0xFFF3F4F6),
                style = Stroke(width = 14.dp.toPx())
            )
            drawArc(
                color = Color(0xFFDC2626),
                startAngle = -90f,
                sweepAngle = 360f * progress,
                useCenter = false,
                style = Stroke(width = 14.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                // TimerDisplay.kt의 Text 부분 수정

                    text = if (isWorking) "집중 시간" else "휴식 시간",
                    // 집중은 빨간색, 휴식은 초록색으로 확실히 구분!
                    color = if (isWorking) Color(0xFFDC2626) else Color(0xFF10B981),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
            )

                        Text(
                text = timeLeft,
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )
        }
    }
}
