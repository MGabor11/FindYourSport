package com.marossolutions.findyoursport.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marossolutions.findyoursport.model.SportPlace
import com.marossolutions.findyoursport.navigation.SimpleNavigator
import com.marossolutions.findyoursport.navigation.screens.ScreenAddSportPlace
import com.marossolutions.findyoursport.repository.SportPlacesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val sportPlacesRepository: SportPlacesRepository,
    private val navigator: SimpleNavigator,
) : ViewModel() {

    private val isLoading = MutableStateFlow(true)

    val uiState = combine(
        isLoading,
        sportPlacesRepository.sportPlaces.filterNotNull()
    ) { isLoading, sportPlaces ->
        if (isLoading) {
            HomeUiState.Loading
        } else {
            HomeUiState.Content(
                sportPlaces = sportPlaces
            )
        }

    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(),
        initialValue = HomeUiState.Loading
    )

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            isLoading.value = true
            sportPlacesRepository.fetchSportPlaces()
            isLoading.value = false
        }
    }

    fun deleteSportPlace(sportPlace: SportPlace) {
        viewModelScope.launch {
            isLoading.value = true
            sportPlacesRepository.removeSportPlace(sportPlace.id)
            isLoading.value = false
        }
    }

    fun navigateToAddSport() {
        navigator.navigateTo(ScreenAddSportPlace)
    }

    sealed interface HomeUiState {
        data object Loading : HomeUiState

        data class Content(
            val sportPlaces: List<SportPlace>,
        ) : HomeUiState
    }
}
