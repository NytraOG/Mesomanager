package com.client.mesomanager.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.client.mesomanager.R
import com.client.mesomanager.ui.screens.ExercisesScreen
import com.client.mesomanager.ui.screens.MesocyclesScreen
import com.client.mesomanager.ui.screens.MoreScreen
import com.client.mesomanager.ui.screens.WorkoutScreen
import com.client.mesomanager.ui.theme.MesomanagerTheme

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destination.WORKOUT
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        modifier = modifier, bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selectedDestination == index,
                        onClick = {
                        navController.navigate(route = destination.route)
                        selectedDestination = index
                    }, icon = {
                        val iconVector = ImageVector.vectorResource(destination.iconId)
                        Icon(iconVector, contentDescription = destination.description)
                    }, label = {
                        destination.label
                    })
                }
            }
        }) { contentPadding ->
        AppNavHost(navController, startDestination, modifier = Modifier.padding(contentPadding))
    }
}

enum class Destination(
    val route: String,
    val label: String,
    @DrawableRes val iconId: Int,
    val description: String
) {
    WORKOUT("workout", "Workout", R.drawable.navigation_workout, "View current Workout"),
    MESOCYCLES("mesocycles", "Mesocycles", R.drawable.navigation_mesocycle, "Create and view Mesocycles"),
    EXERCISES("exercises", "Exercises", R.drawable.navigation_exercises, "Create and view Exercises"),
    MORE("more", "More", R.drawable.navigation_more, "More Options"),
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.WORKOUT -> WorkoutScreen()
                    Destination.MESOCYCLES -> MesocyclesScreen()
                    Destination.EXERCISES -> ExercisesScreen()
                    Destination.MORE -> MoreScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NavigationPreview() {
    MesomanagerTheme(darkTheme = true) {
        BottomNavigationBar()
    }
}