package com.client.mesomanager.ui.composables.dialogues

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.client.mesomanager.data.entities.dtos.NewExerciseDto
import com.client.mesomanager.data.enums.ExerciseType
import com.client.mesomanager.data.enums.MuscleGroup
import com.client.mesomanager.ui.composables.menus.ExerciseTypeSelector
import com.client.mesomanager.ui.composables.menus.PrimaryMuscleGroupSelector
import com.client.mesomanager.ui.composables.menus.SecondaryMuscleGroupSelector

@Composable
fun NewExerciseDialog(
    onConfirmation: (newExercise: NewExerciseDto) -> Unit
) {
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
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                InputGroup(
                    nameTextFieldState = nameTextFieldState,
                    onPrimaryMuscleGroupSelected = { it -> selectedPrimaryMuscleGroup = it },
                    onSecondaryMuscleGroupSelected = { it -> selectedSecondaryMuscleGroup = it },
                    onExerciseTypeSelected = { it -> selectedExerciseType = it },
                    videoTextFieldState = videoTextFieldState
                )

                TextButton(
                    onClick = {
                        val newExercise = NewExerciseDto(
                            name = nameTextFieldState.text.toString(),
                            muscleGroupPrimary = selectedPrimaryMuscleGroup,
                            muscleGroupSecondary = selectedSecondaryMuscleGroup,
                            type = selectedExerciseType,
                            videoId = videoTextFieldState.text.toString()
                        )

                        onConfirmation(newExercise)
                    }
                ) {
                    Text("CREATE")
                }
            }
        }
    }
}

@Composable
fun InputGroup(
    nameTextFieldState: TextFieldState,
    onPrimaryMuscleGroupSelected: (it: MuscleGroup) -> Unit,
    onSecondaryMuscleGroupSelected: (it: MuscleGroup) -> Unit,
    onExerciseTypeSelected: (it: ExerciseType) -> Unit,
    videoTextFieldState: TextFieldState
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
        onSelected = onPrimaryMuscleGroupSelected
    )
    Spacer(modifier = Modifier.height(16.dp))
    SecondaryMuscleGroupSelector(
        onSelected = onSecondaryMuscleGroupSelected
    )
    Spacer(modifier = Modifier.height(16.dp))
    ExerciseTypeSelector(
        onSelected = onExerciseTypeSelected
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