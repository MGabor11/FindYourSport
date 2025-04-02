package com.marossolutions.findyoursport

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.marossolutions.findyoursport.navigation.AppNavHost
import com.marossolutions.findyoursport.navigation.SimpleNavigator
import com.marossolutions.findyoursport.navigation.screens.ScreenAddSportPlace
import com.marossolutions.findyoursport.navigation.screens.ScreenHome
import com.marossolutions.findyoursport.theme.AppTheme
import findyoursport.composeapp.generated.resources.Res
import findyoursport.composeapp.generated.resources.add_sport_place_title
import findyoursport.composeapp.generated.resources.home_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    AppTheme {
        KoinContext {
            val simpleNavigator = koinInject<SimpleNavigator>()
            val navController: NavHostController = rememberNavController()
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                        ),
                        title = {
                            val appScreen by simpleNavigator.currentAppScreen.collectAsStateWithLifecycle()
                            val titleRes by remember {
                                derivedStateOf {
                                    when (appScreen) {
                                        is ScreenHome -> Res.string.home_title
                                        is ScreenAddSportPlace -> Res.string.add_sport_place_title
                                        else -> null
                                    }
                                }
                            }
                            titleRes?.let {
                                androidx.compose.material3.Text(stringResource(it))
                            }
                        },
                        navigationIcon = {
                            val backStack by navController.currentBackStack.collectAsStateWithLifecycle()
                            val showBackButton by remember {
                                derivedStateOf {
                                    backStack.filterNot { it.destination.route == null }.size > 1
                                }
                            }

                            if (showBackButton) {
                                IconButton(onClick = { simpleNavigator.navigateUp() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    )
                }
            ) { innerPadding ->
                AppNavHost(
                    simpleNavigator = simpleNavigator,
                    innerPadding = innerPadding,
                    navController = navController,
                )
            }
        }
    }
}