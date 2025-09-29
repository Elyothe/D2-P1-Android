package com.example.d2_p1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.d2_p1.admin.ui.GalleryOneScreen
import com.example.d2_p1.core.ui.screens.HomeScreen
import com.example.d2_p1.navigation.Routes
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
                        HomeScreen(onNavigate = { navController.navigate(Routes.galleryOne) })
                    }
                    composable(Routes.galleryOne) {
                        GalleryOneScreen()
                    }
                }
            }
        }
    }
}


