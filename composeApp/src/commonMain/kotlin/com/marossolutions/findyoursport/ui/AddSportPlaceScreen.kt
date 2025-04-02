package com.marossolutions.findyoursport.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marossolutions.findyoursport.components.FullScreenLoading
import com.marossolutions.findyoursport.model.SportPlace
import com.marossolutions.findyoursport.viewmodel.AddSportPlaceViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun AddSportPlaceScreen(viewModel: AddSportPlaceViewModel = koinViewModel()) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    ChatGPTChatbotScreenContent(
        isLoading = isLoading,
        addSportPlaceClick = viewModel::addSportPlace
    )
}

@Composable
private fun ChatGPTChatbotScreenContent(
    isLoading: Boolean,
    addSportPlaceClick: (SportPlace) -> Unit
) {
    if (isLoading) {
        FullScreenLoading()
    } else {
        var name by remember { mutableStateOf("") }
        var location by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }

        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    addSportPlaceClick(
                        SportPlace(
                            name = name,
                            location = location,
                            description = description
                        )
                    )
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Submit")
            }
        }
    }
}
