package com.apptive.myapplication.ui.home.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AddHabit(onDismiss: () -> Unit, onAdd: (String) -> Unit) {
    // 내용을 입력받는 팝업창 정의

    var text by remember { mutableStateOf("") }
    // remember { mutableStateOf("") }를 통해 사용자가 TextField에 입력하는 텍스트를 저장
    // 내용이 변경될 때마다 화면을 갱신할 상태 변수 text 생성

    AlertDialog(
        onDismissRequest = onDismiss,
        // 다이얼로그 닫기 요청 처리: 사용자가 다이얼로그 바깥 영역을 클릭했을 때 호출
        // ->> 외부에서 showDialog = false를 전달받았을 때
        containerColor = Color.White, // 모달 배경색
        title = { Text("새로운 습관 추가", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Gray)},
        text = {
            TextField( //사용자가 새로운 습관의 내용을 직접 입력하는 공간
                value = text,
                onValueChange = { text = it },
                // onValueChange: 사용자가 키보드를 입력할 때마다 호출 -> text 상태 변수의 값을 최신 입력값으로 업데이트
                label = { Text("습관 내용") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color(0xFFDC2626),
                    // 사용자가 TextField를 터치하여 키보드가 활성화되었을 때, 하단 밑줄의 색상
                    unfocusedIndicatorColor = Color.LightGray,
                    // TextField가 비활성화 상태일 때, 하단 밑줄의 색상
                    focusedContainerColor = Color.White,
                    // 사용자가 TextField를 터치하여 키보드가 활성화되었을 때, 배경 채우기의 색상
                    unfocusedContainerColor = Color.White,
                    // TextField가 비활성화 상태일 때, 배경 채우기의 색상
                    focusedLabelColor = Color(0xFFDC2626),
                    // 사용자가 TextField를 터치하여 키보드가 활성화되었을 때, label(습관 내용)의 색상
                    unfocusedLabelColor = Color.Gray
                    // TextField가 비활성화 상태일 때, label(습관 내용)의 색상
                )
            )
        },
        confirmButton = {
            // 클릭 시 외부에서 전달받은 onAdd 람다 함수를 호출
            // 현재 TextField에 입력된 text 값을 파라미터로 넘겨줌
            // HomeTab에서는 이 값을 받아 habits 목록에 추가함
            Button(onClick = { onAdd(text) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626))
            ) {
                Text("추가")
            }
        },
        dismissButton = {
            // 클릭 시 다이얼로그 닫기 요청과 동일하게 onDismiss 람다 함수를 호출하여 다이얼로그를 닫음.
            Button(onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            ) {
                Text("취소")
            }
        }
    )
}
