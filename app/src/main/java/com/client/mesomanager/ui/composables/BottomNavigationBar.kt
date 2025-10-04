package com.client.mesomanager.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(destination.icon, contentDescription = destination.description)
                        },
                        label = {
                            destination.label
                        }
                    )
                }
            }
        }) { contentPadding ->
        AppNavHost(navController, startDestination, modifier = Modifier.padding(contentPadding))
    }

}

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val description: String
) {
    WORKOUT("workout", "Workout", Icons.Default.PlayArrow, "View current Workout"),
    MESOCYCLES("mesocycles", "Mesocycles", Icons.Default.DateRange, "Create and view Mesocycles"),
    EXERCISES("exercises", "Exercises", Icons.Default.Edit, "Create and view Exercises"),
    MORE("more", "More", Icons.Default.Menu, "More Options"),
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