package com.project.namu.ui.page


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.namu.R
import com.project.namu.ui.component.BottomNav
import com.project.namu.ui.theme.BackGround

@Composable
fun Bookmark(navController: NavController){
    var selectedIndex by remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .background(color = BackGround),
                contentAlignment = Alignment.Center
            ){
                Text(
                    "찜목록",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                    )
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "장바구니",
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        content = {paddingValues ->
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(color = BackGround)
            ) {
                item {
                    Text(
                        text = "지금 판매하고 있어요.",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF353535),
                        )
                    )
                }

                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
            }

        }


        ,
        bottomBar = {
            BottomNav(
                navController = navController,
                selectedIndex = selectedIndex,
                onItemSelected = { index ->
                    selectedIndex = index
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkItem() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center // Row의 중앙 정렬
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp) // 카드에 직접 padding을 적용
                .border(
                    width = 1.dp,
                    color = Color(0xFFE7E7E7),
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .width(320.dp)
                .height(100.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFFFFFF)
            ),
            shape = RoundedCornerShape(12.dp),
            onClick = {}, // 클릭 이벤트 정의
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.examplefood),
                    contentDescription = "예시 사진",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                )

                Spacer(modifier = Modifier.width(13.dp))

                Column {
                    Spacer(modifier = Modifier.height(13.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 14.dp)
                    ) {
                        Text(
                            text = "카페인중독 부산대점",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF353535),
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        var isHeartFilled by remember { mutableStateOf(true) }
                        val heartIcon = if (isHeartFilled) {
                            Icons.Default.Favorite
                        } else {
                            Icons.Default.FavoriteBorder
                        }

                        Icon(
                            imageVector = heartIcon,
                            contentDescription = "하트 아이콘",
                            tint = Color.Red, // 아이콘 색상 설정
                            modifier = Modifier
                                .size(24.dp) // 아이콘 크기 설정
                                .clickable { isHeartFilled = !isHeartFilled } // 클릭 시 상태 반전
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "와플세트A  (주인장 추천 초코 와플세트)",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF8B8B8B),
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.time),
                            contentDescription = "영업 시간",
                            modifier = Modifier
                                .size(8.dp)
                                .align(Alignment.CenterVertically)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "19:00 ~ 21:00",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF353535),
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Image(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "위치",
                            modifier = Modifier
                                .size(8.dp)
                                .align(Alignment.CenterVertically)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "1.9km",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF353535),
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFD607),
                            modifier = Modifier.size(8.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "4.5",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF353535),
                            )
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "(100+)",
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF353535),
                            )
                        )
                    }
                }
            }
        }
    }
}







@Preview(showBackground = true)
@Composable
fun BookmarkPreview() {
    val navController = rememberNavController()
    Bookmark(navController = navController)
}