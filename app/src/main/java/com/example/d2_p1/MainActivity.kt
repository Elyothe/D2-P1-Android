package com.example.d2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.d2_p1.admin.ui.screens.CreateSpaceScreen
import com.example.d2_p1.admin.ui.screens.ModifySpaceScreen
import com.example.d2_p1.core.ui.screens.HomeScreen
import com.example.d2_p1.ui.theme.D2P1Theme
import navigation.Screen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            D2P1Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.HomeScreen.route
                ) {
                    composable(Screen.HomeScreen.route) {
                        HomeScreen(onNavigate = { route ->
                            navController.navigate(route)
                        })
                    }

                    composable(Screen.CreateSpaceScreen.route) {
                        CreateSpaceScreen(
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                    composable(Screen.ModifySpaceScreen.route) {
                        ModifySpaceScreen(
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
