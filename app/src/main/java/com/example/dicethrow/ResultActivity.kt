package com.example.dicethrow

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class ResultActivity : AppCompatActivity() {
    private lateinit var resultActivityModifier: ResultActivityModifier
    private lateinit var textViewDotsNumber: TextView
    private lateinit var textViewDiceDotsSumValue: TextView
    private lateinit var imageViewDieOne: ImageView
    private lateinit var imageViewDieTwo: ImageView
    private lateinit var imageViewDieThree: ImageView
    private lateinit var buttonGoBack: Button

    private lateinit var diceCount: DiceCount

    private val maximumDotsNumber: Int = 6
    private val oneDiceAnimationYOffset: Float = -350f
    private val twoDiceAnimationYOffset: Float = -700f
    private val threeDiceAnimationYOffset: Float = -400f
    private val diceAnimationDurationMs: Long = 1300
    private val diceAnimationRotation: Float = 180f

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val receivedExtras = intent.extras

        if (receivedExtras != null) {
            diceCount = receivedExtras.getSerializable("diceCount") as DiceCount
        }

        resultActivityModifier = ResultActivityModifier()
        textViewDotsNumber = findViewById(R.id.result_textView_dots_number)
        textViewDiceDotsSumValue = findViewById(R.id.result_dice_dots_sum_value)
        imageViewDieOne = findViewById(R.id.result_die_one)
        imageViewDieTwo = findViewById(R.id.result_die_two)
        imageViewDieThree = findViewById(R.id.result_die_three)
        buttonGoBack = findViewById(R.id.result_button_go_back)

        val dieOneDotsCount = Random.nextInt(1, maximumDotsNumber)
        val dieTwoDotsCount = Random.nextInt(1, maximumDotsNumber)
        val dieThreeDotsCount = Random.nextInt(1, maximumDotsNumber)
        val diceDotsSum: Int

        when (diceCount) {
            DiceCount.ONE -> {
                diceDotsSum = dieOneDotsCount

                resultActivityModifier.setDieImageViewByDotsCount(
                    imageViewDieOne,
                    dieOneDotsCount
                )

                imageViewDieOne.visibility = View.VISIBLE

                imageViewDieOne.animate().setDuration(diceAnimationDurationMs)
                    .translationYBy(oneDiceAnimationYOffset).rotation(diceAnimationRotation)
            }
            DiceCount.TWO -> {
                diceDotsSum = dieTwoDotsCount + dieThreeDotsCount

                resultActivityModifier.setDieImageViewByDotsCount(imageViewDieTwo, dieTwoDotsCount)
                resultActivityModifier.setDieImageViewByDotsCount(imageViewDieThree, dieThreeDotsCount)

                imageViewDieTwo.visibility = View.VISIBLE
                imageViewDieThree.visibility = View.VISIBLE

                imageViewDieTwo.animate().setDuration(diceAnimationDurationMs)
                    .translationYBy(twoDiceAnimationYOffset).rotation(diceAnimationRotation)

                imageViewDieThree.animate().setDuration(diceAnimationDurationMs)
                    .translationYBy(twoDiceAnimationYOffset).rotation(diceAnimationRotation)
            }
            DiceCount.THREE -> {
                diceDotsSum = dieOneDotsCount + dieTwoDotsCount + dieThreeDotsCount

                resultActivityModifier.setDieImageViewByDotsCount(imageViewDieOne, dieOneDotsCount)
                resultActivityModifier.setDieImageViewByDotsCount(imageViewDieTwo, dieTwoDotsCount)
                resultActivityModifier.setDieImageViewByDotsCount(imageViewDieThree, dieThreeDotsCount)

                imageViewDieOne.visibility = View.VISIBLE
                imageViewDieTwo.visibility = View.VISIBLE
                imageViewDieThree.visibility = View.VISIBLE

                imageViewDieOne.animate().setDuration(diceAnimationDurationMs)
                    .translationYBy(threeDiceAnimationYOffset).rotation(diceAnimationRotation)

                imageViewDieTwo.animate().setDuration(diceAnimationDurationMs)
                    .translationYBy(threeDiceAnimationYOffset).rotation(diceAnimationRotation)

                imageViewDieThree.animate().setDuration(diceAnimationDurationMs)
                    .translationYBy(threeDiceAnimationYOffset).rotation(diceAnimationRotation)
            }
        }

        buttonGoBack.setOnClickListener {
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }

        @Suppress("DEPRECATION")
        Handler().postDelayed({
            buttonGoBack.visibility = View.VISIBLE
            textViewDiceDotsSumValue.text = diceDotsSum.toString()
        }, diceAnimationDurationMs)
    }

    override fun onBackPressed() {
        val intentShake = Intent(this, ShakeActivity::class.java)
        val extras = Bundle()

        extras.putSerializable("diceCount", diceCount)
        intentShake.putExtras(extras)
        startActivity(intentShake)
    }
}