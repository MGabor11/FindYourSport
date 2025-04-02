package com.marossolutions.findyoursport.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import com.marossolutions.findyoursport.navigation.screens.AppScreen
import com.marossolutions.findyoursport.navigation.screens.ScreenAddSportPlace
import com.marossolutions.findyoursport.navigation.screens.ScreenHome

/**
 * This solution needs to be replaced, when toRoute method will be able to return the screen
 * from current route of NavBackStackEntry destination
 * https://stackoverflow.com/a/78495523
 */
fun NavBackStackEntry.toAppScreen(): AppScreen? = destination.route?.let { route ->
    when (route.substringBefore("?").substringBefore("/").substringAfterLast(".")) {
        ScreenHome::class.simpleName -> toRoute<ScreenHome>()
        ScreenAddSportPlace::class.simpleName -> toRoute<ScreenAddSportPlace>()
        else -> error("Route: $route, is not recognized")
    }
}
