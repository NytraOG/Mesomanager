package com.client.mesomanager.ui.composables.navigation

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
import androidx.navigation.compose.rememberNavController
import com.client.mesomanager.data.enums.NavigationDestination
import com.client.mesomanager.ui.theme.MesomanagerTheme

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = NavigationDestination.WORKOUT
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        modifier = modifier, bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                NavigationDestination.entries.forEachIndexed { index, destination ->
                    if(destination.isNavItem)
                    {
                        NavigationBarItem(selectedDestination == index, onClick = {
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
            }
        }) { contentPadding ->
        AppNavHost(navController, startDestination, modifier = Modifier.padding(contentPadding))
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