package com.client.mesomanager.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.client.mesomanager.data.viewmodels.ExercisesViewModel

@Composable
fun ExercisesScreen(modifier: Modifier = Modifier,
                    viewModel: ExercisesViewModel = hiltViewModel()){

    val exercise by viewModel.selectedExercise.collectAsState()

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        LazyColumn(
            modifier = Modifier.padding(
                vertical = 32.dp,
                horizontal = 12.dp
            )
        ) {

        }
    }
}

@Composable
@Preview
fun PreviewExercisesScreen(){
    ExercisesScreen()
}