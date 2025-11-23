package com.apptive.myapplication.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//code refactoring
import com.apptive.myapplication.ui.home.components.AddHabitButton
import com.apptive.myapplication.ui.home.components.AddHabit
import com.apptive.myapplication.ui.home.components.HabitList
//data class를 model/Habit로 이동함
import com.apptive.myapplication.model.Habit

@Composable
fun HomeTab(modifier: Modifier = Modifier) {
    // 날짜를 현재 시간 기준으로 표현하기 위한 변수들
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDate = currentDate.format(formatter)

    // 습관 목록을 Habit 객체로 관리하도록 수정
    val habits = remember { mutableStateListOf<Habit>() }
    // 다이얼로그(팝업창) 표시 여부를 관리하기 위한 상태
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "스터디 해빗 트래커",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = formattedDate,
            //현재 시간 기준 날짜로 표현
            color = Color.Gray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 오늘의 습관
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(16.dp)) //모서리가 둥글게 하기 위함
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "오늘의 습관",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            //상태 리스트를 기반으로 HabitList를 동적으로 생성하고, 클릭 이벤트 처리
            // '동적' 이란 말의 의미: 고정되지 않음
            habits.forEachIndexed { index, habit ->
                HabitList(
                    habit = habit,
                    onHabitClick = {
                        // 클릭된 항목의 isDone 상태를 반전시킴
                        habits[index] = habit.copy(isDone = !habit.isDone)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 새로운 습관 추가 버튼
        AddHabitButton(onClick = { showDialog = true })

        // 새로운 습관을 추가하는 다이얼로그를 언제, 어떻게 보여줄지를 결정
        // *참고 : 다이얼로그(Dialog)는 현재 화면 위에 작게 나타나는 별도의 창(팝업창)을 의미
        if (showDialog) {
            // '새로운 습관 추가하기' 버튼(AddHabitButton)을 누르면 onClick = { showDialog = true } 코드가 실행
            // -> AddHbit 함수 호출 -> '새로운 습관 추가' 다이얼로그의 모양과 기능을 정의
            AddHabit(
                onDismiss = { showDialog = false },
                //사용자가 다이얼로그 바깥쪽을 클릭하거나, '취소' 버튼을 누르면 onDismiss = { showDialog = false } 코드가 실행됨
                onAdd = { newHabitText ->
                    // onAdd는 사용자가 다이얼로그에서 '추가' 버튼을 눌렀을 때 무엇을 할 것인지 정의함
                    if (newHabitText.isNotBlank()) { //입력된 내용이 비어있지 않은지 확인
                        habits.add(Habit(newHabitText)) // 4. Habit 객체로 리스트에 추가
                    }
                    showDialog = false
                    // 습관을 추가한 후, 다이얼로그를 닫기 위해 showDialog를 false로 변경
                }
            )
        }
    }
}

