package com.project.namu.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.namu.R
import com.project.namu.ui.component.check_BottomBar
import com.project.namu.ui.theme.Main200

@Composable
fun OrderDetailsScreen(navController: NavController) {
    Scaffold(
        bottomBar = { check_BottomBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Main200)
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "뒤로가기", tint = Color.White, modifier = Modifier.size(32.dp))
                        Text(
                            text = "주문 내역",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Image(
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = "공유",
                            modifier = Modifier.size(28.dp)
                        )


                    }

                }
                item {
                    Spacer(modifier = Modifier.height(60.dp))

                    // ✅ Box를 사용하여 배경 이미지 + 체크 아이콘 + OrderInfoBox 오버레이
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        // ✅ 배경 이미지
                        Image(
                            painter = painterResource(id = R.drawable.orderdetailbill),
                            contentDescription = "주문 상세 배경",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(650.dp), // 원하는 세로 높이로 조정
                            contentScale = ContentScale.Crop, // 또는 ContentScale.FillWidth 와 함께 alignment 지정 가능
                            alignment = Alignment.TopCenter
                        )
                        // ✅ circlecheck 이미지를 중앙 상단에 배치 (bill 이미지 위에 겹침)
                        Image(
                            painter = painterResource(id = R.drawable.circlecheck), // circlecheck 이미지 리소스 사용
                            contentDescription = "완료",
                            modifier = Modifier
                                .size(100.dp) // 이미지 크기
                                .align(Alignment.TopCenter) // 중앙 상단 배치
                                .offset(y = (-50).dp) // bill 이미지 위로 이동
                                .zIndex(1f) // bill 이미지 위에 표시되도록 설정
                        )


                        // ✅ OrderInfoBox를 배경 이미지 위에 자연스럽게 배치
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 45.dp)
                                .padding(horizontal = 20.dp), // 🔥 배경 이미지 위로 적절히 이동
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "예약상태", fontSize = 14.sp, color = Color.Gray)
                            Text(text = "픽업대기중", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)

                            Text(text = "주문번호", fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(top = 4.dp))
                            Text(text = "000324506331", fontSize = 16.sp, fontWeight = FontWeight.Bold)

                            // ✅ Divider 추가 (예약상태 & 주문번호 vs OrderInfoBox 구분)
                            Divider(
                                color = Color(0xFFE7E7E7),
                                thickness = 1.dp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp)
                            )

                            OrderInfo2Box(title = "주문 상품", value1 = "세트 A, B", value2 = "총 금액 12500원")
                            Spacer(modifier = Modifier.height(10.dp))
                            OrderInfoBox(title = "매장명", value = "요거피플 동래점", showArrow = true)
                            Spacer(modifier = Modifier.height(10.dp))
                            OrderInfoBox(title = "전화번호", value = "070-5566-7933", showIcon = Icons.Default.Call)
                            Spacer(modifier = Modifier.height(10.dp))
                            OrderInfo3Box(title = "픽업 시간", value = "12/5 (목) 19:00 - 19:30")
                            Spacer(modifier = Modifier.height(24.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                                Image(painter = painterResource(id = R.drawable.download), contentDescription = "다운로드", modifier = Modifier.size(28.dp))
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "영수증 다운로드", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(12.dp)) }
            }
        }
    }
}

// ✅ 주문 정보 박스 (OrderInfoBox)
@Composable
fun OrderInfoBox(title: String, value: String, showArrow: Boolean = false, showIcon: ImageVector? = null, showChip: String? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFFE7E7E7),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp), // ✅ verticalPadding 적용
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 14.sp, color = Color.Gray)
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        if (showArrow) {
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "이동", tint = Color.Gray)
        }
        if (showIcon != null) {
            Icon(imageVector = showIcon, contentDescription = "아이콘", tint = Color(0xFF4CAF50))
        }
        if (showChip != null) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF4CAF50), shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 6.dp, vertical = 4.dp)
            ) {
                Text(text = showChip, fontSize = 12.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun OrderInfo2Box(title: String, value1: String,value2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFFE7E7E7),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp), // ✅ verticalPadding 적용
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp ))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = value1, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = value2, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun OrderInfo3Box(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFFE7E7E7),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp), // ✅ verticalPadding 적용
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = title, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .background(Color(0xFF4CAF50), shape = RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp)
                ) {
                    Text(text = "오늘", fontSize = 12.sp, color = Color.White)
                }
            }

            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}




@Preview(showBackground = true)
@Composable
fun OrderDetailsPreview() {
    val navController = rememberNavController()
    OrderDetailsScreen(navController)
}
