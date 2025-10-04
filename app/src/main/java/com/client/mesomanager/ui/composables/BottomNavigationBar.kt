package com.client.mesomanager.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    val startDestination = Destination.WORKOUT
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

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
        //NavigationBarItem()
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
@Preview
fun NavigationPreview() {
    BottomNavigationBar()
}