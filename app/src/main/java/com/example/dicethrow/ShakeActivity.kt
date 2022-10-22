package com.example.dicethrow

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class ShakeActivity : AppCompatActivity() {
    private lateinit var diceCount: DiceCount
    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private val accelerometerMinimumExpectedValue: Int = 20

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shake)

        diceCount = intent.getSerializableExtra("diceCount") as DiceCount
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        val intentResult = Intent(this, ResultActivity::class.java)
        val listener = object : SensorEventListener {

            override fun onSensorChanged(sensorEvent: SensorEvent?) {

                if (sensorEvent != null) {
                    Log.d("MY_APP", sensorEvent.values[0].toString())

                    if (sensorEvent.values[0] > accelerometerMinimumExpectedValue) {

                        sensorManager.unregisterListener(this)

                        if (diceCount == DiceCount.ONE) {
                            vibrator.vibrate(200)
                        }
                        else if (diceCount == DiceCount.TWO) {
                            vibrator.vibrate(200)
                            vibrator.vibrate(200)
                        }
                        else if (diceCount == DiceCount.THREE) {
                            vibrator.vibrate(200)
                            vibrator.vibrate(200)
                            vibrator.vibrate(200)
                        }

                        intent.putExtra("diceCount", diceCount)
                        startActivity(intentResult)
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, p1: Int) {
                Log.d("MY_APP", "$sensor $p1")
            }
        }

        sensorManager.registerListener(
            listener,
            accelerometer,
            SensorManager.SENSOR_STATUS_ACCURACY_LOW
        )
    }
}