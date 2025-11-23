package com.apptive.myapplication.ui.timer

import android.R.attr.elevation
import android.R.attr.shape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerTab(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFE5E7EB))
            .padding(16.dp)

    ) {
        Text(
            text = "뽀모도로 타이머",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = "타이머를 시작하세요",
            color = Color(0xFF6B7280)
        )
        Spacer(modifier = Modifier.padding(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(310.dp)
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp),

            ) {
            Column(

                modifier = Modifier
                    .fillMaxSize(),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "50:00",
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                Spacer(modifier = Modifier.height(4.dp)) // 글자 사이 간격
                Text(
                    text = "휴식 시간",
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280),
                )

            }


        }
        Spacer(modifier = Modifier.padding(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(12.dp)
                )
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 16.dp),


            ) {
            Column() {
                Text(
                    text = "타이머 모드",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,

                    )
                Spacer(modifier = Modifier.padding(5.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),

                    ) {
                    Button(
                        onClick = { /* 50/10 모드 선택 로직 */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFDC2626)
                        ),
                    )
                    {
                        Text(
                            text = "50/10",
                            color = Color.White,

                            )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Button(
                        onClick = { /* 30/5 모드 선택 로직 */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF3F4F6)
                        ),

                        ) {
                        Text(
                            text = "30/5",
                            color = Color.Black,

                            )
                    }

                }
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "50분 공부, 10분 휴식",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 14.sp,
                    color = Color(0xFF6B7280),
                    textAlign = TextAlign.Center
                )
            }







        }







    }

}