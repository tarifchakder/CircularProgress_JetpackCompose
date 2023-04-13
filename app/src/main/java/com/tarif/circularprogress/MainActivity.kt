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
                    SimpleCircularProgress()

                   // Spacer(modifier = Modifier.padding(10.dp))

                  //  determinateProgressSample()

                  //  Spacer(modifier = Modifier.padding(10.dp))

                  //  infiniteProgressSample()

                  //  Spacer(modifier = Modifier.padding(10.dp))

                }
            }
        }
    }
}

@Composable
fun SimpleCircularProgress(){
    val progress  = remember { mutableStateOf(0f) }
    LaunchedEffect(true) {
        delay(2000)
        for (i in 0..5) {
            delay(1500)
            progress.value = progress.value + 20
        }
    }

    SimpleCircularProgress(
        progress = progress.value,
    )

}

@Composable
fun infiniteProgressSample() {
    IndeterminateProgress(
        modifier = Modifier
    )
}

@Composable
fun determinateProgressSample() {
    DeterminateProgress(
        modifier = Modifier,
        progress = 100f,
        rotate = Rotate.RIGHT,
        roundedBorder = true,
        durationInMilliSecond = 2000,
        startDelay = 1000,
        radius = 80.dp,
        waveAnimation = true
    )
}