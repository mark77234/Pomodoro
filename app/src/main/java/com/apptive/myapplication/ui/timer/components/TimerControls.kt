package com.apptive.myapplication.ui.timer.components

import androidx.compose.material3.Button
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TimerControls(
    isStarted: Boolean,
    onToggleTimer: () -> Unit,
    onReset: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = onToggleTimer,
            modifier = Modifier
                .height(48.dp)
                .weight(1f),
            shape = RoundedCornerShape(12.dp),
            colors = buttonColors(
                containerColor = if (isStarted) Color(0xFFE5E7EB) else Color(0xFFDC2626)
            )
        ) {
            Text(if (isStarted) "일시정지" else "시작", color = if (isStarted) Color.Black else Color.White)
        }

        Button(
            onClick = onReset,
            modifier = Modifier
                .height(48.dp)
                .weight(1f),
            shape = RoundedCornerShape(12.dp),
            colors = buttonColors(containerColor = Color(0xFFF3F4F6))
        ) {
            Text("초기화", color = Color.Black)
        }
    }
}
