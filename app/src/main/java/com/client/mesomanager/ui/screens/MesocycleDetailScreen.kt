package com.client.mesomanager.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.enums.TrainingIntent
import com.client.mesomanager.data.viewmodels.SharedViewmodel
import com.client.mesomanager.ui.theme.MesomanagerTheme

@Composable
fun MesocycleDetailScreen(
    modifier: Modifier = Modifier,
    sharedViewmodel: SharedViewmodel
) {
    var selectedDay by remember { mutableIntStateOf(1) }
    val days = (1..(sharedViewmodel.currentMeso?.days ?: 0)).toList()

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(40.dp)
    ) {
        SingleChoiceSegmentedButtonRow {
            days.forEach { day ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = day,
                        count = day
                    ),
                    onClick = { selectedDay = day },
                    selected = day == selectedDay,
                    label = { Text("Day $day") }
                )
            }
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
    viewmodel.currentMeso = meso

    MesomanagerTheme(darkTheme = true) {
        MesocycleDetailScreen(sharedViewmodel = viewmodel)
    }
}