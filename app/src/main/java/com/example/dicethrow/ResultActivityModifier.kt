package com.example.dicethrow

import android.widget.ImageView


class ResultActivityModifier {
    fun setDieImageViewByDotsCount(imageView: ImageView, dotsCount: Int) {
        var drawableNumber: Int = R.drawable.die

        when (dotsCount) {
            1 -> drawableNumber = R.drawable.die
            2 -> drawableNumber = R.drawable.die2
            3 -> drawableNumber = R.drawable.die3
            4 -> drawableNumber = R.drawable.die4
            5 -> drawableNumber = R.drawable.die5
            6 -> drawableNumber = R.drawable.die6
        }

        imageView.setImageResource(drawableNumber)
    }
}