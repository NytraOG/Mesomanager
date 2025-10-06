package com.client.mesomanager.ui.composables.dialogues

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.client.mesomanager.data.viewmodels.MesocycleViewModel

@Composable
fun NewMesocycleDialog(
    modifier: Modifier = Modifier,
    viewModel: MesocycleViewModel? = null
) {
    val text = rememberTextFieldState("Hello")

    Dialog(
        onDismissRequest = {}
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Exercises!")
                Spacer(modifier = Modifier.size(30.dp))
                Text("Exercises 2!")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewMesoDialog() {
    NewMesocycleDialog()
}