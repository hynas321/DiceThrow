package com.example.dicethrow

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var buttonOneDice: Button
    private lateinit var buttonTwoDice: Button
    private lateinit var buttonThreeDice: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentShake = Intent(this, ShakeActivity::class.java)
        val extras = Bundle()

        buttonOneDice = findViewById(R.id.main_button_one_dice)
        buttonTwoDice = findViewById(R.id.main_button_two_dice)
        buttonThreeDice = findViewById(R.id.main_button_three_dice)

        buttonOneDice.setOnClickListener {
            extras.putSerializable("diceCount", DiceCount.ONE)
            intentShake.putExtras(extras)
            startActivity(intentShake)
        }

        buttonTwoDice.setOnClickListener {
            extras.putSerializable("diceCount", DiceCount.TWO)
            intentShake.putExtras(extras)
            startActivity(intentShake)
        }

        buttonThreeDice.setOnClickListener {
            extras.putSerializable("diceCount", DiceCount.THREE)
            intentShake.putExtras(extras)
            startActivity(intentShake)
        }
    }
}