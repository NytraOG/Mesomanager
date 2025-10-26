package com.client.mesomanager.ui.composables.buttons

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.client.mesomanager.ui.theme.MesomanagerTheme
import com.client.mesomanager.ui.theme.Red80

@Composable
fun FinalFinalizeButton(
    onConfirm: () -> Unit
){
    TextButton(
        onClick = onConfirm,
        shape = CircleShape,
        colors = ButtonColors(
            containerColor = Red80,
            contentColor = Color.White,
            disabledContainerColor = Red80.copy(alpha = 0.38f),
            disabledContentColor = Color.White
        )
    ) {
        Text("FINALIZE!")
    }
}

@Composable
@Preview
fun PreviewPersistNewMesoButton(){
    MesomanagerTheme(darkTheme = true) {
        FinalFinalizeButton(onConfirm = {})
    }
}