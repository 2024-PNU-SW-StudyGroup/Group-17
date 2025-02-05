package com.project.namu.ui.page

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OrderDetailsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD4E7C5)), // 연한 녹색 배경
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // 주문 내역 타이틀
        Text(
            text = "주문 내역",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // ✅ 체크 아이콘
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "완료",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(48.dp)
                )

                Text(
                    text = "예약상태",
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                Text(
                    text = "픽업대기중",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = "주문번호",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = "000000000000",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ✅ 주문 상품 정보
                OrderInfoItem(title = "주문 상품", value = "박스 A 총 금액 11,000원")

                // ✅ 매장 정보
                OrderInfoItem(title = "매장명", value = "카페인중독 부산대점", showArrow = true)

                // ✅ 전화번호
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "전화번호", fontSize = 14.sp, color = Color.Gray)
                        Text(text = "070-5566-7933", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    IconButton(onClick = { /* 전화 걸기 */ }) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "전화",
                            tint = Color(0xFF4CAF50)
                        )
                    }
                }

                // ✅ 픽업 시간
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "픽업 시간", fontSize = 14.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .background(Color(0xFF4CAF50), shape = RoundedCornerShape(8.dp))
                            .padding(4.dp)
                    ) {
                        Text(text = "오늘", fontSize = 12.sp, color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "12/5 (목) 19:00 - 19:30", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                // ✅ 영수증 다운로드 버튼
                Button(
                    onClick = { /* 영수증 다운로드 */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                ) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "다운로드")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "영수증 다운로드", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // ✅ 하단 "확인" 버튼 (고정)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = { /* 확인 클릭 */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            ) {
                Text(text = "확인", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}

// ✅ 주문 정보 아이템 (매장명, 주문상품 등)
@Composable
fun OrderInfoItem(title: String, value: String, showArrow: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 14.sp, color = Color.Gray)
            Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        if (showArrow) {
            Icon(imageVector = Icons.Default.Check, contentDescription = "이동", tint = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailsPreview() {
    OrderDetailsScreen()
}
