package com.project.namu.bookmark

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.namu.R

@Composable
fun Sort(){
Row(
    modifier = Modifier
        .fillMaxWidth()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp,horizontal = 20.dp)

    ) {
        Text(
            text = "지금 판매하고 있어요.",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF353535),
            )
        )

        Spacer(modifier = Modifier.width(30.dp))

        Image(
            painterResource(id = R.drawable.defaultsort),
            contentDescription = "기본 정렬",
            modifier = Modifier

                .width(119.dp)
                .height(29.dp)

        )
    }
}
}


@Preview (showBackground = true)
@Composable
fun SortPreview(){
    Sort()
}