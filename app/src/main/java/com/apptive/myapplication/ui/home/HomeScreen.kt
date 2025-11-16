package com.apptive.myapplication.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun HomeTab(modifier: Modifier = Modifier) {
    Column (modifier = modifier
        .fillMaxSize()

    ) {
            Text(
                text = "스터디 해빗 트래커",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier= Modifier.height(8.dp))
            Text(
                text = "2025-10-20",
                color = Color.Gray,
                fontSize = 16.sp
            )


//            Column(modifier = Modifier.fillMaxHeight(),
//                horizontalAlignment = Arrangement.Center
//            ) {
//                //home icon
//                Icon(
//                    painter = painterResource(id = com.apptive.)
//                )
//            }

    }
}
