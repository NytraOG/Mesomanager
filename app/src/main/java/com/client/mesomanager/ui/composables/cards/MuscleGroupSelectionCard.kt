package com.client.mesomanager.ui.composables.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.client.mesomanager.R
import com.client.mesomanager.data.enums.MuscleGroup
import com.client.mesomanager.ui.theme.BlueGrey80
import com.client.mesomanager.ui.theme.MesomanagerTheme
import com.client.mesomanager.ui.theme.MuscleGroupAccs
import com.client.mesomanager.ui.theme.MuscleGroupLegs
import com.client.mesomanager.ui.theme.MuscleGroupPull
import com.client.mesomanager.ui.theme.MuscleGroupPush

@Composable
fun MuscleGroupSelectionCard(
    muscleGroup: MuscleGroup,
    onChooseExerciseClick: () -> Unit,
    onDeleteMuscleGroup: () -> Unit
) {
    fun getColor(muscleGroup: MuscleGroup): Color {
        return when (muscleGroup) {
            MuscleGroup.Chest -> MuscleGroupPush
            MuscleGroup.Triceps -> MuscleGroupPush
            MuscleGroup.Shoulders -> MuscleGroupPush

            MuscleGroup.Back -> MuscleGroupPull
            MuscleGroup.Biceps -> MuscleGroupPull

            MuscleGroup.Quads -> MuscleGroupLegs
            MuscleGroup.Hamstrings -> MuscleGroupLegs
            MuscleGroup.Glutes -> MuscleGroupLegs

            MuscleGroup.Forearms -> MuscleGroupAccs
            MuscleGroup.Traps -> MuscleGroupAccs
            MuscleGroup.Calves -> MuscleGroupAccs
            MuscleGroup.Abs -> MuscleGroupAccs
        }
    }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 4.dp,
                horizontal = 4.dp
            )
            .wrapContentHeight()
            .height(85.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        ListItem(
            //modifier = Modifier.padding(vertical = 4.dp),
            headlineContent = {
                Column(
                   // modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier,
                            imageVector = ImageVector.vectorResource(R.drawable.navigation_more),
                            tint = getColor(muscleGroup),
                            contentDescription = "MUSCLEGROUP"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            modifier = Modifier.padding(start = 0.dp),
                            text = muscleGroup.name,
                            style = TextStyle(fontSize = 18.sp)
                        )
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        TextButton(
                            onClick = onChooseExerciseClick,
                            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
                            modifier = Modifier
                                .defaultMinSize(minHeight = 2.dp)
                                .wrapContentWidth(Alignment.Start)
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.onSecondary,
                                        shape = RoundedCornerShape(8.dp),
                                    )
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("Choose an Exercise")
                            }
                        }
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
fun PreviewMuscleGroupSelectionCard() {
    MesomanagerTheme(darkTheme = true) {
        MuscleGroupSelectionCard(
            muscleGroup = MuscleGroup.Biceps,
            onChooseExerciseClick = {},
            onDeleteMuscleGroup = {}
        )
    }
}