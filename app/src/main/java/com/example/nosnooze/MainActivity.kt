package com.example.nosnooze

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AlarmScreen()
        }
    }
}

@Composable
fun AlarmScreen() {

    val context = LocalContext.current
    var hour by remember { mutableStateOf(8) }
    var minute by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("NoSnooze Alarm", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            val calendar = Calendar.getInstance()

            TimePickerDialog(
                context,
                { _, selectedHour, selectedMinute ->
                    hour = selectedHour
                    minute = selectedMinute

                    calendar.set(Calendar.HOUR_OF_DAY, hour)
                    calendar.set(Calendar.MINUTE, minute)
                    calendar.set(Calendar.SECOND, 0)

                    val intent = Intent(context, AlarmReceiver::class.java)

                    val pendingIntent = PendingIntent.getBroadcast(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )

                    val alarmManager =
                        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

                    alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        calendar.timeInMillis,
                        pendingIntent
                    )

                    Toast.makeText(context, "Alarm Set!", Toast.LENGTH_SHORT).show()
                },
                hour,
                minute,
                true
            ).show()

        }) {
            Text("Select Time & Set Alarm")
        }
    }
}