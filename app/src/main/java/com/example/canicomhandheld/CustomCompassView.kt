package com.example.canicomhandheld


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomCompassView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private val paintCircle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        strokeWidth = 8f
    }

    private val paintText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 36f
        textAlign = Paint.Align.CENTER
    }

    private val paintDirection = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    private val paintMarker = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = Math.min(centerX, centerY) - 20

        // Draw the outer circle
        canvas.drawCircle(centerX, centerY, radius, paintCircle)

        // Draw cardinal directions
        val directions = arrayOf("N", "E", "S", "W")
        val angles = arrayOf(0, 90, 180, 270) // Angles in degrees

        directions.forEachIndexed { index, direction ->
            val angle = Math.toRadians(angles[index].toDouble())
            val textX = (centerX + radius * Math.cos(angle)).toFloat()
            val textY = (centerY + radius * Math.sin(angle)).toFloat() + 12 // Adjust text position
            canvas.drawText(direction, textX, textY, paintText)
        }

        // Draw red direction pointer (e.g., North)
        val pointerLength = radius * 0.6
        val pointerAngle = Math.toRadians(0.0) // Angle for North
        val pointerX = (centerX + pointerLength * Math.cos(pointerAngle)).toFloat()
        val pointerY = (centerY + pointerLength * Math.sin(pointerAngle)).toFloat()
        canvas.drawLine(centerX, centerY, pointerX, pointerY, paintDirection)

        // Draw custom markers (e.g., MD and GD)
        drawMarker(canvas, centerX, centerY, radius, "MD", 270.0) // West
        drawMarker(canvas, centerX, centerY, radius, "GD", 45.0)  // North-East
    }

    private fun drawMarker(canvas: Canvas, centerX: Float, centerY: Float, radius: Float, label: String, angle: Double) {
        val markerAngle = Math.toRadians(angle)
        val markerX = (centerX + radius * 0.8 * Math.cos(markerAngle)).toFloat()
        val markerY = (centerY + radius * 0.8 * Math.sin(markerAngle)).toFloat()

        // Draw marker circle
        canvas.drawCircle(markerX, markerY, 12f, paintMarker)

        // Draw marker label
        canvas.drawText(label, markerX, markerY - 20, paintText)
    }
}
