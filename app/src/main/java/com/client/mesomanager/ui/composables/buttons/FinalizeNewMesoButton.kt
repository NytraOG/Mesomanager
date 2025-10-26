package com.client.mesomanager.ui.composables.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.client.mesomanager.data.enums.MuscleGroup
import com.client.mesomanager.ui.composables.dialogues.AddMuscleGroupDialog
import com.client.mesomanager.ui.composables.sheets.FinalizeMesoSheet
import com.client.mesomanager.ui.theme.MesomanagerTheme
import com.client.mesomanager.ui.theme.Red80

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FinalizeNewMesoButton(
    modifier: Modifier = Modifier,
    onAddMuscleGroup: (muscleGroup: MuscleGroup) -> Unit,
    onFinalizeMeso: () -> Unit
) {
    var checked by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }

    fun closeDialog() {
        showDialog = false
    }

    if (showDialog) {
        AddMuscleGroupDialog(
            modifier = modifier,
            onDismissRequest = { closeDialog() },
            onConfirmation = {
                onAddMuscleGroup(it)
                closeDialog()
                checked = false
            }
        )
    }

    SplitButtonLayout(
        leadingButton = {
            SplitButtonDefaults.LeadingButton(
                colors = ButtonColors(
                    containerColor = Red80,
                    contentColor = Color.White,
                    disabledContainerColor = Red80.copy(alpha = 0.38f),
                    disabledContentColor = Color.White
                ),
                onClick = {
                    showBottomSheet = true
                }) {
                Icon(
                    Icons.Filled.StarRate,
                    modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                    contentDescription = "Localized description",
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Finalize Mesocycle")
            }
        },
        trailingButton = {
            val description = "Add Musclegroup"

            TooltipBox(
                positionProvider =
                    TooltipDefaults.rememberTooltipPositionProvider(
                        TooltipAnchorPosition.Above
                    ),
                tooltip = { PlainTooltip { Text(description) } },
                state = rememberTooltipState(),
            ) {
                SplitButtonDefaults.TrailingButton(
                    colors = ButtonColors(
                        containerColor = Red80,
                        contentColor = Color.White,
                        disabledContainerColor = Red80.copy(alpha = 0.38f),
                        disabledContentColor = Color.White
                    ),
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        showDialog = it
                    },
                    modifier =
                        Modifier.semantics {
                            stateDescription = if (checked) "Expanded" else "Collapsed"
                            contentDescription = description
                        },
                ) {
                    val rotation: Float by
                    animateFloatAsState(
                        targetValue = if (checked) 180f else 0f,
                        label = "Trailing Icon Rotation",
                    )
                    Icon(
                        Icons.Filled.Add,
                        modifier =
                            Modifier
                                .size(SplitButtonDefaults.TrailingIconSize)
                                .graphicsLayer {
                                    this.rotationZ = rotation
                                },
                        contentDescription = "Localized description",
                    )
                }
            }
        },
        spacing = 2.dp,
        modifier = Modifier
    )

    FinalizeMesoSheet(
        showBottomSheet = showBottomSheet,
        onDismiss = { showBottomSheet = false },
        onConfirm = {
            onFinalizeMeso()
        }
    )
    /*  DropdownMenu(expanded = checked, onDismissRequest = { checked = false }) {
          DropdownMenuItem(
              text = { Text("Edit") },
              onClick = { /* Handle edit! */ },
              leadingIcon = { Icon(Icons.Outlined.Edit, contentDescription = null) },
          )
          DropdownMenuItem(
              text = { Text("Settings") },
              onClick = { /* Handle settings! */ },
              leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
          )
          HorizontalDivider()
          DropdownMenuItem(
              text = { Text("Send Feedback") },
              onClick = { /* Handle send feedback! */ },
              leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = null) },
              trailingIcon = { Text("F11", textAlign = TextAlign.Center) },
          )
      }*/
}

@Composable
@Preview
fun PreviewFinalizeNewMesoButton() {
    MesomanagerTheme(darkTheme = true) {
        FinalizeNewMesoButton(
            onAddMuscleGroup = {},
            onFinalizeMeso = {}
        )
    }
}