package com.apptive.myapplication.ui.timer.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ModeSelector(
    is50minMode: Boolean,
    onModeChange: (Boolean) -> Unit
) {    Column(
    modifier = Modifier
        .fillMaxWidth()
        .shadow(elevation = 3.dp, shape = RoundedCornerShape(12.dp))
        .background(color = Color.White, shape = RoundedCornerShape(12.dp))
        .padding(16.dp)
) {
    Text(text = "타이머 모드", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
    Spacer(modifier = Modifier.height(12.dp))

    // Row 내부의 버튼들에 Modifier.weight(1f)를 전달합니다.
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ModeButton(
            text = "50/10",
            isSelected = is50minMode,
            modifier = Modifier.weight(1f) // 반반씩 나눠 갖도록 설정
        ) { onModeChange(true) }

        ModeButton(
            text = "30/5",
            isSelected = !is50minMode,
            modifier = Modifier.weight(1f) // 반반씩 나눠 갖도록 설정
        ) { onModeChange(false) }
    }

    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = if (is50minMode) "50분 공부, 10분 휴식" else "30분 공부, 5분 휴식",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        color = Color.Gray
    )
}
}

@Composable
fun ModeButton(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier, // Modifier를 파라미터로 받도록 수정
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        // fillMaxWidth()를 제거하고 외부에서 받아온 modifier(weight)를 적용합니다.
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFFDC2626) else Color(0xFFF3F4F6)
        )
    ) {
        Text(text, color = if (isSelected) Color.White else Color.Black)
    }
}
