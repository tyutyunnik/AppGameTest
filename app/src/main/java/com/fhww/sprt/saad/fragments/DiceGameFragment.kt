package com.fhww.sprt.saad.fragments

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fhww.sprt.saad.R
import com.fhww.sprt.saad.databinding.FragmentDiceGameBinding
import java.util.*
import kotlin.math.sqrt

class DiceGameFragment : Fragment(R.layout.fragment_dice_game) {
    private lateinit var binding: FragmentDiceGameBinding

    private var sensorManager: SensorManager? = null
    private var acceleration = 0f
    private var currentAcceleration = 0f
    private var lastAcceleration = 0f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDiceGameBinding.bind(view)

        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        Objects.requireNonNull(sensorManager!!).registerListener(
            sensorListener, sensorManager!!
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL
        )
        acceleration = 10f
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH
    }

    private val sensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            lastAcceleration = currentAcceleration
            currentAcceleration = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta: Float = currentAcceleration - lastAcceleration
            acceleration = acceleration * 0.9f + delta
            if (acceleration > 12) {
                throwTheDice()
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }
    }

    private fun throwTheDice() {
        val diceBlack: Int = randomDiceNumber()
        val diceRed: Int = randomDiceNumber()

        val blackResult = getResult("black", diceBlack)
        val redResult = getResult("red", diceRed)

        binding.diceBlackIV.setImageResource(blackResult)
        binding.diceRedIV.setImageResource(redResult)

        if (diceBlack > diceRed) {
            setResultText("Dark Side \nWin!")
        } else if (diceBlack < diceRed) {
            setResultText("Light Side \nWin!")
        } else if (diceBlack == diceRed) {
            setResultText("Draw! \nShake Again!")
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(), object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
        )
    }

    private fun setResultText(message: String) {
        binding.titleTV.text = message
    }

    private fun getResult(side: String, diceNumber: Int): Int {
        return resources.getIdentifier(
            "dice_$side" + "_$diceNumber",
            "drawable", "com.my.example.dice"
        )
    }

    private fun randomDiceNumber(): Int {
        return Random().nextInt(6) + 1
    }
}