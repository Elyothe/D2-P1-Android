package com.example.d2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.d2_p1.admin.ui.screens.CreateSpaceScreen
import com.example.d2_p1.admin.ui.screens.DetailSpaceScreen
import com.example.d2_p1.admin.ui.screens.ModifySpaceScreen
import com.example.d2_p1.admin.ui.screens.ListSpaceScreen
import com.example.d2_p1.core.data.models.Route
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
                    startDestination = Route.HomeScreen
                ) {
                    composable(Route.HomeScreen) {
                        HomeScreen(navController)
                    }
                    composable(Route.ListSpaceScreen){
                        ListSpaceScreen(navController)
                    }
                    composable(Route.CreateSpaceScreen) {
                        CreateSpaceScreen(navController)
                    }
                    composable(Route.EditSpaceScreen) {
                        ModifySpaceScreen(navController)
                    }
                    composable(Route.DetailSpaceScreen) {
                        DetailSpaceScreen(navController)
                    }
           }
            }
        }
    }
}
