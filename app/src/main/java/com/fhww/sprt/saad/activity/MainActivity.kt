package com.fhww.sprt.saad.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fhww.sprt.saad.databinding.ActivityMainBinding
import com.onesignal.OneSignal

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        OneSignal.initWithContext(this@MainActivity)
        OneSignal.setAppId("717760a4-1976-464d-9990-7e266633f202")
    }
}