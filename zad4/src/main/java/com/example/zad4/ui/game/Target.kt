package com.example.zad4.ui.game

class Target     // constructor
    (
    view: com.example.zad4.ui.game.CannonView?,
    color: Int, // returns the hit reward for this Target
    val hitReward // the hit reward for this target
    : Int,
    x: Int,
    y: Int,
    width: Int,
    length: Int,
    velocityY: Float
) : com.example.zad4.ui.game.GameElement(
    view!!, color, com.example.zad4.ui.game.CannonView.TARGET_SOUND_ID, x, y, width, length,
    velocityY
)