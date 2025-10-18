package com.client.mesomanager.ui.screens

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.data.enums.TrainingIntent
import com.client.mesomanager.ui.theme.MesomanagerTheme

@Composable
fun MesocycleDetailScreen(
    modifier: Modifier = Modifier,
    meso: Mesocycle
) {
    var selectedDay by remember { mutableIntStateOf(1) }
    val days = (1..meso.days).toList()

    Column(
        modifier = Modifier.fillMaxSize()
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

    MesomanagerTheme(darkTheme = true) {
        MesocycleDetailScreen(meso = meso)
    }
}