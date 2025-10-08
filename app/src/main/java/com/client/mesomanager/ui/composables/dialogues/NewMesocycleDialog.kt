package com.client.mesomanager.ui.composables.dialogues

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.client.mesomanager.data.entities.dtos.NewMesoDto

@Composable
fun NewMesocycleDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: (dto: NewMesoDto) -> Unit
) {
    val text = rememberTextFieldState("")
    var sliderWeeksPosition by remember { mutableFloatStateOf(6f) }
    var sliderDaysPosition by remember { mutableFloatStateOf(3f) }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                InputGroup(
                    sliderDaysPosition = sliderDaysPosition,
                    sliderWeeksPosition = sliderWeeksPosition,
                    onDaysValueChanged = { it -> sliderDaysPosition = it },
                    onWeeksValueChanged = { it -> sliderWeeksPosition = it },
                    text = text
                )

                ButtonGroup(
                    text,
                    sliderDaysPosition,
                    sliderWeeksPosition,
                    onDismissRequest = onDismissRequest,
                    onConfirmation = onConfirmation
                )
            }
        }
    }
}

@Composable
fun InputGroup(
    sliderDaysPosition: Float,
    sliderWeeksPosition: Float,
    onDaysValueChanged: (it: Float) -> Unit,
    onWeeksValueChanged: (it: Float) -> Unit,
    text: TextFieldState
) {

    Column {
        Text(
            text = "YOUR NEW MESOCYCLE",
            style = TextStyle(
                fontSize = 22.sp,
            )
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            state = text,
            placeholder = { Text("Eternal Bulk Hypertrophy #7") },
            label = { Text("Mesocycle Name") },
            lineLimits = TextFieldLineLimits.SingleLine,
            inputTransformation = InputTransformation.maxLength(25)
        )
        Spacer(modifier = Modifier.height(36.dp))

        Text(text = "How many days will you train per week?")
        Spacer(modifier = Modifier.height(12.dp))
        Slider(
            value = sliderDaysPosition,
            onValueChange = { onDaysValueChanged(it) },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 3,
            valueRange = 2f..6f
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = sliderDaysPosition.toInt().toString() + " days")

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "How many weeks will you train?\n(Including deload week)")
        Spacer(modifier = Modifier.height(12.dp))
        Slider(
            value = sliderWeeksPosition,
            onValueChange = { onWeeksValueChanged(it) },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 3,
            valueRange = 4f..8f
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = sliderWeeksPosition.toInt().toString() + " weeks")
    }
}

@Composable
fun ButtonGroup(
    text: TextFieldState,
    days: Float,
    weeks: Float,
    onDismissRequest: () -> Unit,
    onConfirmation: (dto: NewMesoDto) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(
            onClick = { onDismissRequest() },
            modifier = Modifier.padding(8.dp),
        ) {
            Text("DISMISS")
        }
        TextButton(
            onClick = {
                val dto = NewMesoDto(
                    name = text.text.toString(),
                    days = days.toInt(),
                    weeks = weeks.toInt()
                )

                onConfirmation(dto)
            },
            modifier = Modifier.padding(8.dp),
        ) {
            Text("CREATE")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewMesoDialog() {
    NewMesocycleDialog(
        modifier = Modifier,
        onConfirmation = {},
        onDismissRequest = {}
    )
}