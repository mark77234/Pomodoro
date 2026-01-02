package com.apptive.myapplication.ui.timer

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apptive.myapplication.ui.timer.components.ModeSelector
import com.apptive.myapplication.ui.timer.components.TimerControls
import com.apptive.myapplication.ui.timer.components.TimerDisplay
import kotlinx.coroutines.delay

@Composable
fun TimerTab() {
    val scrollState = rememberScrollState()

    var is50minMode by remember { mutableStateOf(true) }
    var isStarted by remember { mutableStateOf(false) }
    var isWorking by remember { mutableStateOf(true) } // 공부중/휴식중
    var timeLeft by remember { mutableStateOf(50 * 60) } // 초 단위

    LaunchedEffect(key1 = isStarted, key2 = timeLeft) {
        if (isStarted && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        } else if (isStarted && timeLeft == 0) {
            // 시간이 다 되면 자동으로 모드 전환! (50->10 또는 30->5)
            if (isWorking) {
                isWorking = false
                timeLeft = if (is50minMode) 10 * 60 else 5 * 60
            } else {
                isWorking = true
                timeLeft = if (is50minMode) 50 * 60 else 30 * 60
                isStarted = false // 한 사이클 끝나면 일단 정지
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
                // 시간 게이지 계산
                val totalTime = if (is50minMode) {
                    if (isWorking) 50 * 60f else 10 * 60f
                } else {
                    if (isWorking) 30 * 60f else 5 * 60f
                }
                val progress = (totalTime - timeLeft) / totalTime
                TimerDisplay(
                    isWorking = isWorking,
                    timeLeft = formatTime(timeLeft),
                    progress = progress
                )
                Spacer(modifier = Modifier.height(24.dp))

                TimerControls(
                    isStarted = isStarted,
                    onToggleTimer = {
                        if (!isStarted && timeLeft > 0) {
                            timeLeft--
                        }
                        isStarted = !isStarted
                                    },
                    onReset = { isStarted = false; isWorking = true; timeLeft = if (is50minMode) 50 * 60 else 30 * 60 }
                )

                }

            Spacer(modifier = Modifier.height(20.dp))

            ModeSelector(is50minMode) { selected50 ->
                is50minMode = selected50
                isWorking = true
                isStarted = false
                timeLeft = if (selected50) 50 * 60 else 30 * 60
            }
    }
// 스크롤 시 하단 여백 확보
    Spacer(modifier = Modifier.height(40.dp))

        }




