package com.example.aston_intensive_2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.TextView

class TextHolderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAtt: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAtt) {

    private val paint = Paint()
    private val textSizef = 50f
    private val canvas = Canvas()
    private var text = ""

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
            paint.style = Paint.Style.FILL
            paint.color = Color.BLACK
            paint.textSize = textSizef
        canvas.drawText(text, 10f, (height + textSizef) / 2f, paint)
    }

    fun drawText(string: String) {
        text = string
        canvas.drawText(string ,10f, (height + textSizef) / 2f, paint)
        invalidate()
        requestLayout()
    }
}