package com.marossolutions.findyoursport.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marossolutions.findyoursport.model.SportPlace
import com.marossolutions.findyoursport.navigation.SimpleNavigator
import com.marossolutions.findyoursport.repository.SportPlacesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class AddSportPlaceViewModel(
    private val sportPlacesRepository: SportPlacesRepository,
    private val navigator: SimpleNavigator,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)

    val isLoading = _isLoading.asStateFlow()

    fun addSportPlace(sportPlace:SportPlace) {
        viewModelScope.launch {
            _isLoading.value = true
            sportPlacesRepository.addSportPlace(sportPlace)
            _isLoading.value = false
            navigator.navigateUp()
        }
    }
}