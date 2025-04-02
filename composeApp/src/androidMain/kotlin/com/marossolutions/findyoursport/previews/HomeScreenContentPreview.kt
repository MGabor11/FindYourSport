package com.marossolutions.findyoursport.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.marossolutions.findyoursport.model.SportPlace
import com.marossolutions.findyoursport.theme.AppTheme
import com.marossolutions.findyoursport.ui.HomeScreenContent
import com.marossolutions.findyoursport.viewmodel.HomeViewModel.HomeUiState

@Preview
@Composable
fun HomeScreenContentPreview() {
    AppTheme {
        HomeScreenContent(
            HomeUiState.Content(
                sportPlaces = listOf(
                    SportPlace(
                        name = "name",
                        location = "location"
                    )
                )
            ),
            onRefreshClick = { },
            onCardLongClick = { },
            onNavigateToAddSportClick = { }
        )
    }
}
