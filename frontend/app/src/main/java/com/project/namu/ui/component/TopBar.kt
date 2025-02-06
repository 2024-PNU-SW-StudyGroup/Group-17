package com.project.namu.ui.component


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.namu.R
import com.project.namu.model.AuthInterceptor
import com.project.namu.model.AuthRepository
import com.project.namu.model.SearchViewModel
import com.project.namu.ui.theme.GrayLine

@Composable
fun SearchTopBar(
    searchViewModel : SearchViewModel,
    additionalContent: @Composable (() -> Unit)? = null, // 동적으로 추가될 콘텐츠
    notificationVisible: Boolean = true, // 알림 아이콘 표시 여부를 결정하는 매개변수
    navController: NavController
) {
    val query by searchViewModel.query.collectAsState()

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 20.dp)
                .padding(top = 32.dp)
        ) {
            // 첫 번째 Row: 위치와 알림 아이콘
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.map), // 위치 아이콘
                        contentDescription = "Map Icon",
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
                /*
                if (notificationVisible) {
                    Icon(
                        painter = painterResource(id = R.drawable.map), // 알림 아이콘
                        contentDescription = "Notification Icon",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp) // 원하는 크기로 조정 (예: 24.dp)
                    )
                }
                */

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
                    modifier = Modifier
                        .size(20.dp) // 원하는 크기로 조정
                        .clickable { searchViewModel.searchStores(navController) } //클릭시
                )
                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp) // padding을 0으로 설정하여 내부 여백 제거
                ) {
                    BasicTextField(
                        value = query,
                        onValueChange = {
                            searchViewModel.onQueryChange(it)
                        },
                        singleLine = true,
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(19.dp)
                    )

                    // 플레이스홀더 텍스트 표시
                    if (query.isEmpty()) {
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


        // 추가 콘텐츠 삽입
        additionalContent?.let {
            it() // 추가 콘텐츠가 있으면 렌더링
        }

        Divider(color = GrayLine, thickness = 2.dp)  // 하단에 회색 구분선 추가
    }

}


@Preview(showBackground = true)
@Composable
fun SearchTopBarPreview() {

    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // 전체 배경색 설정
    ) {
        SearchTopBar(
            SearchViewModel(
                authRepository = AuthRepository(context),
                authInterceptor = AuthInterceptor(authRepository = AuthRepository(context)),
            ),
            navController = rememberNavController()
            )
    }
}

