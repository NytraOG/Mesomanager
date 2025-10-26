package com.client.mesomanager.ui.composables.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.client.mesomanager.data.enums.MassUnit

@Composable
fun MassMeasurementUnitRadioButton(
    modifier: Modifier = Modifier,
    onSelected: (selectedOption: MassUnit) -> Unit
) {
    val radioOptions = listOf(MassUnit.Kg, MassUnit.Lb)
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Row(
        modifier = modifier
            .selectableGroup()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        radioOptions.forEach { massUnit ->
            Row(
                Modifier
                    .height(56.dp)
                    .selectable(
                        selected = (massUnit == selectedOption),
                        onClick = {
                            onOptionSelected(massUnit)
                            onSelected(massUnit)
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (massUnit == selectedOption),
                        onClick = null
                    )
                    Text(
                        text = massUnit.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMassMeasurementUnitRadios() {
    MassMeasurementUnitRadioButton(
        onSelected = {}
    )
}