package com.my.example.dice.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.my.example.dice.R
import com.my.example.dice.databinding.FragmentShakeBinding

class ShakeFragment : Fragment(R.layout.fragment_shake) {
    private lateinit var binding: FragmentShakeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShakeBinding.bind(view)
    }
}