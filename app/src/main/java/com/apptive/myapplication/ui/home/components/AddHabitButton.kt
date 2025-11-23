package com.apptive.myapplication.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun AddHabitButton(onClick: () -> Unit) { // +새로운 습관 추가 버튼 ui 정의
    val stroke = Stroke(width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        // 점선 테두리: PathEffect.dashPathEffect -> 10px 그리고 10px 건너뛰는 패턴의 점선을 정의
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick) // 클릭 기능:  Row 전체가 클릭 가능한 버튼처럼 동작하도록 함
            // 함수 parameter : onClick -> 외부에서 showDialog = true 를 전달받았을 때 실행되도록 함
            .drawBehind {// 표준 border Modifier가 점선을 지원하지 않음 -> var stroke 정의하여 스타일 적용시킴
                drawRoundRect(
                    color = Color.LightGray,
                    style = stroke,
                    cornerRadius = CornerRadius(8.dp.toPx()) // 모서리 둥글게
                )
            }
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
        // 글자를 row 정가운데에 배치
    ) {
        Icon(Icons.Default.Add, contentDescription = null, tint = Color.Gray) // + 아이콘
        Spacer(modifier = Modifier.width(8.dp))
        Text("새로운 습관 추가하기", color = Color.Gray)
    }
}