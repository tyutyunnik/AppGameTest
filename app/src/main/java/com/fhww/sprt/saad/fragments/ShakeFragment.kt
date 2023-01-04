package com.fhww.sprt.saad.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.fhww.sprt.saad.R
import com.fhww.sprt.saad.databinding.FragmentShakeBinding

class ShakeFragment : Fragment(R.layout.fragment_shake) {
    private lateinit var binding: FragmentShakeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShakeBinding.bind(view)
    }
}