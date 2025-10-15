package com.client.mesomanager.ui.composables.menus

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.client.mesomanager.data.enums.ExerciseType
import com.client.mesomanager.data.enums.MuscleGroup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseTypeSelector(
    onSelected: (selection: ExerciseType) -> Unit
) {
    val (allowExpanded, setExpanded) = remember { mutableStateOf(false) }
    val expanded = allowExpanded
    val textFieldState = rememberTextFieldState("Choose Exercise Type")

    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = setExpanded
    ) {
        OutlinedTextField(
            modifier = Modifier
                .width(280.dp)
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable),
            state = textFieldState,
            readOnly = true,
            lineLimits = TextFieldLineLimits.SingleLine,
            label = { Text("Exercise Type") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded,
                    modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.SecondaryEditable),
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        ExposedDropdownMenu(
            modifier = Modifier.heightIn(max = 280.dp),
            expanded = expanded,
            onDismissRequest = { setExpanded(false) },
        ) {
            ExerciseType.entries.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option.toString()
                        )
                    },
                    onClick = {
                        textFieldState.setTextAndPlaceCursorAtEnd(option.toString())
                        setExpanded(false)
                        onSelected(option)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}