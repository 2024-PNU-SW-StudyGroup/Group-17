package com.project.namu.bookmark

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.project.namu.R

@Composable
fun MyScreen() {
    // 첫 번째 버튼의 텍스트 상태
    var buttonText by remember { mutableStateOf("Jetpack") }
    // 이미지 가시성 상태
    var imageVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 첫 번째 버튼: 텍스트 변경
        Button(onClick = {
            buttonText = if (buttonText == "Jetpack") "Compose" else "Jetpack"
        }) {
            Text(buttonText)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 두 번째 버튼: 이미지 가시성 전환
        Button(onClick = {
            imageVisible = !imageVisible
        }) {
            Text("이미지 만들기")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 이미지 표시
        AnimatedVisibility(
            visible = imageVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Image(
                painter = painterResource(id = R.drawable.star), // 이미지 리소스를 적절히 변경
                contentDescription = "Sample Image",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewMyScreen() {
    MyScreen()
}
