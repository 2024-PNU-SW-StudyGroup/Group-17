package com.project.namu.ui.page

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.namu.ui.theme.BackGround
import com.project.namu.ui.theme.CartColor

@Composable
fun CartScreen(navController: NavController) {

    Scaffold(
        topBar = { CartTopBar() },

        bottomBar = { },

        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                // 메인 콘텐츠
                CartContent()
            }
        }
    )
}

@Composable
fun CartTopBar(){
    Row(
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth()
            .background(color = BackGround),
        verticalAlignment = Alignment.CenterVertically
    ){
        // 가게 사진
        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 12.dp)
                .size(40.dp)
                .background(Color.LightGray)
        )

        Column(
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth()
        ) {
            // 가게명
            Text(text = "가게명", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)

            //주문 세부 정보
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 픽업 시간
                Text(text = "픽업", fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "19:00 ~ 19:30", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.Black)

                Spacer(modifier = Modifier.width(6.dp))

                // 위치
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "위치",
                    modifier = Modifier.size(14.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = "257m", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.Black)

                Spacer(modifier = Modifier.width(6.dp))

                //도보 시간
                Text(text = "도보", fontSize = 12.sp, fontWeight = FontWeight.ExtraBold, color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "3분", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.Black)





            }
        }
    }
}

@Composable
fun CartContent(){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CartColor),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


    }

}

@Preview(showBackground = true)
@Composable
fun CartPreview() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // 전체 배경색 설정
    ) {
        CartScreen(navController = navController)
    }
}