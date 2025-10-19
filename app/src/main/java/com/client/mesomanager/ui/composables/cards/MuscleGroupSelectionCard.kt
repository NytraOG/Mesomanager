package com.client.mesomanager.ui.composables.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.client.mesomanager.data.enums.MuscleGroup
import com.client.mesomanager.ui.theme.BlueGrey80
import com.client.mesomanager.ui.theme.MesomanagerTheme

@Composable
fun MuscleGroupSelectionCard(
    muscleGroup: MuscleGroup,
    onChooseExerciseClick: () -> Unit,
    onDeleteMuscleGroup: () -> Unit
){
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(32.dp)
    ) {
        ListItem(
            headlineContent = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(muscleGroup.name, style = TextStyle(fontSize = 24.sp))
                    TextButton(
                        onClick = onChooseExerciseClick,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text("Choose an Exercise")
                    }
                }
            },
            trailingContent = {
                IconButton(
                    onClick = onDeleteMuscleGroup
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.White
                    )
                }
            },
            colors = ListItemDefaults.colors(
                containerColor = BlueGrey80
            )
        )
    }
}

@Preview
@Composable
fun PreviewMuscleGroupSelectionCard(){
    MesomanagerTheme(darkTheme = true) {
        MuscleGroupSelectionCard(
            muscleGroup = MuscleGroup.Biceps,
            onChooseExerciseClick = {},
            onDeleteMuscleGroup = {}
        )
    }
}