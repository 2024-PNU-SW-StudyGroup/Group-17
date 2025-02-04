package com.project.namu.ui.page

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.project.namu.R
import com.project.namu.data.model.DetailMenu
import com.project.namu.data.model.StoreDetailData
import com.project.namu.ui.component.Store_SwitchBottomBar
import com.project.namu.ui.theme.BackGround
import com.project.namu.ui.tools.PagerWithDotsIndicator
import com.project.namu.ui.viewmodel.StoreDetailUiState
import com.project.namu.ui.viewmodel.StoreDetailViewModel

@Composable
fun StoreScreen(
    navController: NavController,
    storeId: Int,
    viewModel: StoreDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(storeId) {
        if (storeId == 0) {
            Log.e("DEBUG", "Warning: storeId is 0 — check your data.")
        } else {
            viewModel.getStoreDetail(storeId)
        }
    }

    Scaffold(
        topBar = { /* ... */ },
        bottomBar = { Store_SwitchBottomBar(isAvailable = true) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (uiState) {
                is StoreDetailUiState.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is StoreDetailUiState.Success -> {
                    val storeDetailData = (uiState as StoreDetailUiState.Success).data
                    StoreContent(storeDetailData, navController)  // ✅ navController 전달
                }
                is StoreDetailUiState.Error -> {
                    val message = (uiState as StoreDetailUiState.Error).message
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "에러: $message", color = Color.Red)
                    }
                }
            }
        }
    }
}

@Composable
fun StoreContent(storeDetailData: StoreDetailData, navController: NavController) { // ✅ navController 추가
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BackGround)
    ) {
        // 1) 사진/이미지 Pager
        item {
            val images = storeDetailData.storePictureUrls ?: emptyList()
            Store_Pager(imageUrls = images)
        }

        // 2) 가게 기본 정보 표시
        item {
            Store_Detail(
                storeName = storeDetailData.storeName,
                storePhone = storeDetailData.storePhone,
                pickupTimes = storeDetailData.pickupTimes,
                storeAddress = storeDetailData.storeAddress,
                storeRating = storeDetailData.storeRating,
                reviewCount = storeDetailData.reviewCount
            )
        }

        // 3) 실제 메뉴 목록 (DetailMenu)
        items(storeDetailData.menus) { menuItem ->
            Store_MenuDetail(menuData = menuItem, navController = navController) // ✅ navController 전달
        }
        
        item { Spacer(modifier = Modifier.height(12.dp)) }
    }
}

@Composable
fun Store_Pager(imageUrls: List<String>) {
    var isFavorite by remember { mutableStateOf(false) }

    Box {
        // ✅ 이미지 슬라이더/페이저
        PagerWithDotsIndicator(
            indicatorColor = Color.White,
            pageCount = imageUrls.size
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp) // 높이 설정
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imageUrls[page]),
                    contentDescription = "가게 사진",
                    modifier = Modifier.fillMaxSize(), // ✅ Box 내부를 꽉 채움
                    contentScale = ContentScale.Crop // ✅ 크롭하여 꽉 차게 표시
                )
            }
        }

        // 좋아요 & 장바구니 아이콘
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
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

@Composable
fun Store_Detail(
    storeName: String,
    storePhone: String?,    // null 가능성 대비
    pickupTimes: String,
    storeAddress: String?,
    storeRating: Float,     // data class에 맞춤
    reviewCount: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = storeName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(80.dp))

                // 별점 표시
                Row {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFD607),
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = storeRating.toString(),
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

             Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.phone),
                        contentDescription = "phonenumber",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = storePhone ?: "전화번호 없음",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                    )
                }


            Spacer(modifier = Modifier.height(4.dp))

            // 영업시간
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = "open-time",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                // null이면 "정보 없음" 식으로 처리할 수도 있음
                Text(
                    text = pickupTimes,
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // 위치
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.mappin),
                    contentDescription = "location",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = storeAddress.toString(),         // Int → 문자열 변환
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                )
            }
        }

        // 리뷰 N개 버튼
    }
}

@Composable
fun Store_MenuDetail(menuData: DetailMenu, navController: NavController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp)
            .height(180.dp)
            .clickable {
                navController.navigate("menu_detail/${menuData.menuId}") // ✅ menuId로 변경
            }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // 이미지 영역
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(menuData.menuPictureUrl),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // 텍스트 정보 영역
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .padding(start = 12.dp)

            ) {
                // 세트 이름
                Text(
                    text = menuData.setName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))

                // 메뉴 구성
                Text(
                    text = menuData.menuNames,
                    fontSize = 12.sp,
                    color = Color.Black,
                    lineHeight = 16.sp // 원하는 줄 간격 값으로 조절

                )
                Spacer(modifier = Modifier.height(4.dp))

                // 메뉴 설명
                Text(
                    text = menuData.menuDetail,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF8B8B8B),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 16.sp // 원하는 줄 간격 값으로 조절

                )
                Spacer(modifier = Modifier.height(8.dp))

                // 가격 정보 (할인율, 할인된 가격, 원래 가격)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 할인율 (초록색)
                    Text(
                        text = "30%",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2DA74D) // 초록색
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    // 할인된 가격 (굵은 글씨)
                    Text(
                        text = "${menuData.menuDiscountPrice}원",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    // 원래 가격 (취소선)
                    Text(
                        text = "${menuData.menuPrice}원",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        textDecoration = TextDecoration.LineThrough
                    )
                }
            }
        }
    }
}

// 예시 Preview
@Preview(showBackground = true)
@Composable
fun StorePreview() {
    val navController = rememberNavController()
    StoreScreen(navController = navController, storeId = 123)
}