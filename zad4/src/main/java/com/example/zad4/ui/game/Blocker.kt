package com.example.zad4.ui.game

class Blocker     // constructor
    (
    view: com.example.zad4.ui.game.CannonView?,
    color: Int, // returns the miss penalty for this Blocker
    val missPenalty // the miss penalty for this Blocker
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