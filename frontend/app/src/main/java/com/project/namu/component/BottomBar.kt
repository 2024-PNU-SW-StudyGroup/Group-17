package com.project.namu.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.project.namu.ui.theme.Main100
import com.project.namu.ui.theme.Main200


@Composable
fun BottomNav(
    navController: NavController,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            BottomIcon(
                icon = Icons.Default.Home,
                text = "홈",
                selected = selectedIndex == 0,
                action = {
                    onItemSelected(0)
                    navController.navigate("홈") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            BottomIcon(
                icon = Icons.Default.Favorite,
                text = "찜한목록",
                selected = selectedIndex == 1,
                action = {
                    onItemSelected(1)
                    navController.navigate("예약하기") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            BottomIcon(
                icon = Icons.Default.Person,
                text = "마이페이지",
                selected = selectedIndex == 2,
                action = {
                    onItemSelected(2)
                    navController.navigate("마이페이지") {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun BottomIcon(
    icon: ImageVector,
    text: String,
    selected: Boolean,
    action: () -> Unit
) {
    Button(
        contentPadding = PaddingValues(0.dp),
        onClick = action,
        colors = ButtonDefaults.buttonColors(
            Color.Transparent,
            contentColor = Color.Unspecified
        ),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .width(120.dp)
                .height(60.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = icon,
                contentDescription = "icon description",
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp),
                colorFilter = if (selected) {
                    ColorFilter.tint(Main100)
                } else {
                    ColorFilter.tint(Main200)
                }
            )

            Text(
                text = text,
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(400),
                    color = if (selected) Main100 else Main200,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    val navController = rememberNavController()
    BottomNav(navController = navController, selectedIndex = 1) {
        
    }
}