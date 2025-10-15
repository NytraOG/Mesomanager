package com.client.mesomanager.ui.composables.dialogues

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.client.mesomanager.data.enums.ExerciseType
import com.client.mesomanager.data.enums.MuscleGroup
import com.client.mesomanager.ui.composables.menus.ExerciseTypeSelector
import com.client.mesomanager.ui.composables.menus.PrimaryMuscleGroupSelector
import com.client.mesomanager.ui.composables.menus.SecondaryMuscleGroupSelector

@Composable
fun NewExerciseDialog() {

    var selectedPrimaryMuscleGroup by remember { mutableStateOf(MuscleGroup.Chest) }
    var selectedSecondaryMuscleGroup by remember { mutableStateOf(MuscleGroup.Chest) }
    var selectedExerciseType by remember { mutableStateOf(ExerciseType.Barbell) }

    val nameTextFieldState = rememberTextFieldState()
    val videoTextFieldState = rememberTextFieldState()

    Dialog(
        onDismissRequest = {}
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
                    .padding(
                        vertical = 18.dp,
                        horizontal = 12.dp
                    ),
                verticalArrangement = Arrangement.Top
            ) {
                Text("NEW EXERCISE", style = TextStyle(fontSize = 24.sp))
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .width(280.dp),
                    state = nameTextFieldState,
                    lineLimits = TextFieldLineLimits.SingleLine,
                    label = { Text("Name") },
                )
                Spacer(modifier = Modifier.height(16.dp))
                PrimaryMuscleGroupSelector(
                    onSelected = { selection ->
                        selectedPrimaryMuscleGroup = selection
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                SecondaryMuscleGroupSelector(
                    onSelected = { selection ->
                        selectedSecondaryMuscleGroup = selection
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                ExerciseTypeSelector(
                    onSelected = { selection ->
                        selectedExerciseType = selection
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .width(280.dp),
                    state = videoTextFieldState,
                    lineLimits = TextFieldLineLimits.SingleLine,
                    label = { Text("YouTube Video Id") },
                )
            }
        }
    }
}

