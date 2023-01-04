package com.fhww.sprt.saad.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
}