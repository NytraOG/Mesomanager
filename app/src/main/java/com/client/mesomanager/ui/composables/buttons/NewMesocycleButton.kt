package com.client.mesomanager.ui.composables.buttons

import android.graphics.drawable.Icon
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.client.mesomanager.data.viewmodels.MesocycleViewModel

@Composable
fun NewMesocycleButton(modifier: Modifier = Modifier,
                       viewModel: MesocycleViewModel? = null) {
    FloatingActionButton(
        onClick = {
            viewModel?.createMesocycle()
        },
        shape = CircleShape,
        containerColor = Color(0xfff34d4d),
        contentColor = Color(0xfffccece),
        modifier = modifier
        ) {
        //Icon(Icon.Def, "Large floating action button")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButton(){
    NewMesocycleButton()
}