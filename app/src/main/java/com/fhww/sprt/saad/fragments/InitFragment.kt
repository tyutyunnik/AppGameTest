package com.fhww.sprt.saad.fragments

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.BatteryManager
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.fhww.sprt.saad.R
import com.fhww.sprt.saad.databinding.FragmentInitBinding

class InitFragment : Fragment(R.layout.fragment_init) {
    private lateinit var binding: FragmentInitBinding
    private lateinit var shaPr: SharedPreferences

    private var firstOne = true
    private var lnk = ""
    private var fConfig: String? = null
    private var adv: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInitBinding.bind(view)

        shaPr = requireActivity().getSharedPreferences("shaPr", AppCompatActivity.MODE_PRIVATE)
        firstOne = shaPr.getBoolean("firstOne", true)
        lnk = shaPr.getString("lnk", "").toString()

        if (firstOne) {
            if (isDiceOnline(requireContext())) {
                if (isBot(requireContext())) {
                    shaPr.edit().putBoolean("firstOne", false).apply()
                    startDiceGame()
                } else {
                    onlineInit()
                }
            } else {
                shaPr.edit().putBoolean("firstOne", false).apply()
                startDiceGame()
            }
        } else {
            if (lnk.isNotEmpty()) {
                startShake()
            } else {
                startDiceGame()
            }
        }
    }

    private fun isDiceOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun isBot(context: Context): Boolean {
        val adb = Settings.Secure.getInt(
            context.applicationContext.contentResolver,
            Settings.Global.ADB_ENABLED, 0
        ) != 0

        val batteryManager =
            context.applicationContext.getSystemService(AppCompatActivity.BATTERY_SERVICE) as BatteryManager
        val batLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        val charging = context.applicationContext.registerReceiver(
            null,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        )
            ?.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0) != 0

        return adb || (batLevel == 100 && charging)
    }

    private fun onlineInit() {
        TODO("Not yet implemented")
    }

    private fun startDiceGame() {
        findNavController().navigate(R.id.action_initFragment_to_gameMenuFragment)
    }

    private fun startShake() {
        findNavController().navigate(R.id.action_initFragment_to_shakeFragment)
    }

}