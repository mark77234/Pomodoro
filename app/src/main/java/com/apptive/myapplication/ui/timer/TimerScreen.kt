package com.apptive.myapplication.ui.timer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun TimerTab() {
    var is50minMode by remember { mutableStateOf(true) }
    var isstarted by remember { mutableStateOf(false) }
    var isWorking by remember { mutableStateOf(true) }
    var timeLeft by remember { mutableStateOf(50 * 60) }

    // 스크롤 상태 정의
    val scrollState = rememberScrollState()

    // 타이머
    LaunchedEffect(key1 = isstarted, key2 = timeLeft) {
        if (isstarted && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        } else if (isstarted && timeLeft == 0) {
            if (isWorking) {
                isWorking = false
                timeLeft = if (is50minMode) 10 * 60 else 5 * 60
            } else {
                isWorking = true
                timeLeft = if (is50minMode) 50 * 60 else 30 * 60
                isstarted = false
            }
        }
    }

    fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return "%02d:%02d".format(minutes, remainingSeconds)
    }

    // 메인 레이아웃
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFE5E7EB))
            .verticalScroll(scrollState) // 스크롤 가능하게 설정
            .padding(16.dp)
    ) {
        Text(text = "뽀모도로 타이머", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "타이머를 시작하세요", color = Color(0xFF6B7280))
        Spacer(modifier = Modifier.height(20.dp))

        // --- 상단 타이머 박스 ---
        Column( // 전체를 감싸는 Column 추가
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(12.dp))
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(vertical = 30.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(240.dp)
            ) {
                // 시간 게이지 계산
                val totalTime = if (is50minMode) {
                    if (isWorking) 50 * 60f else 10 * 60f
                } else {
                    if (isWorking) 30 * 60f else 5 * 60f
                }
                val progress = (totalTime - timeLeft) / totalTime

                // 시간 게이지 -> 원형 테두리 게이지 표시
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color(0xFFF3F4F6),
                        style = Stroke(width = 14.dp.toPx())
                    )
                    drawArc(
                        color = Color(0xFFDC2626),
                        startAngle = -90f,
                        sweepAngle = 360f * progress,
                        useCenter = false,
                        style = Stroke(width = 14.dp.toPx(), cap = StrokeCap.Round)
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (isWorking) "집중 시간" else "휴식 시간",
                        color = if (isWorking) Color(0xFF333333) else Color(0xFF333333),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = formatTime(timeLeft),
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { isstarted = !isstarted },
                    modifier = Modifier
                        .height(48.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isstarted) Color(0xFFE5E7EB) else Color(0xFFDC2626)
                    )
                ) {
                    Text(
                        if (isstarted) "일시정지" else "시작",
                        color = if (isstarted) Color.Black else Color.White
                    )
                }

                Button(
                    onClick = {
                        isstarted = false
                        isWorking = true
                        timeLeft = if (is50minMode) 50 * 60 else 30 * 60
                    },
                    modifier = Modifier
                        .height(48.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF3F4F6))
                ) {
                    Text("초기화", color = Color.Black)
                }
            }
        }


        Spacer(modifier = Modifier.height(20.dp))

        // --- 하단 타이머 모드 선택 영역 ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(12.dp))
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text(text = "타이머 모드", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = {
                        is50minMode = true
                        isWorking = true
                        isstarted = false
                        timeLeft = 50 * 60
                    },
                    modifier = Modifier.weight(1f).height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (is50minMode) Color(0xFFDC2626) else Color(0xFFF3F4F6)
                    )
                ) {
                    Text("50/10", color = if (is50minMode) Color.White else Color.Black)
                }

                Button(
                    onClick = {
                        is50minMode = false
                        isWorking = true
                        isstarted = false
                        timeLeft = 30 * 60
                    },
                    modifier = Modifier.weight(1f).height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (!is50minMode) Color(0xFFDC2626) else Color(0xFFF3F4F6)
                    )
                ) {
                    Text("30/5", color = if (!is50minMode) Color.White else Color.Black)
                }
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

        // 스크롤 시 하단 여백 확보
        Spacer(modifier = Modifier.height(40.dp))
    }
}
