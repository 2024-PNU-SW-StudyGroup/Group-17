package com.project.namu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.project.namu.login.Login
import com.project.namu.login.PopUpViewModel
import com.project.namu.login.SignIn
import com.project.namu.model.SignInViewModel
import com.project.namu.navigation.NavGraph
import com.project.namu.ui.theme.NamuTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NamuTheme {
                // A surface container using the 'background' color from the theme
              val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

