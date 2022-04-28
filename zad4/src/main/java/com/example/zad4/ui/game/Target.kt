package com.example.zad4.ui.game

class Target     // constructor
    (
    view: CannonView?,
    color: Int, // returns the hit reward for this Target
    val hitReward // the hit reward for this target
    : Int,
    x: Int,
    y: Int,
    width: Int,
    length: Int,
    velocityY: Float
) : com.example.zad4.ui.game.GameElement(
    view!!, color, x, y, width, length,
    velocityY
)