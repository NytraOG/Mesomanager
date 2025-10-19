package com.client.mesomanager.ui.composables.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.client.mesomanager.data.enums.NavigationDestination
import com.client.mesomanager.data.viewmodels.SharedViewmodel
import com.client.mesomanager.ui.screens.MesocycleDetailScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedNavHost(
    modifier: Modifier = Modifier,
    sharedViewmodel: SharedViewmodel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "",

        ) {
        composable(
            route = NavigationDestination.MESOCYCLE.route,

        ) {
            MesocycleDetailScreen(sharedViewmodel = sharedViewmodel)
        }
    }
}