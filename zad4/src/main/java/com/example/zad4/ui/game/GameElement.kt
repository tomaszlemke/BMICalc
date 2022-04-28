package com.example.zad4.ui.game

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

open class GameElement(
    view: com.example.zad4.ui.game.CannonView, color: Int, soundId: Int, x: Int,
    y: Int, width: Int, length: Int, velocityY: Float
) {
    protected var view // the view that contains this GameElement
            : com.example.zad4.ui.game.CannonView
    protected var paint = Paint() // Paint to draw this GameElement
    var shape // the GameElement's rectangular bounds
            : Rect
    private var velocityY // the vertical velocity of this GameElement
            : Float

    // update GameElement position and check for wall collisions
    open fun update(interval: Double) {
        // update vertical position
        shape.offset(0, (velocityY * interval).toInt())

        // if this GameElement collides with the wall, reverse direction
        if (shape.top < 0 && velocityY < 0 ||
            shape.bottom > view.screenHeight && velocityY > 0
        ) velocityY *= -1f // reverse this GameElement's velocity
    }

    // draws this GameElement on the given Canvas
    open fun draw(canvas: Canvas) {
        canvas.drawRect(shape, paint)
    } // plays the sound that corresponds to this type of GameElement

    //    public void playSound() {
    //        view.playSound(soundId);
    //    }
    // private int soundId; // the sound associated with this GameElement
    // public constructor
    init {
        this.view = view
        // shape2 = new
        shape = Rect(x, y, x + width, y + length) // set bounds
        // this.soundId = soundId;
        this.velocityY = velocityY
    }
}