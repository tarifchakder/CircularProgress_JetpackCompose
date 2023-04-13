package com.tarif.circularprogressbar

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tarif.circularprogressbar.constant.AnimatedArcState
import com.tarif.circularprogressbar.constant.Rotate


@Composable
fun DeterminateProgress(
    modifier: Modifier = Modifier,
    radius: Dp = 80.dp,
    indicatorColor: Color = Color.Blue,
    trackColor: Color = Color.Blue.copy(0.2f),
    indicatorStrokeWidth: Dp = 10.dp,
    trackStrokeWidth: Dp = 10.dp,
    progress: Float = 0f,
    rotate: Rotate = Rotate.RIGHT,
    roundedBorder: Boolean = true,
    durationInMilliSecond: Int = 2000,
    startDelay: Int = 1000,
    waveAnimation: Boolean = true
) {

    val indicatorStroke = with(LocalDensity.current) {
        Stroke(width = indicatorStrokeWidth.toPx(), cap = if(roundedBorder) StrokeCap.Round else StrokeCap.Square)
    }

    val trackStroke = with(LocalDensity.current) {
        Stroke(width = trackStrokeWidth.toPx())
    }

    val strokeReverse = Stroke(trackStroke.width / 4)

    val currentState = remember {
        MutableTransitionState(AnimatedArcState.START)
            .apply { targetState = AnimatedArcState.END }
    }
    val animatedProgress = updateTransition(currentState, label = "")
    var isFinished by remember { mutableStateOf(false) }
    val animatedCircle = rememberInfiniteTransition()

    val progressAnim by animatedProgress.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = durationInMilliSecond,
                easing = LinearEasing,
                delayMillis = startDelay
            )
        }, label = ""
    ) { state ->
        when (state) {
            AnimatedArcState.START -> 0f
            AnimatedArcState.END -> {
                when(rotate) {
                    Rotate.RIGHT -> progress
                    Rotate.LEFT -> -progress
                }
            }
        }
    }

    val animatedReverse by animatedCircle.animateFloat(
        initialValue = 1.40f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse)
    )

    val animatedColor by animatedCircle.animateColor(
        initialValue = trackColor.copy(0.5f),
        targetValue = indicatorColor.copy(0.8f),
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse)
    )

    DisposableEffect(Unit) {
        isFinished = animatedProgress.currentState == animatedProgress.targetState
        onDispose {}
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2)
    ) {
        Canvas( modifier = modifier.size(radius * 2)) {

            val higherStrokeWidth = if (indicatorStroke.width > trackStroke.width) indicatorStroke.width else trackStroke.width
            val r = (size.minDimension - higherStrokeWidth) / 2

            isFinished = animatedProgress.currentState == animatedProgress.targetState

            // Track
            drawArc(
                startAngle = 0f,
                sweepAngle = 360f,
                color = trackColor,
                useCenter = false,
                topLeft = Offset(
                    (size / 2.0F).width - r,
                    (size / 2.0F).height - r
                ),
                size = Size(r * 2, r * 2),
                style = trackStroke
            )

            // Wave Animation
            if(waveAnimation && !isFinished) {
                drawCircle(
                    color = animatedColor,
                    style = strokeReverse,
                    radius = r * animatedReverse,
                )
            }

            // Indicator
            drawArc(
                color = indicatorColor,
                startAngle = 270f,
                sweepAngle = progressAnim * 360 / 100,
                useCenter = false,
                topLeft = Offset(
                    (size / 2.0F).width - r,
                    (size / 2.0F).height - r
                ),
                size = Size(r * 2, r * 2),
                style = indicatorStroke
            )
        }

        Text(
            text = progress.toInt().toString() + "%",
            color = trackColor,
            fontSize = radius.value.sp / 2,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Monospace,
        )

    }


}

