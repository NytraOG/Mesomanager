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
import androidx.compose.foundation.text.input.TextFieldState
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
import com.client.mesomanager.ui.composables.buttons.MassMeasurementUnitRadioButton

@Composable
fun NewMesocycleDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    val text = rememberTextFieldState("")

    Dialog(
        onDismissRequest = {}
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                InputGroup(text)

                ButtonGroup(
                    onDismissRequest = onDismissRequest,
                    onConfirmation = onConfirmation
                )
            }
        }
    }
}

@Composable
fun InputGroup(text: TextFieldState) {
    var sliderPosition by remember { mutableFloatStateOf(4f) }

    Column {
        OutlinedTextField(
            state = text,
            placeholder = { Text("Eternal Bulk Hypertrophy") },
            label = { Text("Mesocycle Name") }
        )
        Spacer(modifier = Modifier.height(36.dp))

        Text(text = "How many weeks will you train?\n(Including deload week)")
        Spacer(modifier = Modifier.height(12.dp))
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 3,
            valueRange = 4f..8f
        )
        Text(text = sliderPosition.toInt().toString() + " weeks")

        Spacer(modifier = Modifier.height(36.dp))
        MassMeasurementUnitRadioButton()
    }
}

@Composable
fun ButtonGroup(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
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
            Text("Dismiss")
        }
        TextButton(
            onClick = { onConfirmation() },
            modifier = Modifier.padding(8.dp),
        ) {
            Text("Confirm")
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