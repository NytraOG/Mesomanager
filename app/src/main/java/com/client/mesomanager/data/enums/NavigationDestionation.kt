package com.client.mesomanager.data.enums

import androidx.annotation.DrawableRes
import com.client.mesomanager.R

enum class NavigationDestination(
    val route: String,
    val label: String,
    @DrawableRes val iconId: Int,
    val description: String,
    val isNavItem: Boolean
) {
    WORKOUT(
        "workout",
        "Workout",
        R.drawable.navigation_workout,
        "View current Workout",
        true
    ),
    MESOCYCLES(
        "mesocycles",
        "Mesocycles",
        R.drawable.navigation_mesocycle,
        "Create and view Mesocycles",
        true
    ),
    MESOCYCLE(
        "mesocycle", "Mesocycle", R.drawable.navigation_mesocycle, "Edit Mesocycle", false
    ),
    EXERCISES(
        "exercises", "Exercises", R.drawable.navigation_exercises, "Create and view Exercises", true
    ),
    MORE("more", "More", R.drawable.navigation_more, "More Options", true),
}