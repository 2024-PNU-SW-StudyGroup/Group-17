package com.project.namu.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.project.namu.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopUp(){
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp))

            .padding(start = 60.dp, top = 27.dp, end = 70.dp, bottom = 28.dp)
    ) {
        DialogContent()
    }
}


@Composable
fun DialogContent(){
Column (
    modifier= Modifier
        .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,

){

    Image(
        painter = painterResource(id = R.drawable.circlealert),
        contentDescription = "느낌표 경고",
        modifier = Modifier.size(40.dp))

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "회원가입이 완료되었어요.",
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF000000),
            textAlign = TextAlign.Center,
            letterSpacing = 0.02.sp,
        )
    )

    Spacer(modifier = Modifier.height(32.dp))

    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1F9F37)
        ),
        shape = RoundedCornerShape(size = 8.dp),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .width(90.dp)
            .height(39.dp)


    ) {

        Text(
            text = "확인",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                letterSpacing = 0.02.sp,
            )
        )
    }
}
    }



@Preview
@Composable
fun PopUpPreview(){
    PopUp()
}