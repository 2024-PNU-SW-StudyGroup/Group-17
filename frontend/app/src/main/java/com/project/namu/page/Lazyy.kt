package com.project.namu.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun cm2(){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        iHorizontalItem()
        Spacer(modifier = Modifier.height(30.dp))

        HorizontalItem()
        Spacer(modifier = Modifier.height(30.dp))
        HorizontalItem()

        HorizontalItem()
        Spacer(modifier = Modifier.height(30.dp))

        HorizontalItem()
        Spacer(modifier = Modifier.height(30.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun cm2Preview(){
    cm2()
}