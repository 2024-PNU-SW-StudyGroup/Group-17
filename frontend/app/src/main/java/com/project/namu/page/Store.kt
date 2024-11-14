package com.project.namu.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.project.namu.tools.PagerWithDotsIndicator
import com.project.namu.ui.theme.BackGround
import com.project.namu.ui.theme.Ui_empty
import com.project.namu.R


@Composable
fun StoreScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = { },

        bottomBar = {
        },

        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                // 메인 콘텐츠
                StoreContent()
            }
        }
    )
}

@Composable
fun StoreContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackGround),

        ){

        item { Store_Pager() }

        item { Store_Detail() }

        item { Store_MenuDetail() }

        item {  }

    }
}

@Composable
fun Store_Pager(){
    var isFavorite by remember { mutableStateOf(false) } // 좋아요 상태

    Box {
        PagerWithDotsIndicator(
            indicatorColor = Color.White,
            pageCount = 5,
            pageContent = { page ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .background(color = Ui_empty)
                ) {
                }
            }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
                ,
                horizontalArrangement = Arrangement.End
            ){
                Icon(painter = painterResource(id = R.drawable.ic_shopping),
                    contentDescription = "장바구니",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(28.dp)
                    )
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { isFavorite = !isFavorite }
                        .size(28.dp)
                )
            }
        }
    }
}

@Composable
fun Store_Detail(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "카페인중독 부산대점",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(80.dp))
                Row(

                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFD607),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "4.5", fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.Medium,
                    )
                }

            }
            Spacer(modifier = Modifier.height(20.dp))

            // 가게 전화번호
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.card_clock),
                    contentDescription = "phonenumber",
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "0507-2093-2266", fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Normal,)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // 가게 영업시간
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.card_clock),
                    contentDescription = "영업시간",
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "19:00 ~ 21:00", fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Normal,)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // 가게 위치
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.card_location),
                    contentDescription = "위치",
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "부산 금정구 금정로 83 1층", fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Normal,)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(20.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE7E7E7),
                        shape = RoundedCornerShape(size = 30.dp)
                    )
                    .width(100.dp)
                    .height(32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,  // 변경된 매개변수 이름
                    contentColor = Color.Black
                ),
                contentPadding = PaddingValues(0.dp)  // Added to remove default padding

            ) {
                Text(text = "리뷰 100+개")
            }
        }
    }
}

@Composable
fun Store_MenuDetail() {

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
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
fun StorePreview() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // 전체 배경색 설정
    ) {
        StoreScreen(navController = navController)
    }
}

