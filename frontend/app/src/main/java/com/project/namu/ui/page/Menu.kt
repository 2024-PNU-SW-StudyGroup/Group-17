package com.project.namu.ui.page


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.gson.annotations.SerializedName
import com.project.namu.data.model.MenuDetailData
import com.project.namu.ui.component.Menu_BottomBar
import com.project.namu.ui.theme.Main100
import com.project.namu.ui.theme.Ui_empty
import com.project.namu.ui.viewmodel.MenuDetailUiState
import com.project.namu.ui.viewmodel.MenuViewModel

@Composable
fun MenuScreen(
    navController: NavController,
    menuId: Int,
    viewModel: MenuViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(menuId) {
        viewModel.getMenuDetail(menuId) // ✅ menuId를 기반으로 API 요청
    }

    Scaffold(
        topBar = { /* 상단바 */ },
        bottomBar = {
            when (uiState) {
                is MenuDetailUiState.Success -> {
                    val menuDetail = (uiState as MenuDetailUiState.Success).data
                    Menu_BottomBar(navController = navController, menuDetail = menuDetail)
                }
                else -> {
                    // 로딩이나 에러 상태일 경우 기본 BottomBar (혹은 빈 공간) 처리
                    Box(modifier = Modifier.height(0.dp))
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (uiState) {
                is MenuDetailUiState.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is MenuDetailUiState.Success -> {
                    val menuDetail = (uiState as MenuDetailUiState.Success).data
                    MenuContent(menuDetail)
                }
                is MenuDetailUiState.Error -> {
                    val message = (uiState as MenuDetailUiState.Error).message
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "에러: $message", color = Color.Red)
                    }
                }
            }
        }
    }
}



@Composable
fun MenuContent(menuDetail: MenuDetailData) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Menu_Image(menuDetail)

        Column(modifier = Modifier.padding(20.dp)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = menuDetail.setName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(12.dp))

                // 메뉴 구성
                Text(
                    text = menuDetail.menuNames,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 할인 정보 및 가격
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 할인율 (녹색 배경 버튼 스타일)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(color = Main100, shape = RoundedCornerShape(16.dp)) // 녹색 배경과 둥근 모서리
                        .width(60.dp)
                        .padding(horizontal = 8.dp, vertical = 4.dp) // 텍스트 주위의 패딩
                ) {
                    Text(
                        text = "30%",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // 할인된 가격
                Text(
                    text = "${menuDetail.menuDiscountPrice}원",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Main100 // 녹색 텍스트로 스타일링
                )

                Spacer(modifier = Modifier.width(8.dp))

                // 원래 가격 (취소선)
                Text(
                    text = "${menuDetail.menuPrice}원",
                    fontSize = 16.sp,
                    color = Main100,
                    textDecoration = TextDecoration.LineThrough
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 메뉴 설명
            Text(
                text = "상세 정보",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = menuDetail.menuDetail,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF8B8B8B),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = menuDetail.menuDetail,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF8B8B8B),
            )



            Spacer(modifier = Modifier.height(10.dp))


        }
    }

}


@Composable
fun Menu_Image(menuDetail: MenuDetailData) {
    var isFavorite by remember { mutableStateOf(false) } // 좋아요 상태

    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(color = Ui_empty)
        ) {
            Image(
                painter = rememberAsyncImagePainter(menuDetail.menuPictureUrl), // ✅ 변경됨
                contentDescription = "메뉴 사진",
                modifier = Modifier.fillMaxWidth().height(230.dp)
        )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        ) {
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
}

