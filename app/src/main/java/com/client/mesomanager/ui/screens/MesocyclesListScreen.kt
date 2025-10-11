package com.client.mesomanager.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.viewmodels.MesocycleViewModel
import com.client.mesomanager.ui.composables.buttons.NewMesocycleButton
import com.client.mesomanager.ui.composables.cards.MesocycleCard
import kotlinx.coroutines.launch

@Composable
fun MesocyclesListScreen(
    modifier: Modifier = Modifier,
    viewModel: MesocycleViewModel = hiltViewModel()
) {
    viewModel.getAllMesocycles()
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
                    dismissState
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
            }
        )
    }
}

@Composable
@Preview
fun PreviewMesocyclesScreen() {
    MesocyclesListScreen()
}