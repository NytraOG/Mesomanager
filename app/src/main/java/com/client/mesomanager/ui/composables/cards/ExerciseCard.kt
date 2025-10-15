package com.client.mesomanager.ui.composables.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.client.mesomanager.data.entities.Exercise
import com.client.mesomanager.data.enums.ExerciseType
import com.client.mesomanager.data.enums.MuscleGroup
import com.client.mesomanager.ui.theme.BlueGrey80

@Composable
fun ExerciseCard(
    exercise: Exercise,
    onDelete: () -> Unit,
    dismissState: SwipeToDismissBoxState
) {
    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {},
        content = {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                ListItem(
                    headlineContent = {
                        Column {
                            Text("${exercise.muscleGroupPrimary} - ${exercise.type}", style = TextStyle(fontSize = 16.sp) )
                            Text(exercise.name, style = TextStyle(fontSize = 24.sp) )
                        }
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = BlueGrey80
                    ),
                    leadingContent = { }
                )
            }
        }
    )
}

@Preview()
@Composable
fun PreviewExerciseCard() {
    val exercise = Exercise(
        id = 1,
        name = "Hammercurls",
        muscleGroupPrimary = MuscleGroup.Hamstrings,
        muscleGroupSecondary = MuscleGroup.Forearms,
        type = ExerciseType.BodyweightLoadable,
        videoId = "idVongVideoHer"
    )

    ExerciseCard(
        exercise = exercise,
        onDelete = {},
        dismissState = rememberSwipeToDismissBoxState()
    )
}