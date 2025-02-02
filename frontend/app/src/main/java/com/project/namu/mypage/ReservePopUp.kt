package com.project.namu.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.namu.R

@Composable
fun ReservePopUp(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(80.dp)
            .background(color = Color(0xFF1F9F37), shape = RoundedCornerShape(size = 12.dp))
    ){
        Image(
            painter = painterResource(id = R.drawable.examplefood),
            contentDescription = "예약한 가게 사진",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(
                    RoundedCornerShape(12.dp, 0.dp, 0.dp, 12.dp),
                )
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 17.dp, vertical = 6.dp)
        ) {
            Column(

            ) {
                Text(
                    text = "예약되었어요!",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),

                        )
                )

                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.whitemap),
                        contentDescription = "위치 아이콘",
                        modifier = Modifier
                            .padding(1.dp)
                            .width(16.dp)
                            .height(16.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "카페인중독 부산대점",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFFFFFF),

                            )
                    )
                }
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "픽업 시간",
                        modifier = Modifier
                            .padding(1.dp)
                            .width(16.dp)
                            .height(16.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "픽업 시간 : 18:00 ~ 20:00",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFFFFFF),

                            )
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(0.dp),
                contentPadding = PaddingValues(0.dp),

            ){
                Image(
                    painter = painterResource(id = R.drawable.right),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

    }

}

@Preview
@Composable
fun ReservePopUpPreview(){
    ReservePopUp()
}
