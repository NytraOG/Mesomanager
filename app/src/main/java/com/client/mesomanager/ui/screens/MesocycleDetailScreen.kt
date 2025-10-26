package com.client.mesomanager.ui.screens

import android.annotation.SuppressLint
import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.enums.NavigationDestination
import com.client.mesomanager.data.enums.TrainingIntent
import com.client.mesomanager.data.viewmodels.SharedViewmodel
import com.client.mesomanager.ui.composables.buttons.FinalizeNewMesoButton
import com.client.mesomanager.ui.theme.MesomanagerTheme

@Composable
fun MesocycleDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    sharedViewmodel: SharedViewmodel
) {
    val currentMeso by sharedViewmodel.currentMeso.collectAsState()
    var selectedDay by remember { mutableIntStateOf(1) }
    val days = (1..(currentMeso?.days ?: 0)).toList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "${currentMeso?.name}",
            modifier = Modifier.padding(8.dp),
            style = TextStyle(
                fontSize = 36.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        SingleChoiceSegmentedButtonRow {
            days.forEach { day ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = day,
                        count = day
                    ),
                    onClick = {
                        selectedDay = day
                    },
                    selected = day == selectedDay,
                    label = { Text("Day $day") }
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(470.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                MuscleGroupSelectionsScreen(
                    viewModel = sharedViewmodel,
                    onDeleteMuscleGroup = {
                        sharedViewmodel.removeMuslceGroupSelection(selectedDay, it)
                    },
                    day = selectedDay
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.End
        ) {
            FinalizeNewMesoButton(
                onAddMuscleGroup = { muscleGroup ->
                    sharedViewmodel.insertMuscleGroupSelection(selectedDay, muscleGroup)
                },
                onFinalizeMeso = {
                    sharedViewmodel.finalizeMesocycle()
                    navController.navigate(NavigationDestination.WORKOUT.route)
                },
                sharedViewmodel = sharedViewmodel
            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun PreviewMesocycleDetailScreen() {
    val meso = Mesocycle(
        id = 0,
        name = "Eternal Bulk #3",
        days = 4,
        weeks = 8,
        intent = TrainingIntent.Strength
    )
    val viewmodel = SharedViewmodel()
    viewmodel.setMeso(meso)

    MesomanagerTheme(darkTheme = true) {
        MesocycleDetailScreen(sharedViewmodel = viewmodel, navController = rememberNavController())
    }
}