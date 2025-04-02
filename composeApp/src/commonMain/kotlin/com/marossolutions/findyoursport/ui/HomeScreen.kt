package com.marossolutions.findyoursport.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marossolutions.findyoursport.components.FullScreenLoading
import com.marossolutions.findyoursport.model.SportPlace
import com.marossolutions.findyoursport.viewmodel.HomeViewModel
import com.marossolutions.findyoursport.viewmodel.HomeViewModel.HomeUiState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        onRefreshClick = viewModel::refresh,
        onCardLongClick = viewModel::deleteSportPlace,
        onNavigateToAddSportClick = viewModel::navigateToAddSport
    )
}

@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onRefreshClick: () -> Unit,
    onCardLongClick: (SportPlace) -> Unit,
    onNavigateToAddSportClick: () -> Unit
) {
    when (uiState) {
        HomeUiState.Loading -> FullScreenLoading()
        is HomeUiState.Content -> Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
        ) {
            Button(onClick = onRefreshClick) {
                Text("Refresh")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = onNavigateToAddSportClick) {
                Text("Add Sport place")
            }

            Spacer(modifier = Modifier.height(16.dp))

            SportPlaceList(
                models = uiState.sportPlaces,
                onCardLongClick = onCardLongClick
            )
        }
    }
}

@Composable
private fun SportPlaceList(
    models: List<SportPlace>,
    onCardLongClick: (SportPlace) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(models) { model ->
            SportPlaceListItem(
                model = model,
                onCardLongClick = onCardLongClick
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SportPlaceListItem(
    model: SportPlace,
    onCardLongClick: (SportPlace) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .combinedClickable(
                onClick = { },
                onLongClick = { onCardLongClick(model) },
            ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: " + model.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = "Location: " + model.location, style = MaterialTheme.typography.bodyMedium)
            model.description?.let {
                Text(text = "Description: " + it, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
