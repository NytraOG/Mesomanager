package com.client.mesomanager.ui.composables.sheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.client.mesomanager.ui.composables.buttons.FinalFinalizeButton
import com.client.mesomanager.ui.composables.buttons.MassMeasurementUnitRadioButton
import com.client.mesomanager.ui.composables.buttons.TrainingIntentRadioButton
import com.client.mesomanager.ui.theme.MesomanagerTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalizeMesoSheet(
    showBottomSheet: Boolean,
    onDismiss: () -> Unit,
    onConfirmFinalization: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState()
) {
    val scope = rememberCoroutineScope()

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onDismiss()
                    }
                }
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TrainingIntentRadioButton(
                    onIntentSelected = {}
                )
                HorizontalDivider(thickness = 2.dp)
                MassMeasurementUnitRadioButton(
                    onSelected = {selectedOption ->

                    }
                )
                HorizontalDivider(thickness = 2.dp)
                Spacer(Modifier.height(12.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {
                    FinalFinalizeButton(onConfirm = onConfirmFinalization)
                }
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = false)
@Composable
fun PreviewFinalizeMesoSheet() {
    val sheetState = SheetState(
        skipPartiallyExpanded = false,
        positionalThreshold = { return@SheetState 0f },
        velocityThreshold = { return@SheetState 0f },
        initialValue = SheetValue.Expanded
    )

    MesomanagerTheme(darkTheme = true) {
        FinalizeMesoSheet(
            onDismiss = {},
            onConfirmFinalization = {},
            showBottomSheet = true,
            sheetState = sheetState
        )
    }
}