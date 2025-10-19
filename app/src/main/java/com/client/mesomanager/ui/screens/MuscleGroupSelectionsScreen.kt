package com.client.mesomanager.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.enums.TrainingIntent
import com.client.mesomanager.data.viewmodels.MesocycleViewModel
import com.client.mesomanager.ui.theme.MesomanagerTheme

@Composable
fun MuscleGroupSelectionsScreen(
    modifier: Modifier = Modifier,
    viewModel: MesocycleViewModel
){
    val meso = viewModel.mesocycle.collectAsState()

    MuscleGroupSelectionsScreen(meso = meso.value)
}

@Composable
fun MuscleGroupSelectionsScreen(meso: Mesocycle?){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}

@Preview
@Composable
fun PreviewMuscleGroupSelectionsScreen(){
    val meso = Mesocycle(name = "Meso 1", days = 4, weeks = 6, intent = TrainingIntent.Strength)

    MesomanagerTheme(darkTheme = true) {
        MuscleGroupSelectionsScreen(meso)
    }
}