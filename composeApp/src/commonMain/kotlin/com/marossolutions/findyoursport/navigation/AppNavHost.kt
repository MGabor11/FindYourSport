package com.marossolutions.findyoursport.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marossolutions.findyoursport.navigation.screens.ScreenAddSportPlace
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.marossolutions.findyoursport.navigation.screens.ScreenHome
import com.marossolutions.findyoursport.ui.AddSportPlaceScreen
import com.marossolutions.findyoursport.ui.HomeScreen

@Composable
fun AppNavHost(
    simpleNavigator: SimpleNavigator,
    innerPadding: PaddingValues,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        modifier = Modifier.padding(innerPadding),
        startDestination = ScreenHome
    ) {
        composable<ScreenHome> {
            HomeScreen()
        }
        composable<ScreenAddSportPlace> {
            AddSportPlaceScreen()
        }
    }

    LaunchedEffect(Unit) {
        simpleNavigator.navigationEvents
            .onEach { navigateEvent ->
                when (navigateEvent) {
                    is NavigationEvent.ForwardNavigation -> navController.navigate(navigateEvent.screen) {
                        navigateEvent.navigationOptions?.let { navigationOptions ->
                            popUpTo(navigationOptions.popUpToScreen) {
                                inclusive = navigationOptions.popUpToInclusive
                            }
                        }
                    }

                    NavigationEvent.NavigateUp -> navController.navigateUp()
                }
            }
            .launchIn(this)

        navController.currentBackStackEntryFlow
            .onEach {
                simpleNavigator.setCurrentAppScreen(it.toAppScreen())
            }
            .launchIn(this)
    }
}
