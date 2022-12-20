package com.my.example.dice.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.my.example.dice.R
import com.my.example.dice.databinding.FragmentInitBinding

class InitFragment : Fragment(R.layout.fragment_init) {
    private lateinit var binding: FragmentInitBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInitBinding.bind(view)

    }
}