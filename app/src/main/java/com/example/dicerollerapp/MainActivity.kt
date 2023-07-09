package com.example.dicerollerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dicerollerapp.ui.theme.DiceRollerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerAndImage(
                        Modifier
                            .background(Color.White)
                    )
                }
            }
        }
    }
}

@Composable
fun DiceRollerAndImage(
    modifier: Modifier
) {

    var firstDiceNbOfPips by remember { mutableStateOf(1) }
    var secondDiceNbOfPips by remember { mutableStateOf(1) }
    var playerTurn by remember { mutableStateOf(1) }
    val isSecond by remember { mutableStateOf(false) }
    var enabled1 by remember { mutableStateOf(true) }
    var enabled2 by remember { mutableStateOf(false) }

    val firstDice = Dice(firstDiceNbOfPips)
    val secondDice = Dice(secondDiceNbOfPips)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        PlayerCommand(
            onClick = {
                firstDiceNbOfPips = (1..6).random()
                secondDiceNbOfPips = (1..6).random()
                enabled2 = !enabled2
                enabled1 = !enabled1
            },
            rotated = true,
            enabled2,
            playerName = "Paul"
        )

        ImageComponent(dice1 = firstDice, dice2 = secondDice, rotated = enabled2)

        PlayerCommand(
            onClick = {
                firstDiceNbOfPips = (1..6).random()
                secondDiceNbOfPips = (1..6).random()
                enabled2 = !enabled2
                enabled1 = !enabled1
            },
            rotated = false,
            enabled1,
            playerName = "Jlk"
        )
    }
}

@Composable
fun PlayerCommand(onClick: () -> Unit, rotated: Boolean, enabled: Boolean,playerName: String) {
    Column(
        modifier = if(rotated) Modifier.rotate(180f) else Modifier
    ) {
        Text(text = playerName)

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(Color(0xFF2196F3)),
            enabled = enabled
        ) {
            Text(text = stringResource(R.string.roll))
        }
    }
}

@Composable
fun ImageComponent(dice1: Dice, dice2: Dice, rotated: Boolean) {
    Row(
        modifier = if(rotated) Modifier.rotate(180f) else Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = dice1.face),
            contentDescription = dice1.pips.toString(),
            modifier = Modifier
                .fillMaxWidth(0.5f)
        )
        Image(
            painter = painterResource(id = dice2.face),
            contentDescription = dice2.pips.toString(),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview()
@Composable
fun DiceRollerAndImagePreview() {
    DiceRollerAndImage(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .wrapContentSize(Alignment.Center)
    )
}