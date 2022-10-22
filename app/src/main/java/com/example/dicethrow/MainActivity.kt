package com.example.dicethrow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var buttonOneDice: Button
    private lateinit var buttonTwoDice: Button
    private lateinit var buttonThreeDice: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, ShakeActivity::class.java)

        buttonOneDice = findViewById(R.id.main_button_one_dice)
        buttonTwoDice = findViewById(R.id.main_button_two_dice)
        buttonThreeDice = findViewById(R.id.main_button_three_dice)

        buttonOneDice.setOnClickListener {
            intent.putExtra("diceCount", DiceCount.ONE)
            startActivity(intent)
        }

        buttonTwoDice.setOnClickListener {
            intent.putExtra("diceCount", DiceCount.TWO)
            startActivity(intent)
        }

        buttonThreeDice.setOnClickListener {
            intent.putExtra("diceCount", DiceCount.THREE)
            startActivity(intent)
        }
    }
}