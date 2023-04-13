package com.tarif.circularprogress

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tarif.circularprogress.ui.theme.AppTheme
import com.tarif.circularprogressbar.*
import com.tarif.circularprogressbar.constant.Rotate
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //  SimpleCircularProgress()

                    // Spacer(modifier = Modifier.padding(10.dp))

                    determinateProgressSample()

                    //  Spacer(modifier = Modifier.padding(10.dp))

                    // infiniteProgressSample()

                }
            }
        }
    }
}

@Composable
fun SimpleCircularProgress() {
    val progress = remember { mutableStateOf(0f) }
    LaunchedEffect(true) {
        delay(2000)
        for (i in 0..5) {
            delay(1500)
            progress.value = progress.value + 20
        }
    }

    SimpleCircularProgress(
        radius = 80.dp,
        progress = progress.value,
        maxProgress = 100F,
        indicatorColor = Color.Blue,
        indicatorWidth = 10.dp,
        trackColor = Color.Blue.copy(0.2f),
        trackWidth = 10.dp,
        cornerRadius = true,
        startAngle = 0f,
        durationInMilliSecond = 2000
    )

}

@Composable
fun infiniteProgressSample() {
    IndeterminateProgress(
        modifier = Modifier,
        radius = 80.dp,
        indicatorColor = Color.Blue,
        trackColor = Color.Blue.copy(0.2f),
        indicatorStrokeWidth = 10.dp,
        trackStrokeWidth = 10.dp,
        rotate = Rotate.RIGHT,
        roundedBorder = true,
        durationInMilliSecond = 1400
    )
}

@Composable
fun determinateProgressSample() {
    DeterminateProgress(
        modifier = Modifier,
        radius = 80.dp,
        indicatorColor = Color.Blue,
        trackColor = Color.Blue.copy(0.2f),
        indicatorStrokeWidth = 10.dp,
        trackStrokeWidth = 10.dp,
        progress = 90f,
        rotate = Rotate.RIGHT,
        roundedBorder = true,
        durationInMilliSecond = 10000,
        startDelay = 3000,
        waveAnimation = true
    )
}