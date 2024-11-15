package com.example.canicomhandheld

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class CompassView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val needlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.FILL
    }
    private val markerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        style = Paint.Style.FILL
    }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 40f
        textAlign = Paint.Align.CENTER
    }
    private val directions = arrayOf("N", "NE", "E", "SE", "S", "SW", "W", "NW")

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = centerX * 0.8f

        // Draw compass circle
        paint.color = Color.LTGRAY
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        canvas.drawCircle(centerX, centerY, radius, paint)

        // Draw directions (N, NE, E, etc.)
        for (i in directions.indices) {
            val angle = Math.toRadians((i * 45).toDouble())
            val dx = (centerX + radius * 0.9 * cos(angle)).toFloat()
            val dy = (centerY + radius * 0.9 * sin(angle)).toFloat()
            canvas.drawText(directions[i], dx, dy + 10, textPaint)
        }

        // Draw the compass needle pointing North
        canvas.drawPath(createNeedlePath(centerX, centerY, radius), needlePaint)

        // Draw markers for "MD" and "GD" (example positions)
        drawMarker(canvas, centerX, centerY, radius, Math.toRadians(135.0), "MD", Color.YELLOW)
        drawMarker(canvas, centerX, centerY, radius, Math.toRadians(45.0), "GD", Color.GREEN)
    }

    private fun createNeedlePath(cx: Float, cy: Float, radius: Float): Path {
        val path = Path()
        path.moveTo(cx, cy - radius * 0.8f) // top of the needle
        path.lineTo(cx - 20, cy)            // bottom left of needle
        path.lineTo(cx + 20, cy)            // bottom right of needle
        path.close()
        return path
    }

    private fun drawMarker(canvas: Canvas, cx: Float, cy: Float, radius: Float, angle: Double, label: String, color: Int) {
        val dx = (cx + radius * 0.85 * cos(angle)).toFloat()
        val dy = (cy + radius * 0.85 * sin(angle)).toFloat()

        // Draw the marker circle
        markerPaint.color = color
        canvas.drawCircle(dx, dy, 15f, markerPaint)

        // Draw the marker label
        textPaint.color = Color.BLACK
        textPaint.textSize = 30f
        canvas.drawText(label, dx, dy + 40, textPaint)
    }
}
