package com.my.example.dice.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.my.example.dice.R
import com.my.example.dice.databinding.FragmentGameMenuBinding

class GameMenuFragment : Fragment(R.layout.fragment_game_menu) {
    private lateinit var binding: FragmentGameMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameMenuBinding.bind(view)
    }
}