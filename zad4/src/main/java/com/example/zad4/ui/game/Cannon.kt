package com.example.zad4.ui.game

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import kotlin.math.cos
import kotlin.math.sin

class Cannon(
    view: com.example.zad4.ui.game.CannonView, baseRadius: Int, barrelLength: Int,
    barrelWidth: Int
) {
    private val baseRadius // Cannon base's radius
            : Int
    private val barrelLength // Cannon barrel's length
            : Int
    private val barrelEnd = Point() // endpoint of Cannon's barrel
    private var barrelAngle // angle of the Cannon's barrel
            = 0.0
    private var cannonball // the Cannon's Cannonball
            : Cannonball? = null
    private val paint = Paint() // Paint used to draw the cannon
    private val view // view containing the Cannon
            : CannonView

    // aligns the Cannon's barrel to the given angle
    fun align(barrelAngle: Double) {
        this.barrelAngle = barrelAngle
        barrelEnd.x = (barrelLength * sin(barrelAngle)).toInt()
        barrelEnd.y = (-barrelLength * cos(barrelAngle)).toInt() +
                view.screenHeight / 2
    }

    // creates and fires Cannonball in the direction Cannon points
    fun fireCannonball() {
        // calculate the Cannonball velocity's x component
        val velocityX = (CannonView.CANNONBALL_SPEED_PERCENT *
                view.screenWidth * sin(barrelAngle)).toInt()

        // calculate the Cannonball velocity's y component
        val velocityY = (CannonView.CANNONBALL_SPEED_PERCENT *
                view.screenWidth * -cos(barrelAngle)).toInt()

        // calculate the Cannonball's radius
        val radius = (view.screenHeight *
                com.example.zad4.ui.game.CannonView.CANNONBALL_RADIUS_PERCENT).toInt()

        // construct Cannonball and position it in the Cannon
        cannonball = Cannonball(
            view, Color.BLACK,
             -radius,
            view.screenHeight / 2 - radius, radius, velocityX.toFloat(),
            velocityY.toFloat()
        )
    }

    // draws the Cannon on the Canvas
    fun draw(canvas: Canvas) {
        // draw cannon barrel
        canvas.drawLine(
            0f, (view.screenHeight  / 2).toFloat(), barrelEnd.x.toFloat(),
            barrelEnd.y.toFloat(), paint
        )

        // draw cannon base
        canvas.drawCircle(
            0f, (view.screenHeight / 2).toFloat(),
            baseRadius.toFloat(), paint
        )
    }

    // returns the Cannonball that this Cannon fired
    fun getCannonball(): Cannonball? {
        return cannonball
    }

    // removes the Cannonball from the game
    fun removeCannonball() {
        cannonball = null
    }

    // constructor
    init {
        this.view = view
        this.baseRadius = baseRadius
        this.barrelLength = barrelLength
        paint.strokeWidth = barrelWidth.toFloat() // set width of barrel
        paint.color = Color.BLACK // Cannon's color is Black
        align(Math.PI / 2) // Cannon barrel facing straight right
    }
}