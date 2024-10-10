package com.example.coneez2.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coneez2.R
import com.example.coneez2.ui.theme.Main600
import com.example.coneez2.ui.theme.cafeFontFamily


@Composable
fun LoginScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(120.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.symbol),
                    contentDescription = "logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(60.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))

                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(210.dp)
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            LoginContent(navController)

            Spacer(modifier = Modifier.height(160.dp))


            LoginRow()

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(navController: NavController) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "아이디",
                color = Color.Gray,
                modifier = Modifier.padding(start = 8.dp)
            )
            TextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(text = "아이디를 입력해 주세요.", color = Color.LightGray)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "비밀번호",
                color = Color.Gray,
                modifier = Modifier.padding(start = 8.dp)
            )
            TextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(text = "비밀번호를 입력해 주세요.", color = Color.LightGray)
                },
                modifier = Modifier
                    .fillMaxWidth(),

                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = { navController.navigate("result")  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(size = 4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Main600)
            ) {
                Text(text = "로그인", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                TextButton(onClick = { /* 아이디 찾기 */ }) {
                    Text("아이디 찾기", color = Color.Gray)
                }
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .height(15.dp)  // 세로 방향으로 꽉 채움
                        .width(1.dp)      // 선의 두께 설정
                )
                TextButton(onClick = { /* 비밀번호 재설정 */ }) {
                    Text("비밀번호 재설정", color = Color.Gray)
                }
                Divider(
                    color = Color.Gray,
                    modifier = Modifier
                        .height(15.dp)  // 세로 방향으로 꽉 채움
                        .width(1.dp)      // 선의 두께 설정
                )
                TextButton(onClick = { /* 회원가입 */ }) {
                    Text("회원가입", color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun LoginRow(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.kakao),
            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(65.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Image(
            painter = painterResource(id = R.drawable.naver),
            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(65.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(65.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Image(
            painter = painterResource(id = R.drawable.apple),
            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(65.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Previewlogin() {
    val navController = rememberNavController()
    LoginScreen(navController)
}