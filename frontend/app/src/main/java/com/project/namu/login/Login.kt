package com.project.namu.login

import android.content.Context
import android.database.Cursor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.namu.R
import com.project.namu.model.AuthRepository
import com.project.namu.model.AuthViewModel
import com.project.namu.model.LogInViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    PopUpViewModel: PopUpViewModel,
    LogInViewModel: LogInViewModel,
    navController: NavController,
    AuthViewModel : AuthViewModel
){




    val isDialogVisible by PopUpViewModel.isDialogVisible.collectAsState()
    if(isDialogVisible){
        PopUp(text = "아이디 또는 비밀번호를\n 확인해 주세요.", viewModel = PopUpViewModel)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)) {
        Spacer(modifier = Modifier.height(100.dp))

        Image(
            painter = painterResource(id = R.drawable.namulogo),
            contentDescription = "로그인 로고 이미지",
            modifier = Modifier
                .width(166.dp)
                .height(66.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "작은 배달로, 나무 키우기",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF1F9F37),
                textAlign = TextAlign.Center,
                letterSpacing = 0.02.sp,
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(35.dp))
        val email by LogInViewModel.email.collectAsState()

        TextField(
            value = email,
            onValueChange = { LogInViewModel.updateEmail(it) },
            label = { Text(
                text = "E-mail",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFAAAAAA),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.02.sp,
                )
            ) },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,

            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFFE7E7E7), // 포커스 상태에 따른 색상 변경
                    shape = RoundedCornerShape(16.dp)
                )
                , singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        val password by LogInViewModel.password.collectAsState()
        var passwordVisible by remember { mutableStateOf(false) }

        TextField(
            value = password,
            onValueChange = { LogInViewModel.updatePassword(it) },
            label = { Text(
                text = "Password",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFAAAAAA),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.02.sp,
                )
            ) },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) {
                    Icons.Default.Clear
                } else {
                    Icons.Default.Lock
                }
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,

                ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFFE7E7E7), // 포커스 상태에 따른 색상 변경
                    shape = RoundedCornerShape(16.dp)
                )
            , singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        //if(email == "" || password == "" || isLogInSuccess == false) {

            LogSignButton(type = "login", color = "green", PopUpViewModel = PopUpViewModel, LogInViewModel = LogInViewModel, navController, AuthViewModel = AuthViewModel)


        Text(
            text = "아이디/비밀번호 찾기",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF121212),

                textAlign = TextAlign.Center,
                letterSpacing = 0.01.sp,
            ),
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 10.dp, end = 20.dp)
                .clickable { }
        )

        Spacer(modifier = Modifier.height(40.dp))

        TextWithDivider(text = "간편로그인")

        Spacer(modifier = Modifier.height(11.dp))

        SocialLogin(
            text = "카카오톡으로 계속하기" ,
            iconName = painterResource(id = R.drawable.kakaotalklogo) ,
            backgroundColor = Color(0xFFFFE812))

        Spacer(modifier = Modifier.height(10.dp))

        SocialLogin(
            text = "구글로 계속하기",
            iconName = painterResource(id = R.drawable.googlelogo),
            backgroundColor = Color.White,
            border = 1)

        Spacer(modifier = Modifier.height(10.dp))

        SocialLogin(
            text = "네이버로 계속하기",
            iconName = painterResource(id = R.drawable.naverlogo),
            backgroundColor = Color(0xFF00C300),
            textColor = Color.White)

        Spacer(modifier = Modifier.height(48.dp))

        TextWithDivider(text = "혹시 처음이신가요?")

        Spacer(modifier = Modifier.height(10.dp))

        LogSignButton(type = "signin", color = "white", PopUpViewModel = PopUpViewModel, LogInViewModel = LogInViewModel, navController, AuthViewModel = AuthViewModel)
    }

}

@Composable
fun SocialLogin(
    text: String,
    iconName: Painter,
    backgroundColor: Color,
    textColor : Color = Color(0xFF121212),
    border: Int = 0
    ){
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(56.dp)
            .border(
                width = border.dp,
                color = Color(0xFFE7E7E7),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .background(color = backgroundColor, shape = RoundedCornerShape(size = 12.dp))





    ){
        Image(
            painter = iconName ,
            contentDescription = text,
            modifier = Modifier
                .size(44.dp)

        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = text,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                color = textColor,
                textAlign = TextAlign.Center,
                letterSpacing = 0.02.sp,
            )
        )


    }

}

@Composable
fun TextWithDivider(
    text: String
){
    Row(
        verticalAlignment = Alignment.CenterVertically, // 수직 중앙 정렬
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp) // 좌우 패딩 20dp 적용
    ) {
        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier
                .weight(1f) // 남은 공간을 균등하게 분배
        )

        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF121212),
                textAlign = TextAlign.Center,
                letterSpacing = 0.02.sp,
            ),
            modifier = Modifier
                .padding(horizontal = 13.dp) // 구분선과의 간격 유지
        )

        Divider(
            color = Color.Black, // 구분선 색상
            thickness = 1.dp,    // 구분선 두께
            modifier = Modifier
                .weight(1f) // 남은 공간을 균등하게 분배
        )
    }
}

/*
@Preview
@Composable
fun LoginPreview(){
    Login(PopUpViewModel= PopUpViewModel(), LogInViewModel= LogInViewModel(), navController = rememberNavController(), AuthViewModel = AuthViewModel(
        AuthRepository()
    )
    )
}


*/