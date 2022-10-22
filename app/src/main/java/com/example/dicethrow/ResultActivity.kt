package com.example.dicethrow

import android.content.Intent
import android.os.Bundle
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
    private val twoDiceAnimationYOffset: Float = -600f
    private val threeDiceAnimationYOffset: Float = -400f

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
        var diceDotsSum = 0

        when (diceCount) {
            DiceCount.ONE -> {
                diceDotsSum = dieOneDotsCount
                textViewDiceDotsSumValue.text = diceDotsSum.toString()

                resultActivityModifier.setDieImageViewByDotsCount(
                    imageViewDieOne,
                    dieOneDotsCount
                )

                imageViewDieOne.visibility = View.VISIBLE

                imageViewDieOne.animate().setDuration(1500)
                    .translationYBy(oneDiceAnimationYOffset).rotation(180f)
            }
            DiceCount.TWO -> {
                diceDotsSum = dieTwoDotsCount + dieThreeDotsCount
                textViewDiceDotsSumValue.text = diceDotsSum.toString()

                resultActivityModifier.setDieImageViewByDotsCount(imageViewDieTwo, dieOneDotsCount)
                resultActivityModifier.setDieImageViewByDotsCount(imageViewDieThree, dieTwoDotsCount)

                imageViewDieTwo.visibility = View.VISIBLE
                imageViewDieThree.visibility = View.VISIBLE

                imageViewDieTwo.animate().setDuration(1500)
                    .translationYBy(twoDiceAnimationYOffset).rotation(180f)
                imageViewDieThree.animate().setDuration(1500)
                    .translationYBy(twoDiceAnimationYOffset).rotation(180f)
            }
            DiceCount.THREE -> {
                diceDotsSum = dieOneDotsCount + dieTwoDotsCount + dieThreeDotsCount
                textViewDiceDotsSumValue.text = diceDotsSum.toString()

                resultActivityModifier.setDieImageViewByDotsCount(imageViewDieOne, dieOneDotsCount)
                resultActivityModifier.setDieImageViewByDotsCount(imageViewDieTwo, dieTwoDotsCount)
                resultActivityModifier.setDieImageViewByDotsCount(imageViewDieThree, dieThreeDotsCount)

                imageViewDieOne.visibility = View.VISIBLE
                imageViewDieTwo.visibility = View.VISIBLE
                imageViewDieThree.visibility = View.VISIBLE

                imageViewDieOne.animate().setDuration(1500)
                    .translationYBy(threeDiceAnimationYOffset).rotation(180f)
                imageViewDieTwo.animate().setDuration(1500)
                    .translationYBy(threeDiceAnimationYOffset).rotation(180f)
                imageViewDieThree.animate().setDuration(1500)
                    .translationYBy(threeDiceAnimationYOffset).rotation(180f)
            }
        }

        textViewDotsNumber.visibility = View.VISIBLE
        textViewDiceDotsSumValue.visibility = View.VISIBLE
        buttonGoBack.visibility = View.VISIBLE

        buttonGoBack.setOnClickListener {
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
    }

    override fun onBackPressed() {
        val intentShake = Intent(this, ShakeActivity::class.java)
        val extras = Bundle()

        extras.putSerializable("diceCount", diceCount)
        intentShake.putExtras(extras)
        startActivity(intentShake)
    }
}