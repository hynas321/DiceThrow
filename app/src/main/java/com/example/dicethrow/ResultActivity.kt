package com.example.dicethrow

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class ResultActivity : AppCompatActivity() {
    private lateinit var resultActivityModifier: ResultActivityModifier
    private lateinit var diceDotsSumValue: TextView
    private lateinit var imageViewDieOne: ImageView
    private lateinit var imageViewDieTwo: ImageView
    private lateinit var imageViewDieThree: ImageView

    private lateinit var diceCount: DiceCount
    private val maximumDotsNumber: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        diceCount = intent.getSerializableExtra("diceCount") as DiceCount

        resultActivityModifier = ResultActivityModifier()
        diceDotsSumValue = findViewById(R.id.result_dice_dots_sum_value)
        imageViewDieOne = findViewById(R.id.result_die_one)
        imageViewDieTwo = findViewById(R.id.result_die_two)
        imageViewDieThree = findViewById(R.id.result_die_three)

        val dieOneDotsCount = Random.nextInt(maximumDotsNumber)
        val dieTwoDotsCount = Random.nextInt(maximumDotsNumber)
        val dieThreeDotsCount = Random.nextInt(maximumDotsNumber)
        var diceDotsSum = 0


        when (diceCount) {
            DiceCount.ONE -> {
                diceDotsSum = dieOneDotsCount
                diceDotsSumValue.text = diceDotsSum.toString()

                resultActivityModifier.setDieImageViewByDotsCount(
                    imageViewDieOne,
                    dieOneDotsCount
                )

                imageViewDieOne.visibility = View.VISIBLE
            }
            DiceCount.TWO -> {
                diceDotsSum = dieTwoDotsCount + dieThreeDotsCount
                diceDotsSumValue.text = diceDotsSum.toString()

                resultActivityModifier
                    .setDieImageViewByDotsCount(imageViewDieOne, dieOneDotsCount)

                resultActivityModifier
                    .setDieImageViewByDotsCount(imageViewDieTwo, dieTwoDotsCount)

                imageViewDieTwo.visibility = View.VISIBLE
                imageViewDieThree.visibility = View.VISIBLE
            }
            DiceCount.THREE -> {
                diceDotsSum = dieOneDotsCount + dieTwoDotsCount + dieThreeDotsCount
                diceDotsSumValue.text = diceDotsSum.toString()

                resultActivityModifier
                    .setDieImageViewByDotsCount(imageViewDieOne, dieOneDotsCount)

                resultActivityModifier
                    .setDieImageViewByDotsCount(imageViewDieTwo, dieTwoDotsCount)

                resultActivityModifier
                    .setDieImageViewByDotsCount(imageViewDieThree, dieThreeDotsCount)

                imageViewDieOne.visibility = View.VISIBLE
                imageViewDieTwo.visibility = View.VISIBLE
                imageViewDieThree.visibility = View.VISIBLE
            }
        }
    }
}