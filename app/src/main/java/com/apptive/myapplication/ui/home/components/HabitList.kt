package com.apptive.myapplication.ui.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apptive.myapplication.ui.home.Habit

@Composable
fun HabitList(
    habit: Habit, // String 대신 Habit 객체를 받음
    onHabitClick: () -> Unit // 클릭 이벤트를 상위로 전달
) { // 하나의 습관 항목을 화면에 어떻게 나타낼지 정의
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onHabitClick) // Row 전체에 클릭 이벤트 적용
            .border(
                width = 1.dp, //테두리 두께
                color = Color.LightGray,
                shape = RoundedCornerShape(12.dp)
            )
            .height(58.dp)
            .padding(horizontal = 12.dp, vertical = 8.dp), // 테두리 안 여백 정의 : 좌우 12.dp, 위아래: 8.dp
        verticalAlignment = Alignment.CenterVertically // contents를 수직 방향으로 가운데 정렬
    ) {
        // isDone 상태에 따라 아이콘과 색상을 변경
        val icon = if (habit.isDone) Icons.Filled.CheckCircle else Icons.Default.RadioButtonUnchecked
        val iconColor = if (habit.isDone) Color.Red else Color.LightGray
        Icon(
            imageVector = icon,
            contentDescription = "Habit status",
            tint = iconColor
        )

        Spacer(modifier = Modifier.width(12.dp)) //아이콘과 텍스트 사이에 12.dp만큼의 가로 간격

        //isDone 상태에 따라 텍스트 색상과 취소선을 변경
        val textColor = if (habit.isDone) Color.Gray else Color.Black
        val textDecoration = if (habit.isDone) TextDecoration.LineThrough else TextDecoration.None
        Text(
            text = habit.text,
            fontSize = 12.sp,
            color = textColor,
            textDecoration = textDecoration
        )
    }
}