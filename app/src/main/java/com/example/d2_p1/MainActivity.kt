package com.example.d2_p1

import LoginScreen
import SpaceModule
import AvailabilityModule
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.d2_p1.admin.ui.screens.create.CreateSpaceScreen
import com.example.d2_p1.admin.ui.screens.DetailSpaceScreen
import com.example.d2_p1.admin.ui.screens.ModifySpaceScreen
import com.example.d2_p1.admin.ui.screens.ListSpaceScreen
import com.example.d2_p1.available.ui.screens.AvailableSpacesScreen
import com.example.d2_p1.available.ui.screens.available.DailyAvailabilityScreen
import com.example.d2_p1.available.ui.screens.available.SearchSpaceScreen
import com.example.d2_p1.available.ui.screens.available.SpaceDetailScreen
import com.example.d2_p1.available.ui.viewmodels.AvailableSpacesViewModel
import com.example.d2_p1.available.ui.viewmodels.DailyAvailabilityViewModel
import com.example.d2_p1.available.ui.viewmodels.SearchSpaceViewModel
import com.example.d2_p1.available.ui.viewmodels.SpaceDetailViewModel
import com.example.d2_p1.core.data.models.Route
import com.example.d2_p1.core.data.models.Route.LoginScreen
import com.example.d2_p1.core.ui.screens.HomeScreen
import com.example.d2_p1.ui.theme.D2P1Theme
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            modules(
                SpaceModule,
                AvailabilityModule

            )
        }
        enableEdgeToEdge()
        setContent {
            D2P1Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.LoginScreen
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
                    composable(Route.LoginScreen){
                        LoginScreen(navController)
                    }
                    composable(
                        route = "${Route.DetailSpaceScreen}/{spaceId}"
                    ) { backStackEntry ->
                        val spaceId = backStackEntry.arguments?.getString("spaceId") ?: ""
                        DetailSpaceScreen(navController, spaceId)
                    }
                    composable(Route.SearchSpace) {
                        val viewModel: SearchSpaceViewModel = viewModel()
                        SearchSpaceScreen(navController, viewModel)
                    }
                    composable(Route.AvailableSpaces) {
                        val viewModel: AvailableSpacesViewModel = viewModel()
                        AvailableSpacesScreen(navController, viewModel)
                    }
                    composable(
                        route = Route.SpaceDispoDetails,
                        arguments = listOf(navArgument("id") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val spaceId = backStackEntry.arguments?.getInt("id") ?: 1
                        val viewModel: SpaceDetailViewModel = viewModel()
                        SpaceDetailScreen(navController, spaceId,viewModel)
                    }
                    composable(
                        route = Route.DailyAvailability,
                        arguments = listOf(navArgument("spaceId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val spaceId = backStackEntry.arguments?.getInt("spaceId") ?: 1
                        val viewModel: DailyAvailabilityViewModel = viewModel()
                        DailyAvailabilityScreen(navController, spaceId, viewModel)
                    }
                }
            }
        }
    }
}
