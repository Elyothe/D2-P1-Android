package com.example.d2_p1.navigation

sealed class Screen(val route: String) {
   object HomeScreen : Screen("home_screen")
   object CreateSpaceScreen : Screen("space_screen")
   object ModifySpaceScreen : Screen("modify_space_screen/{spaceId}") {
      fun createRoute(spaceId: Int) = "modify_space_screen/$spaceId"
   }

   object SpaceDetailScreen : Screen("space_detail_screen/{spaceId}") {
      fun createRoute(spaceId: Int) = "space_detail_screen/$spaceId"
   }
}