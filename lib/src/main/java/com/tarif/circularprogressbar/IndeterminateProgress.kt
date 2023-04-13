package com.tarif.circularprogressbar

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tarif.circularprogressbar.constant.Rotate


@Composable
fun IndeterminateProgress(
    modifier: Modifier = Modifier,
    radius: Dp = 80.dp,
    indicatorColor: Color = Color.Blue,
    trackColor: Color = Color.Blue.copy(0.2f),
    indicatorStrokeWidth: Dp = 10.dp,
    trackStrokeWidth: Dp = 10.dp,
    rotate: Rotate = Rotate.RIGHT,
    roundedBorder: Boolean = true,
    durationInMilliSecond: Int = 1400
) {

    val indicatorStroke = with(LocalDensity.current) {
        Stroke(width = indicatorStrokeWidth.toPx(), cap = if(roundedBorder) StrokeCap.Round else StrokeCap.Square)
    }

    val trackStroke = with(LocalDensity.current) {
        Stroke(width = trackStrokeWidth.toPx())
    }

    val transition = rememberInfiniteTransition()

    val animatedRestart by transition.animateFloat(
        initialValue = 0f,
        targetValue = if(rotate == Rotate.RIGHT) 360f else -360f,
        animationSpec = infiniteRepeatable(tween(durationInMilliSecond, easing = LinearEasing))
    )

    Canvas(
        modifier = modifier.size(radius * 2)
    ) {
        val higherStrokeWidth = if (indicatorStroke.width > trackStroke.width) indicatorStroke.width else trackStroke.width
        val r = (size.minDimension - higherStrokeWidth) / 2

        // Track
        drawArc(
            startAngle = 0f,
            sweepAngle = 360f,
            topLeft = Offset(
                (size / 2.0F).width -r,
                (size / 2.0F).height -r,
            ),
            color = trackColor,
            useCenter = false,
            size = Size(r * 2, r * 2),
            style = trackStroke
        )

        // Indicator
        drawArc(
            color = indicatorColor,
            startAngle = animatedRestart,
            sweepAngle = 90f,
            useCenter = false,
            topLeft = Offset(
                (size / 2.0F).width - r,
                (size / 2.0F).height - r,
            ),
            size = Size(r * 2, r * 2),
            style = indicatorStroke,
        )
    }
}