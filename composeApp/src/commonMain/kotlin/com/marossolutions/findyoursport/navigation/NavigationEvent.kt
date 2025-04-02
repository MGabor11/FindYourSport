package com.marossolutions.findyoursport.navigation

import com.marossolutions.findyoursport.navigation.screens.AppScreen

sealed interface NavigationEvent {

    data object NavigateUp : NavigationEvent

    data class ForwardNavigation(
        val screen: AppScreen,
        val navigationOptions: NavigationOptions? = null
    ) : NavigationEvent
}
