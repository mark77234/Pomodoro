package com.apptive.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apptive.myapplication.navigation.PodomoroDestination
import com.apptive.myapplication.navigation.PodomoroNavGraph

private data class BottomNavItem(
    val destination: PodomoroDestination,
    val icon: Int
)

private val bottomNavItems = listOf(
    BottomNavItem(PodomoroDestination.Home, R.drawable.home),
    BottomNavItem(PodomoroDestination.Timer, R.drawable.timer),
    BottomNavItem(PodomoroDestination.Stats, R.drawable.stat)
)

@OptIn(ExperimentalMaterial3Api::class)
@androidx.compose.runtime.Composable
fun PodomoroApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    bottomNavItems
        .firstOrNull { item ->
            currentDestination?.hierarchy?.any { it.route == item.destination.route } == true
        }
        ?.destination
        ?.title
        ?: PodomoroDestination.Home.title

    Scaffold(
        bottomBar = {
            Column {
                // ⭐ 네비바 상단 구분선 추가
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color(0xFFE0E0E0)
                )
                NavigationBar(
                    containerColor = Color.White,   // 원하는 색
                    tonalElevation = 0.dp
                ) {
                    bottomNavItems.forEach { item ->
                        val selected = currentDestination
                            ?.hierarchy
                            ?.any { destination -> destination.route == item.destination.route } == true

                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (!selected) {
                                    navController.navigate(item.destination.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = item.destination.title,
                                    modifier = Modifier.size(30.dp)

                                )
                            },
                            label = { Text(item.destination.title) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFFE57373),
                                selectedTextColor = Color(0xFFE57373),

                                unselectedIconColor = Color(0xFFC0C0C0),
                                unselectedTextColor = Color(0xFFC0C0C0),

                                indicatorColor = Color.Transparent  // 선택 시 배경색 없앰
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        PodomoroNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
