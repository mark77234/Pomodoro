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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.apptive.myapplication.ui.home.components.AddHabitButton
import com.apptive.myapplication.ui.home.components.AddHabit
import com.apptive.myapplication.ui.home.components.HabitList
import com.apptive.myapplication.viewmodel.MainViewModel

@Composable
fun HomeTab(modifier: Modifier = Modifier
, viewModel: MainViewModel = viewModel()
) {
    // 날짜를 현재 시간 기준으로 표현하기 위한 변수들
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDate = currentDate.format(formatter)

    // 다이얼로그(팝업창) 표시 여부를 관리하기 위한 상태
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
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
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(16.dp))
                // 테두리색 지정보다는 다른 페이지와의 통일감을 위해 그림자로 변경
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                //RoundedCornerShape: 모서리가 둥글게 하기 위함. size 숫자 클수록 더 둥글둥글해짐
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "오늘의 습관",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            viewModel.habits.forEach { habit ->
                HabitList(
                    habit = habit,
                    onHabitClick = {
                        viewModel.toggleHabit(habit)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 새로운 습관 추가 버튼
        AddHabitButton(onClick = { showDialog = true })

        // 새로운 습관을 추가하는 다이얼로그
        if (showDialog) {
            // '새로운 습관 추가하기' 버튼(AddHabitButton)을 누르면 onClick = { showDialog = true } 코드가 실행
            // -> AddHbit 함수 호출 -> '새로운 습관 추가' 다이얼로그의 모양과 기능을 정의
            AddHabit(
                onDismiss = { showDialog = false },
                //사용자가 다이얼로그 바깥쪽을 클릭하거나, '취소' 버튼을 누르면 onDismiss = { showDialog = false } 코드가 실행됨
                onAdd = { newHabitText ->
                    // onAdd는 사용자가 다이얼로그에서 '추가' 버튼을 눌렀을 때 무엇을 할 것인지 정의함
                    viewModel.addHabit(newHabitText)
                    showDialog = false
                    // 습관을 추가한 후, 다이얼로그를 닫기 위해 showDialog를 false로 변경
                }
            )
        }
    }
}

