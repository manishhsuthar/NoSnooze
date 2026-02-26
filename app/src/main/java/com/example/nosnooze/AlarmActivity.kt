package com.example.nosnooze

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class AlarmActivity : ComponentActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm_sound)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        setContent {
            MathChallenge {
                mediaPlayer.stop()
                mediaPlayer.release()
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }
}

@Composable
fun MathChallenge(onSuccess: () -> Unit) {

    var correctCount by remember { mutableStateOf(0) }

    var num1 by remember { mutableStateOf((1..50).random()) }
    var num2 by remember { mutableStateOf((1..50).random()) }

    var userAnswer by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Solve 3 Math Problems to Stop Alarm")

        Spacer(modifier = Modifier.height(20.dp))

        Text("$num1 + $num2 = ?")

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = userAnswer,
            onValueChange = { userAnswer = it },
            label = { Text("Your Answer") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            if (userAnswer.toIntOrNull() == num1 + num2) {
                correctCount++

                if (correctCount == 3) {
                    onSuccess()
                } else {
                    num1 = (1..50).random()
                    num2 = (1..50).random()
                    userAnswer = ""
                }
            } else {
                userAnswer = ""
            }
        }) {
            Text("Submit")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Correct Answers: $correctCount / 3")
    }
}