package com.project.namu.page

import com.project.namu.R


import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController





@Composable
fun HorizontalItem(

) {

        Button(
            onClick = {},
            contentPadding = PaddingValues(0.dp),

            // 내용에 맞게 버튼 크기 조정
            colors = ButtonDefaults.buttonColors(
                Color.Transparent,  // 버튼 배경을 투명하게 설정
                contentColor = Color.Unspecified
            ),
            // 기본 텍스트 색상 사용
            shape = RectangleShape

        ) {
            Column(
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dessert),  // 이미지 리소스 설정
                    contentDescription = "배너 사진",
                    modifier = Modifier
                        .width(155.dp)
                        .height(155.dp)
                        .clip(RoundedCornerShape(6.dp))
                )
                Text(
                    text = "ㅇㄴㅇㄴ",
                    modifier = Modifier
                        .padding(top = 8.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(600),

                        ),
                    maxLines = 1,  // 한 줄로 제한
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "스터디",

                    // Body/Caption 12 R
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF60646C),

                        )
                )

                Text(
                    text = "안드로이드",

                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(600),

                        )
                )
            }
        }
    }




@Composable
@Preview(showBackground = true)
fun Preview22() {
    val navController = rememberNavController()
    HorizontalItem()
}
