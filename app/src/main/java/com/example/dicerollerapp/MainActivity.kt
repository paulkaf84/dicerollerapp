package com.example.dicerollerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
                            .fillMaxSize()
                            .background(Color.White)
                            .wrapContentSize(Alignment.Center)
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
    val firstDice = getImage(firstDiceNbOfPips)
    val secondDice = getImage(secondDiceNbOfPips)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Image(
                painter = painterResource(id = firstDice),
                contentDescription = firstDiceNbOfPips.toString()
            )
            Image(
                painter = painterResource(id = secondDice),
                contentDescription = secondDiceNbOfPips.toString()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier,
            onClick = {
                firstDiceNbOfPips = (1..6).random()
                secondDiceNbOfPips = (1..6).random()
            },
            colors = ButtonDefaults.buttonColors(Color(0xFF8F0AA7))
        ){
            Text(text = stringResource(R.string.roll))
        }
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