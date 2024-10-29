package com.project.namu.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.namu.R

@Composable
fun SearchTopBar(onSearch: (String) -> Unit) {
    var searchText by remember { mutableStateOf("") } // 검색어 상태

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // 첫 번째 Row: 위치와 알림 아이콘
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.location), // 위치 아이콘
                    contentDescription = "Location Icon",
                    tint = Color(0xFF4CAF50)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "금정구 부산대학교로 63번길 2",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown Arrow",
                    tint = Color.Black
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.notification), // 알림 아이콘
                contentDescription = "Notification Icon",
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 두 번째 Row: 검색창
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(24.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.search), // 검색 아이콘
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                value = searchText,
                onValueChange = { newText -> searchText = newText },
                placeholder = { Text(text = "어떤 음식을 찾으시나요?", color = Color.Gray) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    // 검색 버튼을 눌렀을 때 또는 Enter 키를 눌렀을 때 검색어를 전달
    LaunchedEffect(searchText) {
        if (searchText.isNotEmpty()) {
            onSearch(searchText)
        }
    }
}
