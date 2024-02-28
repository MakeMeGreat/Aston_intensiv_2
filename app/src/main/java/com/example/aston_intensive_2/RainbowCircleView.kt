package com.example.aston_intensive_2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.RotateAnimation
import kotlin.properties.Delegates

class RainbowCircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAtt: Int = 0
) : View(context, attrs, defStyleAtt) {
    private var centerX by Delegates.notNull<Float>()
    private var centerY by Delegates.notNull<Float>()
    private var radius by Delegates.notNull<Float>()

    private val colors = listOf(
       Color.RED,
        Color.rgb(255, 150,0),
        Color.YELLOW,
        Color.GREEN,
        Color.CYAN,
        Color.BLUE,
        Color.MAGENTA,

    )
    private val arcAngle = 360f / colors.size
    private val paint = Paint()

    init {
        with(paint) {
            style = Paint.Style.FILL
            color = Color.RED
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircle(canvas)
    }

    private fun drawCircle(canvas: Canvas) {
        centerX = width / 2f
        centerY = width / 2f
        radius = width.coerceAtMost(height) / 2f
        for (i in colors.indices) {
            paint.color = colors[i]
            canvas.drawArc(
                centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius,
                i * arcAngle,
                arcAngle,
                true,
                paint
            )
        }
    }
}