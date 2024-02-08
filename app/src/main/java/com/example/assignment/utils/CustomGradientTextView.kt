package com.example.assignment.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Shader
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet

class CustomGradientTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {

    private val startColor = 0xFFFFFFFF.toInt() // White
    private val middleColor = 0xFFFFFFFF.toInt() // White
    private val endColor = 0xFFFFFFFF.toInt() // White

    override fun onDraw(canvas: Canvas) {
        val width = width.toFloat()
        val height = height.toFloat()

        // Define the gradient colors and positions
        val colors = intArrayOf(startColor, middleColor, endColor)
        val positions = floatArrayOf( 1f ,0.566f, 0f, ) // 0%, 56.6%, 100%

        // Create the gradient shader
        val shader = LinearGradient(0f, 0f, 0f, height, colors, positions, Shader.TileMode.CLAMP)

        // Set the shader to the paint
        paint.shader = shader

        // Call super.onDraw to draw the text
        super.onDraw(canvas)
    }
}