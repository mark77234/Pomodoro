package com.apptive.myapplication.model

import androidx.compose.ui.graphics.Color

enum class HabitStatus(val displayText: String, val bgColor: Color, val textColor: Color) {
    NOT_COMPLETED("미완료", Color(0xFFEEEEEE), Color.DarkGray),
    IN_PROGRESS("진행중", Color(0xFF87CEFA), Color(0xFFE0F7FA)),
    COMPLETED("완료", Color(0xFFF87171), Color(0xFFE8F5E9));
}
