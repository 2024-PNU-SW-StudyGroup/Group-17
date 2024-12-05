package com.project.namu.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bookmark(){


    Scaffold(
        topBar = {
            Row(
                Modifier
                    .border(width = 1.dp, color = Color(0xFFE7E7E7))
                    .padding(1.dp)
                    .fillMaxWidth()
                    .height(82.dp)

            ) {

                    TopAppBar(
                        modifier = Modifier
                            .fillMaxWidth()

                            .padding(start = 20.dp, top = 22.dp, bottom = 20.dp, end = 20.dp),
                        title = {
                            Text(
                                text = "찜 목록",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF121212),

                                )
                            )
                        },

                    )
                }

        },
        content = {paddingValues ->
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF3F3F3))
            ) {
                item{ Sort()}

                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
                item { BookmarkItem() }
                item{ Spacer(modifier = Modifier.height(10.dp))}
            }

           }


        ,
        bottomBar = {
            BottomAppBar {
                Text("Bottom Bar", Modifier.padding(16.dp))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BookmarkPreview(){
    Bookmark()
}