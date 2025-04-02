package com.marossolutions.findyoursport.navigation

import com.marossolutions.findyoursport.navigation.NavigationEvent
import com.marossolutions.findyoursport.navigation.NavigationOptions
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import com.marossolutions.findyoursport.navigation.screens.AppScreen

interface SimpleNavigator {

    val navigationEvents: SharedFlow<NavigationEvent>

    val currentAppScreen: StateFlow<AppScreen?>

    fun setCurrentAppScreen(screen: AppScreen?)

    fun navigateTo(screen: AppScreen, navigationOptions: NavigationOptions? = null)

    fun navigateUp()
}