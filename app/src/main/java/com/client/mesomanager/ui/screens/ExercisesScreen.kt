package com.client.mesomanager.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
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
import com.client.mesomanager.data.viewmodels.ExercisesViewModel
import com.client.mesomanager.ui.composables.cards.ExerciseCard
import kotlinx.coroutines.launch

@Composable
fun ExercisesScreen(modifier: Modifier = Modifier,
                    viewModel: ExercisesViewModel = hiltViewModel()){

    viewModel.loadAllExercises()
    val allExercises by viewModel.allExercises.collectAsState()

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        LazyColumn(
            modifier = Modifier.padding(
                vertical = 32.dp,
                horizontal = 12.dp
            )
        ) {
            itemsIndexed(allExercises){ index, exercise ->
                val dismissState = rememberSwipeToDismissBoxState()
                val scope = rememberCoroutineScope()

                ExerciseCard(
                    exercise = exercise,
                    onDelete = {
                        scope.launch {
                            dismissState.reset()
                            viewModel.deleteExercise(exercise)
                        }
                    },
                    dismissState = dismissState
                )
            }
        }


    }
}

@Composable
@Preview
fun PreviewExercisesScreen(){
    ExercisesScreen()
}