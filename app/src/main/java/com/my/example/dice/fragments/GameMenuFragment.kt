package com.my.example.dice.fragments

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.my.example.dice.R
import com.my.example.dice.databinding.FragmentGameMenuBinding

class GameMenuFragment : Fragment(R.layout.fragment_game_menu) {
    private lateinit var binding: FragmentGameMenuBinding

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameMenuBinding.bind(view)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        with(binding) {
            startBtn.setOnClickListener {
                findNavController().navigate(R.id.action_gameMenuFragment_to_diceGameFragment)
            }

            quitBtn.setOnClickListener {
                quitInit()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(), object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    quitInit()
                }
            }
        )
    }

    private fun quitInit() {
        AlertDialog.Builder(requireContext())
            .setTitle("You want out?")
            .setMessage("Sure?")
            .setPositiveButton("Yup") { _, _ ->
                requireActivity().finish()
            }
            .setNegativeButton("Nope", null)
            .create()
            .show()
    }
}