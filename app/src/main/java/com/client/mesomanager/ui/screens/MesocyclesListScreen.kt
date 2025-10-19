package com.client.mesomanager.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.client.mesomanager.data.enums.NavigationDestination
import com.client.mesomanager.data.viewmodels.MesocycleViewModel
import com.client.mesomanager.data.viewmodels.SharedViewmodel
import com.client.mesomanager.ui.composables.buttons.NewMesocycleButton
import com.client.mesomanager.ui.composables.cards.MesocycleCard
import kotlinx.coroutines.launch

@Composable
fun MesocyclesListScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    sharedViewmodel: SharedViewmodel,
    viewModel: MesocycleViewModel = hiltViewModel()
) {
    viewModel.loadAllMesocycles()
    val allMesocycles by viewModel.mesocycles.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .padding(
                    vertical = 32.dp,
                    horizontal = 12.dp
                )
        ) {
            itemsIndexed(allMesocycles) { index, meso ->
                val dismissState = rememberSwipeToDismissBoxState()
                val scope = rememberCoroutineScope()

                MesocycleCard(
                    meso = meso,
                    onDelete = {
                        scope.launch {
                            dismissState.reset()
                            viewModel.deleteMesocycle(meso)
                        }
                    },
                    cardOnClick = {
                        sharedViewmodel.setMeso(meso)
                        navController.navigate(NavigationDestination.MESOCYCLE.route)
                    },
                    dismissState = dismissState
                )
            }
        }

        NewMesocycleButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 24.dp,
                    bottom = 150.dp
                ),
            onConfirmDialog = { dto ->
                viewModel.createMesocycle(dto)
                sharedViewmodel.setMeso(viewModel.mesocycle.value)
                navController.navigate(NavigationDestination.MESOCYCLE.route)
            }
        )
    }
}

@Composable
@Preview
fun PreviewMesocyclesScreen() {
    //MesocyclesListScreen()
}