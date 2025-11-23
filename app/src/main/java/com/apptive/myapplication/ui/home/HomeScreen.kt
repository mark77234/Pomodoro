package com.apptive.myapplication.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// 습관의 내용과 완료 상태를 저장할 데이터 클래스 정의
data class Habit(val text: String, var isDone: Boolean = false)

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
                shape = RoundedCornerShape(8.dp)
            )
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

@Composable
fun AddHabit(onDismiss: () -> Unit, onAdd: (String) -> Unit) {
    // 내용을 입력받는 팝업창(다이얼로그_ 정의

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
