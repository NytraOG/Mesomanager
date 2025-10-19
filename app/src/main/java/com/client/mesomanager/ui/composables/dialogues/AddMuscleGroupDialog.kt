package com.client.mesomanager.ui.composables.dialogues

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.client.mesomanager.data.enums.MuscleGroup
import com.client.mesomanager.ui.composables.menus.MuscleGroupSelector
import com.client.mesomanager.ui.theme.MesomanagerTheme

@Composable
fun AddMuscleGroupDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: (muscleGroup: MuscleGroup) -> Unit){
    var selectedMuscleGroup by remember { mutableStateOf(MuscleGroup.Chest) }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
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
                MuscleGroupSelector(
                    label = "Muscle Group",
                    onSelected = { it ->
                        selectedMuscleGroup = it
                    }
                )

                TextButton(
                    onClick = {
                        onConfirmation(selectedMuscleGroup)
                    }
                ) {
                    Text("ADD")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAddMuscleGroupDialog(){
    MesomanagerTheme(darkTheme = true) {
        AddMuscleGroupDialog(
            onConfirmation = {},
            onDismissRequest = {}
        )
    }
}