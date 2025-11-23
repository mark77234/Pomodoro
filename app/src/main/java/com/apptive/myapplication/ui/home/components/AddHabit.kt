package com.apptive.myapplication.ui.home.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

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
        title = { Text("새로운 습관 추가") },
        text = {
            TextField( //사용자가 새로운 습관의 내용을 직접 입력하는 공간
                value = text,
                onValueChange = { text = it },
                // onValueChange: 사용자가 키보드를 입력할 때마다 호출 -> text 상태 변수의 값을 최신 입력값으로 업데이트
                label = { Text("습관 내용") },
                singleLine = true
            )
        },
        confirmButton = {
            // 클릭 시 외부에서 전달받은 onAdd 람다 함수를 호출
            // 현재 TextField에 입력된 text 값을 파라미터로 넘겨줌
            // HomeTab에서는 이 값을 받아 habits 목록에 추가함
            Button(onClick = { onAdd(text) }) {
                Text("추가")
            }
        },
        dismissButton = {
            // 클릭 시 다이얼로그 닫기 요청과 동일하게 onDismiss 람다 함수를 호출하여 다이얼로그를 닫음.
            Button(onClick = onDismiss) {
                Text("취소")
            }
        }
    )
}
