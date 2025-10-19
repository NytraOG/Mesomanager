package com.client.mesomanager.ui.screens

import android.annotation.SuppressLint
import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    val actionMap = mutableMapOf<Int, () -> Unit>()

    fun changeDayContentVisibility(dayToShow: Int) {

    }

    days.forEach { day ->
        actionMap[day] = { changeDayContentVisibility(day) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${sharedViewmodel.currentMeso?.name}",
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
                        actionMap[selectedDay]?.invoke()
                    },
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