package com.client.mesomanager.ui.composables.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.client.mesomanager.data.enums.NavigationDestination
import com.client.mesomanager.data.viewmodels.SharedViewmodel
import com.client.mesomanager.ui.screens.ExercisesScreen
import com.client.mesomanager.ui.screens.MesocycleDetailScreen
import com.client.mesomanager.ui.screens.MesocyclesListScreen
import com.client.mesomanager.ui.screens.MoreScreen
import com.client.mesomanager.ui.screens.WorkoutScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: NavigationDestination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController, startDestination = startDestination.route
    ) {
        val sharedViewmodel = SharedViewmodel()

        NavigationDestination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    NavigationDestination.WORKOUT -> WorkoutScreen()
                    NavigationDestination.MESOCYCLES -> MesocyclesListScreen(
                        navController = navController,
                        sharedViewmodel = sharedViewmodel
                    )

                    NavigationDestination.EXERCISES -> ExercisesScreen()
                    NavigationDestination.MORE -> MoreScreen()
                    NavigationDestination.MESOCYCLE -> MesocycleDetailScreen(
                        sharedViewmodel = sharedViewmodel,
                        navController = navController
                    )
                }
            }
        }
    }
}