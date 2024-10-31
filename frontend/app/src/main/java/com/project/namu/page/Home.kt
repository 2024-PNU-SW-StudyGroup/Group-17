package com.project.namu.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.namu.R
import com.project.namu.component.BottomNav
import com.project.namu.component.SearchTopBar
import com.project.namu.tools.PagerWithDotsIndicator
import com.project.namu.ui.theme.BackGround
import com.project.namu.ui.theme.Main100
import com.project.namu.ui.theme.Main200

@Composable
fun HomeScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            SearchTopBar(
                onSearch = {},
                additionalContent = {
                    Spacer(modifier = Modifier.height(20.dp)) // 원하는 높이로 Spacer 추가
                },
                notificationVisible = true // 여기서 알림 아이콘 표시 여부를 결정
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
                HomeContent()
            }
        }
    )
}

@Composable
fun HomeContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackGround),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            PagerWithDotsIndicator(
                pageCount = 5,
                pageContent = { page ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(color = Main200)
                    ) {
                    }
                }
            )
        }

        item { Spacer(modifier = Modifier.height(4.dp)) }

        item{ FoodCategoryRow() }

        item { Spacer(modifier = Modifier.height(4.dp)) }

        item{ HorizantalCard("이런 가게는 어때요?") }

        item{ HorizantalCard("지금 근처 픽업 가능한 곳") }


    }
}

// 카테고리 카드 Row
@Composable
fun FoodCategoryRow() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center // Box 내에서 Row를 중앙 정렬
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(), // Row의 너비를 내용에 맞춤
            horizontalArrangement = Arrangement.spacedBy(6.dp) // Card 사이 간격 설정
        ) {
            val categories = listOf(
                "샌드위치" to R.drawable.sandwitch, "과일" to R.drawable.apple,
                "편의점,마트" to R.drawable.market, "디저트" to R.drawable.dessert, "기타" to R.drawable.more
            )

            categories.forEach { (name, icon) ->
                CategoryCard(name, icon)
            }
        }
    }
}

// 카테고리 카드 하나
@Composable
fun CategoryCard(name: String, icon: Int) {
    Card(
        modifier = Modifier
            .width(66.dp) // 카드의 너비 설정
            .height(76.dp) // 카드의 높이 설정
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)), // 테두리 추가
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = name, fontSize = 11.sp, color = Color.Black)

        }
    }
}

// 더보기와 카드의 horizontal Row
@Composable
fun HorizantalCard(text : String) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.padding(start = 20.dp)
    ) {
        // 이런 가게는 어때요 + 더보기 row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = text, fontSize = 20.sp, fontWeight = Bold, color = Color.Black)

            Text(text = "+ 더보기", fontSize = 16.sp, color = Main100)
        }

        Row(
            modifier = Modifier
                .horizontalScroll(scrollState) // Row가 좌우로 스크롤 가능하도록 설정

        ) {
            CafeCard()

            Spacer(modifier = Modifier.width(12.dp))

            CafeCard()

            Spacer(modifier = Modifier.width(12.dp))

            CafeCard()

        }


    }
}

@Composable
fun CafeCard(){
    val isFavorite by remember { mutableStateOf(false) } // 좋아요 상태를 저장

    //카페 카드
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .width(232.dp)
            .padding(vertical = 16.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Gray)
            ) {
                // 좋아요 아이콘을 오른쪽 상단에 배치
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 가게 이름과 평점
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "카페인중독 부산대점",
                    fontSize = 14.sp,
                    fontWeight = Bold,
                    color = Color.Black
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star, // 별 아이콘 리소스
                        contentDescription = "Rating",
                        tint = Color(0xFFFFD607),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "4.5", fontSize = 12.sp, color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 시간과 거리 정보
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp,),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.card_clock),
                    contentDescription = "Time",
                    tint = Color(0xFF00BCD4),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "19:00 ~ 21:00", fontSize = 12.sp, color = Color.Gray)

                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    painter = painterResource(id = R.drawable.card_location),
                    contentDescription = "Location",
                    tint = Color(0xFF00BCD4),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "1.9km", fontSize = 12.sp, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // 전체 배경색 설정
    ) {
        HomeScreen(navController = navController)
    }
}
