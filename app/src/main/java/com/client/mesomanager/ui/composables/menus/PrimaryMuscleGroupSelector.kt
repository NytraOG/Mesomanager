package com.client.mesomanager.ui.composables.menus

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.client.mesomanager.data.enums.MuscleGroup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryMuscleGroupSelector(
    onSelected: (selection: MuscleGroup) -> Unit){
    MuscleGroupSelector(
        label = "Primary Muscle Group",
        onSelected = onSelected
    )
}