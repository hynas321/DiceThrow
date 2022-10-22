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
        val receivedExtras = intent.extras

        if (receivedExtras != null) {
            diceCount = receivedExtras.getSerializable("diceCount") as DiceCount
        }

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        val intentResult = Intent(this, ResultActivity::class.java)
        val extras = Bundle()

        val listener = object : SensorEventListener {

            override fun onSensorChanged(sensorEvent: SensorEvent?) {

                if (sensorEvent != null) {
                    Log.d("MY_APP", sensorEvent.values[0].toString())

                    if (sensorEvent.values[0] > accelerometerMinimumExpectedValue) {

                        sensorManager.unregisterListener(this)
                        vibrator.vibrate(500)

                        extras.putSerializable("diceCount", diceCount)
                        intentResult.putExtras(extras)
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