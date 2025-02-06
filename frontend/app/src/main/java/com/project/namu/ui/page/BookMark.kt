package com.project.namu.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
import com.project.namu.data.model.WishItem
import com.project.namu.ui.component.BottomNav
import com.project.namu.ui.theme.BackGround
import com.project.namu.ui.viewmodel.StoreUiState
import com.project.namu.ui.viewmodel.StoreViewModel

@Composable
fun WishListScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(1) }
    Scaffold(
        topBar = { WishListTopBar() },
        bottomBar = {
            BottomNav(
                navController = navController,
                selectedIndex = selectedIndex,
                onItemSelected = { index -> selectedIndex = index }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            WishListContent(navController = navController)
        }
    }
}


// ✅ 상단 바
@Composable
fun WishListTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "찜 목록", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)

        Icon(
            imageVector = Icons.Outlined.ShoppingCart,
            contentDescription = "장바구니",
            tint = Color.Black,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Composable
fun WishListContent(navController: NavController, storeViewModel: StoreViewModel = hiltViewModel()) {
    val context = LocalContext.current
    // 로컬 저장소에 저장된 즐겨찾기 storeId 목록 상태 (문자열로 저장)
    var favoriteIds by remember { mutableStateOf<List<String>>(emptyList()) }

    // 코루틴 scope로 로컬 저장소에서 즐겨찾기 목록 불러오기
    LaunchedEffect(key1 = true) {
        favoriteIds = FavoriteManager.getFavorites(context).toList()
    }

    // 백엔드에서 받아온 가게 데이터 상태
    val uiState by storeViewModel.uiState

    when (uiState) {
        is StoreUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is StoreUiState.Success -> {
            val stores = (uiState as StoreUiState.Success).data
            // 로컬 저장소에 저장된 storeId와 일치하는 가게만 필터링
            val favoriteStores = stores.filter { favoriteIds.contains(it.storeId.toString()) }
                if (favoriteStores.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "찜한 가게가 없습니다.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = BackGround)
                        .padding(horizontal = 22.dp, vertical = 8.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        )  {
                            Text(
                                text = "지금 판매하고 있어요.",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            SortButtonLike()
                        }
                    }
                    items(favoriteStores) { store ->
                        WishListItem(item = store.toWishItem(), navController = navController)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
        is StoreUiState.Error -> {
            val message = (uiState as StoreUiState.Error).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = message, color = Color.Red)
            }
        }
    }
}

// ✅ 찜한 아이템 개별 항목
@Composable
fun WishListItem(item: WishItem, navController: NavController) {
    var isFavorite by remember { mutableStateOf(true) }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable { /* 클릭 시 이동 가능 */ }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // 왼쪽 이미지 (임시 이미지)
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(1 / 3f)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = item.imageUrl),
                    contentDescription = "Store Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            // 오른쪽 정보
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = item.storeName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = item.setMenu,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "open-time",
                        tint = Color(0xFF00BCD4),
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = item.time, fontSize = 10.sp, color = Color.Black)

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.mappin),
                        contentDescription = "Location",
                        tint = Color(0xFF00BCD4),
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = item.distance, fontSize = 10.sp, color = Color.Black)

                    Spacer(modifier = Modifier.width(8.dp))



                }


                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "star",
                        modifier = Modifier.size(12.dp),
                        tint = Color.Unspecified // ✅ 원본 이미지 색상 유지
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = item.rating, fontSize = 10.sp, color = Color.Black)
                }
            }

            // 좋아요 버튼
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "찜",
                tint = Color.Red,
                modifier = Modifier
                    .padding(12.dp)
                    .size(24.dp)
                    .clickable { isFavorite = !isFavorite }
            )
        }
    }
}

@Composable
fun SortButtonLike() {
    Row(
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(14.dp)) // ✅ Round 처리된 부분까지 흰색 배경 적용
            .border(1.dp, Color.LightGray, RoundedCornerShape(14.dp)) // 테두리 추가
            .padding(start = 12.dp, end = 8.dp)
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = "찜한순",
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        // 텍스트
        Text(
            text = "최근 순",
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.width(4.dp))

        // 드롭다운 아이콘
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Dropdown",
            tint = Color.Black,
            modifier = Modifier.size(16.dp)
        )
    }
}


// ✅ 찜한 아이템 데이터 클래스 (하드코딩용)
fun StoreData.toWishItem(): WishItem {
    return WishItem(
        storeName = this.storeName,
        setMenu = this.setNames.joinToString(", ") { it.setName },
        time = this.pickupTimes,
        distance = "${this.location / 1000.0} km",
        rating = "${this.storeRating} (${this.reviewCount}+)",
        imageUrl = this.storePictureUrls.firstOrNull() ?: ""  // 리스트가 비어있다면 빈 문자열 사용
    )
}

// ✅ 미리보기용
@Preview(showBackground = true)
@Composable
fun WishListPreview() {
    val navController = rememberNavController()
    WishListScreen(navController = navController)
}
