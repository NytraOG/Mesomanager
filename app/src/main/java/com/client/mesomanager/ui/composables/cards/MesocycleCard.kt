package com.client.mesomanager.ui.composables.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.client.mesomanager.data.entities.Mesocycle
import com.client.mesomanager.ui.theme.BlueGrey80
import com.client.mesomanager.ui.theme.MesomanagerTheme

@Composable
fun MesocycleCard(
    meso: Mesocycle,
    onDelete: () -> Unit,
    dismissState: SwipeToDismissBoxState = rememberSwipeToDismissBoxState()
) {
    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            val color = when (dismissState.currentValue) {
                SwipeToDismissBoxValue.EndToStart -> Color.Red
                else -> Color.Transparent
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(start = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (color == Color.Red) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.White
                        )
                        Text(

                            text = "Delete",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        },
        content = {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(
                    //containerColor = Color.Green,
                    //contentColor = Color.White,
                )
            ) {
                ListItem(
                    headlineContent = { Text(meso.name) },
                    colors = ListItemDefaults.colors(
                        containerColor = BlueGrey80
                    )
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMesocycleCardUnswiped() {
    val meso = Mesocycle(name = "Meso 1")

    MesomanagerTheme(darkTheme = true) {
        MesocycleCard(
            meso = meso,
            onDelete = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMesocycleCardSwiped() {
    val meso = Mesocycle(name = "Meso 2")
    val swipedState = rememberSwipeToDismissBoxState()

    LaunchedEffect(Unit) {
        swipedState.snapTo(SwipeToDismissBoxValue.EndToStart)
    }

    MesocycleCard(
        meso = meso,
        onDelete = {},
        dismissState = swipedState
    )
}