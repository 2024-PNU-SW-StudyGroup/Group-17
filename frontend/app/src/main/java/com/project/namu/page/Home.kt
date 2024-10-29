package com.project.namu.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.namu.component.BottomNav
import com.project.namu.ui.theme.GrayLine

@Composable
fun HomeScreen(navController: NavController){
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {

        },

        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp) // 높이 설정
                    .background(Color.White) // 배경색 설정
            ) {
                Column {
                    // 상단에 Gray 색상의 선을 추가
                    Divider(
                        color = GrayLine,
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    // 하단 아이콘들 배치
                    BottomNav(
                        navController = navController,
                        selectedIndex = selectedIndex,
                        onItemSelected = { index ->
                            selectedIndex = index
                        }
                    )
                }
            }
        },

        content = {paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                // 메인 콘텐츠
                HomeContent()
            }
        }
    )
}

@Composable
fun HomeContent(){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {



    }
}
