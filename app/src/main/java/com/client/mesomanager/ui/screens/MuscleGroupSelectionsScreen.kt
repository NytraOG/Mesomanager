package com.client.mesomanager.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.enums.MuscleGroup
import com.client.mesomanager.data.enums.TrainingIntent
import com.client.mesomanager.data.viewmodels.SharedViewmodel
import com.client.mesomanager.ui.composables.cards.MuscleGroupSelectionCard
import com.client.mesomanager.ui.theme.MesomanagerTheme

@Composable
fun MuscleGroupSelectionsScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewmodel,
    day: Int
) {
    val meso by viewModel.currentMeso.collectAsState()
    val allMuscleGroups by viewModel.muscleGroupSelectionOfDay.collectAsState()
    val muscleGroupsToShow = allMuscleGroups[day] ?: emptyList()

    MuscleGroupSelectionsScreen(
        meso = meso,
        muscleGroupsToShow = muscleGroupsToShow,
        onChooseExerciseClick = {},
        onDeleteMuscleGroup = { muscleGroup ->
            //viewModel.removeMuslceGroupSelection()
        })
}

@Composable
fun MuscleGroupSelectionsScreen(
    meso: Mesocycle?,
    muscleGroupsToShow: List<MuscleGroup>,
    onDeleteMuscleGroup: (toDelete: MuscleGroup) -> Unit,
    onChooseExerciseClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            muscleGroupsToShow.forEachIndexed { index, group ->
                MuscleGroupSelectionCard(
                    muscleGroup = group,
                    onChooseExerciseClick = onChooseExerciseClick,
                    onDeleteMuscleGroup = {
                        onDeleteMuscleGroup(group)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewMuscleGroupSelectionsScreen() {
    val meso = Mesocycle(name = "Meso 1", days = 4, weeks = 6, intent = TrainingIntent.Strength)
    val muscleGroups = listOf(MuscleGroup.Biceps, MuscleGroup.Chest, MuscleGroup.Shoulders)

    MesomanagerTheme(darkTheme = true) {
        MuscleGroupSelectionsScreen(meso, muscleGroups, {}, {})
    }
}