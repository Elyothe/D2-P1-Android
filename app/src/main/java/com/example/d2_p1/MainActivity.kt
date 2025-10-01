package com.example.d2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.d2_p1.admin.ui.screens.GalleryOneScreen
import com.example.d2_p1.core.data.models.Routes
import com.example.d2_p1.core.ui.screens.HomeScreen
import com.example.d2_p1.ui.theme.D2P1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            D2P1Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.homescreen
                ) {
                    composable(Routes.homescreen) {
                        HomeScreen(navController)
                    }
                    composable(Routes.galleryOne) {
                        GalleryOneScreen()
                    }
                }
            }
        }
    }
}


