package com.example.Auzaar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.Auzaar.navigation.Navigation
import com.example.Auzaar.ui.theme.AuzaarTheme
import com.example.Auzaar.view.signUp.SignUpPage


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AuzaarAppMain()
        }
    }

    @Composable
    fun AuzaarAppMain() {
        AuzaarTheme {
            Surface(color = MaterialTheme.colors.background) {
                Navigation()
            }
        }
    }
}

