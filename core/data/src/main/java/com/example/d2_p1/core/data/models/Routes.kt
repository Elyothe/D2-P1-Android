package com.example.d2_p1.core.data.models

object Route {
   const val HomeScreen = "home_screen"
   const val LoginScreen = "login_screen"
   const val EditSpaceScreen = "editSpace_screen"
   const val ListSpaceScreen = "listSpace_screen"
   const val CreateSpaceScreen = "createSpace_screen"
   const val DetailSpaceScreen = "detailSpace_screen"
   const val SearchSpace = "search_space"
   const val AvailableSpaces = "available_spaces"
   const val SpaceDispoDetails = "space_dispo_details/{id}"
   public fun spaceDispoDetailsRoute(id: Int): String = "space_dispo_details/$id"
   const val DailyAvailability = "daily_availability/{spaceId}"
   fun dailyAvailabilityRoute(spaceId: Int): String = "daily_availability/$spaceId"

}
