package com.tarif.circularprogressbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SimpleCircularProgress(
    modifier: Modifier = Modifier,
    radius: Dp = 80.dp,
    progress: Float = 50f,
    maxProgress: Float = 100F,
    indicatorColor: Color = Color.Blue,
    indicatorWidth: Dp = 10.dp,
    trackColor: Color = Color.Blue.copy(0.2f),
    trackWidth: Dp = 10.dp,
    cornerRadius: Boolean = true,
    startAngle: Float = 0f,
    durationInMilliSecond: Int = 2000,
) {
    val animProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = durationInMilliSecond,
        )
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2)
    ) {
        Canvas(modifier = modifier.size(radius * 2)) {
            val canvasSize = size.minDimension
            val r = canvasSize / 2 - maxOf(trackWidth, indicatorWidth).toPx() / 2

            // Track
            drawCircle(
                color = trackColor,
                radius = r,
                center = size.center,
                style = Stroke(width = trackWidth.toPx())
            )

            // Indicator
            drawArc(
                color = indicatorColor,
                startAngle = 270f + startAngle,
                sweepAngle = (animProgress / maxProgress) * 360f,
                useCenter = false,
                topLeft = size.center - Offset(r, r),
                size = Size(r * 2, r * 2),
                style = Stroke(
                    width = indicatorWidth.toPx(),
                    cap = if (cornerRadius) StrokeCap.Round else StrokeCap.Butt
                )
            )
        }
    }
}