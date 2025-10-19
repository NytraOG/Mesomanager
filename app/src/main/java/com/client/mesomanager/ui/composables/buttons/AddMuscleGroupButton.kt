package com.client.mesomanager.ui.composables.buttons

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.client.mesomanager.data.enums.MuscleGroup
import com.client.mesomanager.ui.composables.dialogues.AddMuscleGroupDialog

@Composable
fun AddMuscleGroupButton(
    modifier: Modifier = Modifier,
    onConfirmDialog: (muscleGroup: MuscleGroup) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    fun openDialog() {
        showDialog = true
    }

    fun closeDialog() {
        showDialog = false
    }

    if (showDialog) {
        AddMuscleGroupDialog(
            modifier = modifier,
            onDismissRequest = { closeDialog() },
            onConfirmation = {
                onConfirmDialog(it)
                closeDialog()
            }
        )
    }

    FloatingActionButton(
        onClick = { openDialog() },
        shape = CircleShape,
        containerColor = Color(0xfff34d4d),
        contentColor = Color(0xfffccece),
        modifier = modifier
    ) {
        Icon(Icons.Default.Add, "NEW MESO")
    }
}