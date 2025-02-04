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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.project.namu.R
import com.project.namu.ui.component.BottomNav
import com.project.namu.ui.theme.BackGround

@Composable
fun WishListScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(0) }
    Scaffold(
        topBar = { WishListTopBar() },
        bottomBar = {BottomNav(
            navController = navController,
            selectedIndex = selectedIndex,
            onItemSelected = { index ->
                selectedIndex = index
            }
        ) }
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

// ✅ 찜한 목록 내용
@Composable
fun WishListContent(navController: NavController) {
    val sampleStores = List(5) {
        WishItem(
            storeName = "카페인중독 부산대점",
            setMenu = "약과세트A (주인장 추천 초코 약과세트)",
            time = "19:00 - 21:00",
            distance = "1.9km",
            rating = "4.5 (100+)",
            imageUrl = "" // 여기에 이미지 URL을 넣을 수도 있음
        )
    }

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
                Text(text = "지금 판매하고 있어요.",  fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)

                SortButtonLike()
            }
        }

        items(sampleStores) { item ->
            WishListItem(item = item, navController = navController)
            Spacer(modifier = Modifier.height(12.dp))
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
            .height(110.dp)
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
                    fontSize = 18.sp,
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
            text = "최근 찜한 순",
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
            modifier = Modifier.size(16.dp)
        )
    }
}


// ✅ 찜한 아이템 데이터 클래스 (하드코딩용)
data class WishItem(
    val storeName: String,
    val setMenu: String,
    val time: String,
    val distance: String,
    val rating: String,
    val imageUrl: String
)

// ✅ 미리보기용
@Preview(showBackground = true)
@Composable
fun WishListPreview() {
    val navController = rememberNavController()
    WishListScreen(navController = navController)
}
