package com.project.namu.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.namu.R
import com.project.namu.ui.theme.GrayLine

@Composable
fun SearchTopBar(onSearch: (String) -> Unit) {
    var searchText by remember { mutableStateOf("") } // 검색어 상태
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 20.dp)
                .padding(top = 32.dp, bottom = 20.dp)
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
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(24.dp) // 원하는 크기로 조정 (예: 24.dp)

                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "금정구 부산대학교로 63번길 2",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown Arrow",
                        tint = Color.Black,
                        modifier = Modifier.size(14.dp) // 원하는 크기로 조정 (예: 24.dp)
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.notification), // 알림 아이콘
                    contentDescription = "Notification Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp) // 원하는 크기로 조정 (예: 24.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 두 번째 Row: 검색창
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE8E8E8), shape = RoundedCornerShape(36.dp))
                    .padding(horizontal = 20.dp)
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search), // 검색 아이콘
                    contentDescription = "Search Icon",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp) // 원하는 크기로 조정
                )
                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp) // padding을 0으로 설정하여 내부 여백 제거
                ) {
                    BasicTextField(
                        value = searchText,
                        onValueChange = { newText ->
                            searchText = newText
                        },
                        singleLine = true,
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp
                        ),
                        modifier = Modifier.fillMaxSize()
                    )

                    // 플레이스홀더 텍스트 표시
                    if (searchText.isEmpty()) {
                        Text(
                            text = "어떤 음식을 찾으시나요?",
                            color = Color.Gray,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 8.dp) // 플레이스홀더 여백 조정
                        )
                    }
                }
            }
        }

        // 검색 버튼을 눌렀을 때 또는 Enter 키를 눌렀을 때 검색어를 전달
        LaunchedEffect(searchText) {
            if (searchText.isNotEmpty()) {
                onSearch(searchText)
            }
        }
        Divider(color = GrayLine, thickness = 2.dp)  // 하단에 회색 구분선 추가
    }

}

@Preview(showBackground = true)
@Composable
fun SearchTopBarPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // 전체 배경색 설정
    ) {
        SearchTopBar(onSearch = { /* 검색 처리 로직 */ })
    }
}
