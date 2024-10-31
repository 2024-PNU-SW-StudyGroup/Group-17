package com.project.namu.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.namu.R
import com.project.namu.component.BottomNav
import com.project.namu.component.SearchTopBar
import com.project.namu.ui.theme.BackGround

@Composable
fun Search_listScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            SearchTopBar(
                onSearch = { /* 검색 처리 로직 */ },
                additionalContent = { FilterButtonRow() },
                notificationVisible = false // 여기서 알림 아이콘 표시 여부를 결정
            )

        },

        bottomBar = {

            BottomNav(
                navController = navController,
                selectedIndex = selectedIndex,
                onItemSelected = { index ->
                    selectedIndex = index
                }
            )

        },

        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                // 메인 콘텐츠
                Search_listContent()
            }
        }
    )
}

@Composable
fun Search_listContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackGround)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),

    ){
        item { Spacer(modifier = Modifier.height(4.dp)) }

        item { CafeCardWithDetails() }

        item { CafeCardWithDetails() }

        item { CafeCardWithDetails() }

        item { CafeCardWithDetails() }

    }
}

@Composable
fun FilterButtonRow() {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth() // 너비를 꽉 채움
            .background(Color.White) // 배경색을 하얀색으로 설정
            .horizontalScroll(scrollState)
            .padding(start = 20.dp) // Row의 패딩 추가
            .padding(top = 12.dp, bottom = 20.dp)
    ) {
        SortButton()
        Spacer(modifier = Modifier.width(4.dp))
        FilterButton(image = R.drawable.filter_star, text = "별점")
        Spacer(modifier = Modifier.width(4.dp))
        FilterButton(image = R.drawable.filter_price, text = "가격대")
        Spacer(modifier = Modifier.width(4.dp))
        FilterButton(image = R.drawable.filter_time, text = "픽업시간대")
        Spacer(modifier = Modifier.width(4.dp))
        FilterButton(image = R.drawable.filter_location, text = "거리")

    }
}

@Composable
fun SortButton() {
    Row(
        modifier = Modifier
            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp)) // 테두리 추가
            .padding(start = 12.dp, end = 8.dp)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.filter_sort ),
            contentDescription = "기본순",
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        // 텍스트
        Text(
            text = "기본순",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.width(4.dp))

        // 드롭다운 아이콘
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Dropdown",
            tint = Color.Black,
            modifier = Modifier.size(14.dp)
        )
    }
}

@Composable
fun FilterButton(image: Int, text: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, // 좌우로 텍스트와 아이콘 배치
        modifier = Modifier
            .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp)) // 테두리 추가
            .padding(horizontal = 12.dp)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "text",
            modifier = Modifier.size(14.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(text = text, fontSize = 14.sp)
    }
}

@Composable
fun CafeCardWithDetails() {
    var isFavorite by remember { mutableStateOf(false) } // 좋아요 상태

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(165.dp) // 기본 높이 설정
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            // 왼쪽 이미지 섹션
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .background(Color.Gray)
            ) {
                // 좋아요 아이콘을 오른쪽 상단에 배치
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .clickable { isFavorite = !isFavorite }
                )
            }

            // 오른쪽 텍스트 섹션
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                // 가게 이름
                Text(
                    text = "카페인중독 부산대점",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 메뉴 정보
                Text(
                    text = "세트A (와플, 샌드위치)\n세트B (샌드위치, 휘낭시에)…",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 가격 정보
                Text(
                    text = "₩ 5,000 ~",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 평점, 시간, 거리 정보
                Column(
                    modifier = Modifier.fillMaxWidth()
                ){
                    // 평점 정보
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFD607),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "4.5 (100+)", fontSize = 12.sp, color = Color.Black)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row() {
                        // 시간 정보
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.card_clock),
                                contentDescription = "Time",
                                tint = Color(0xFF00BCD4),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "19:00 ~ 21:00", fontSize = 12.sp, color = Color.Gray)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        // 거리 정보
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.card_location),
                                contentDescription = "Location",
                                tint = Color(0xFF00BCD4),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "1.9km", fontSize = 12.sp, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Search_listPreview() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // 전체 배경색 설정
    ) {
        Search_listScreen(navController = navController)
    }
}