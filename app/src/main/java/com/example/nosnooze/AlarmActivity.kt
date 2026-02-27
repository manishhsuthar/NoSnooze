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
import com.example.nosnooze.ui.theme.NoSnoozeTheme

class AlarmActivity : ComponentActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.alarm_sound)
            mediaPlayer?.isLooping = true
            mediaPlayer?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setContent {
            NoSnoozeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MathChallenge {
                        mediaPlayer?.stop()
                        mediaPlayer?.release()
                        mediaPlayer = null
                        finish()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

@Composable
fun MathChallenge(onSuccess: () -> Unit) {

    var correctCount by remember { mutableIntStateOf(0) }

    var num1 by remember { mutableIntStateOf((1..50).random()) }
    var num2 by remember { mutableIntStateOf((1..50).random()) }

    var userAnswer by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Solve 3 Math Problems to Stop Alarm", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(20.dp))

        Text("$num1 + $num2 = ?", style = MaterialTheme.typography.headlineMedium)

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

        Text("Correct Answers: $correctCount / 3", style = MaterialTheme.typography.bodyLarge)
    }
}
