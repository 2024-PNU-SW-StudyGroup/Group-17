package com.project.namu.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.project.namu.R
import com.project.namu.navigation.Screen

@Composable
fun PayComplete(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.paycomplete),
                contentDescription = "결제 완료 이미지",
                modifier = Modifier
                    .width(340.dp)
                    .height(210.dp)
            )
        }


        Spacer(modifier = Modifier.height(64.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "결제를 성공했어요!",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF121212),
                    textAlign = TextAlign.Center,
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "과연 어떤 음식이 들어있을까요?\n픽업시간까지 1시간 10분 남았어요.",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF6F6F6F),
                    textAlign = TextAlign.Center,
                )
            )
        }

        Spacer(modifier = Modifier.height(64.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = {
                    navController.navigate("orderdetail")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .width(150.dp)
                    .height(24.dp)
                    .background(
                        color = Color(0xFF1F9F37),
                        shape = RoundedCornerShape(size = 12.dp)
                    ),
                contentPadding = PaddingValues(0.dp),
            ){
                Text(
                    text = "주문내역 확인하기",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                    )
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.Home.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .width(150.dp)
                    .height(24.dp)
                    .background(
                        color = Color(0xFF1F9F37),
                        shape = RoundedCornerShape(size = 12.dp)
                    ),
                contentPadding = PaddingValues(0.dp),
            ){
                Text(
                    text = "홈으로 가기",
                    style = TextStyle(

                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                    )
                )
            }

        }
    }

}


@Preview
@Composable
fun PayCompletePreview(){
    PayComplete(navController = rememberNavController())
}