package com.project.namu.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.namu.model.EmailLogInRequest
import com.project.namu.model.LogInViewModel
import com.project.namu.navigation.Screen

@Composable
fun LogSignButton(
    type: String,
    color: String,
    PopUpViewModel: PopUpViewModel,
    LogInViewModel : LogInViewModel,
    navController: NavController


){

    val isSuccess by LogInViewModel.isSuccess.collectAsState()

    val email by LogInViewModel.email.collectAsState()
    val password by LogInViewModel.password.collectAsState()


    Button(
        onClick = {
            if (email=="" || password=="") {
                PopUpViewModel.showDialog()
            } else {
                val request = EmailLogInRequest(email, password)
                LogInViewModel.postLoginData(request, navController, PopUpViewModel)

            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(56.dp)
            .border(
                width = if (color == "green") 0.dp else 1.dp,
                color = Color(0xFF1F9F37),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .background(
                color = if (color == "green") Color(0xFF1F9F37) else Color.White,
                shape = RoundedCornerShape(size = 12.dp)
            ),
        contentPadding = PaddingValues(0.dp),

    ){


        Text(
            text = if (type == "login") "Log in" else "Sign in",
            style = TextStyle(
                fontSize = 20.sp,

                fontWeight = FontWeight(400),
                color =  if (color == "green") Color(0xFFFFFFFF) else Color(0xFF1F9F37),
                textAlign = TextAlign.Center,
                letterSpacing = 0.02.sp,
            )
        )


    }
}

@Preview
@Composable
fun LogSignButtonPreview(){
    LogSignButton(type = "login", color = "green", PopUpViewModel = PopUpViewModel(), LogInViewModel = LogInViewModel(), navController = rememberNavController())
}