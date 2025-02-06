package com.project.namu.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.project.namu.FavoriteManager
import com.project.namu.R
import com.project.namu.data.model.StoreData
import com.project.namu.ui.component.BottomNav
import com.project.namu.ui.component.SearchTopBar
import com.project.namu.ui.tools.PagerWithDotsIndicator
import com.project.namu.ui.theme.BackGround
import com.project.namu.ui.theme.Main100
import com.project.namu.ui.theme.Main200
import com.project.namu.ui.viewmodel.StoreUiState
import com.project.namu.ui.viewmodel.StoreViewModel
import androidx.compose.ui.platform.LocalContext  // 추가: Compose의 LocalContext 임포트
import com.project.namu.navigation.Screen


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

        // 장바구니 플로팅 버튼
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // 장바구니 페이지로 이동
                    navController.navigate("cart")
                },
                containerColor = Color.White,
                contentColor = Color.Black

            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "장바구니",
                    Modifier.size(32.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ){ paddingValues ->
        // 메인 콘텐츠
        Box(modifier = Modifier.padding(paddingValues)) {
            HomeContent(navController = navController)
        }
    }
}

@Composable
fun HomeContent(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackGround),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            PagerWithDotsIndicator(
                indicatorColor = Main200,
                pageCount = 5,
                pageContent = { page ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        // 예시: page 값에 따라 다른 이미지를 표시하거나, 동일한 이미지를 사용
                        Image(
                            painter = painterResource(id = R.drawable.homebener),
                            contentDescription = "Pager image for page $page",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            )

        }
        item { Spacer(modifier = Modifier.height(4.dp)) }
        item { FoodCategoryRow() }
        item { Spacer(modifier = Modifier.height(4.dp)) }

        // 가게 정보를 백엔드에서 읽어와 가로 리스트로 표시 (예: "이런 가게는 어때요?")
        item { HorizontalStoreList(title = "이런 가게는 어때요?", navController = navController) }
        // 또 다른 가로 리스트 예: "지금 근처 픽업 가능한 곳"
        item { HorizontalStoreList(title = "지금 근처 픽업 가능한 곳", navController = navController) }
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
                "마트" to R.drawable.mart, "디저트" to R.drawable.dessert, "기타" to R.drawable.more
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
            Text(text = name, fontSize = 10.sp, color = Color.Black, fontWeight = Medium)

        }
    }
}

// 더보기와 카드의 horizontal Row
@Composable
fun HorizontalStoreList(title: String, navController: NavController) {
    // StoreViewModel을 Hilt로 가져오기 (여기서 LazyColumn과 독립적으로 사용 가능)
    val storeViewModel: StoreViewModel = hiltViewModel()
    val uiState by storeViewModel.uiState

    Column() {
        // 제목 및 "더보기" Row
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = Bold,
                color = Color.Black
            )
            Row(
                modifier = Modifier.clickable {
                    navController.navigate(Screen.Search.route)
                }
            ) {
                Text(text = "+ 더보기", fontSize = 16.sp, color = Main100)
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        // 가게 데이터를 가로 리스트(LazyRow)로 표시
        when (uiState) {
            is StoreUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is StoreUiState.Success -> {
                val stores = (uiState as StoreUiState.Success).data
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(stores) { store ->
                        CafeCard(storeData = store, navController = navController)
                    }
                }
            }
            is StoreUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "데이터 로딩 오류", color = Color.Red)
                }
            }
        }
    }
}

@Composable
fun CafeCard(storeData: StoreData, navController: NavController) {
    val context = LocalContext.current  // 현재 Context를 가져옴
    var isFavorite by remember { mutableStateOf(false) }  // var 로 선언하여 상태 변경 가능하도록 함

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .width(280.dp)
            .padding(vertical = 16.dp)
            .clickable { navController.navigate("가게상세/${storeData.storeId}") }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.Gray)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        storeData.storePictureUrls.firstOrNull() ?: ""
                    ),
                    contentDescription = "Store Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // 좋아요 아이콘을 오른쪽 상단에 배치
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .clickable {
                            isFavorite = !isFavorite
                            FavoriteManager.toggleFavorite(context, storeData.storeId.toString())
                        }
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
                    text = "${storeData.storeName}",
                    fontSize = 14.sp,
                    fontWeight = Bold,
                    color = Color.Black
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFD607),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${storeData.storeRating}", fontSize = 12.sp, color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // 시간과 거리 정보
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = "Time",
                    tint = Color(0xFF00BCD4),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "${storeData.pickupTimes}", fontSize = 12.sp, color = Color.Gray)

                Spacer(modifier = Modifier.width(16.dp))

                Icon(
                    painter = painterResource(id = R.drawable.mappin),
                    contentDescription = "Location",
                    tint = Color(0xFF00BCD4),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "${storeData.location / 1000.0} km", fontSize = 12.sp, color = Color.Gray)
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