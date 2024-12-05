package com.project.namu.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.project.namu.page.Store_MenuDetail
import com.project.namu.ui.theme.GrayLine
import com.project.namu.ui.theme.Main100
import com.project.namu.ui.theme.Main200
import com.project.namu.ui.theme.Main300


@Composable
fun BottomNav(
    navController: NavController,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        ) {
        Divider(color = GrayLine, thickness = 2.dp)  // 하단에 회색 구분선 추가

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Spacer(modifier = Modifier.weight(1f))
            BottomIcon(
                icon = Icons.Default.Home,
                text = "홈",
                selected = selectedIndex == 0,
                action = {
                    onItemSelected(0)
                    navController.navigate("홈") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            BottomIcon(
                icon = Icons.Default.Favorite,
                text = "찜한목록",
                selected = selectedIndex == 1,
                action = {
                    onItemSelected(1)
                    navController.navigate("예약하기") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            BottomIcon(
                icon = Icons.Default.Person,
                text = "마이페이지",
                selected = selectedIndex == 2,
                action = {
                    onItemSelected(2)
                    navController.navigate("마이페이지") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun BottomIcon(
    icon: ImageVector,
    text: String,
    selected: Boolean,
    action: () -> Unit
) {
    Button(
        contentPadding = PaddingValues(0.dp),
        onClick = action,
        colors = ButtonDefaults.buttonColors(
            Color.Transparent,
            contentColor = Color.Unspecified
        ),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .width(120.dp)
                .height(60.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = icon,
                contentDescription = "icon description",
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp),
                colorFilter = if (selected) {
                    ColorFilter.tint(Main100)
                } else {
                    ColorFilter.tint(Main300)
                }
            )

            Text(
                text = text,
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400),
                    color = if (selected) Main100 else Main300,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

// 가게 준비 되었을때 가격확인 바텀바
@Composable
fun Store_AvailableBottomBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(16.dp)
            .background(
                color = Color(0xFF4CAF50),
                shape = RoundedCornerShape(16.dp)
            ) // Green background with rounded corners
            .clickable { /* Handle click */ },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "총 7,070원 - 장바구니 보기",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

//가게 준비중일때 바텀바
@Composable
fun Store_NotAvailableBottomBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(20.dp)
            .background(color = Color.White, shape = RoundedCornerShape(16.dp)), // Gray background with rounded corners
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "아직 준비중이에요.",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

//가게 영업상태에 따른 스위치 바텀바
@Composable
fun Store_SwitchBottomBar(isAvailable: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // 조건에 따른 바텀바 표시
        if (isAvailable) {
            Store_AvailableBottomBar() // 상품이 이용 가능할 때
        } else {
            Store_NotAvailableBottomBar() // 아직 준비 중일 때
        }
        
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun Menu_BottomBar(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(16.dp)
                .background(
                    color = Color(0xFF4CAF50),
                    shape = RoundedCornerShape(16.dp)
                ) // Green background with rounded corners
                .clickable { /* Handle click */ },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "장바구니에 담기",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(52.dp))
    }
}



@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    val navController = rememberNavController()

    BottomNav(navController = navController, selectedIndex = 0) {

    }
}