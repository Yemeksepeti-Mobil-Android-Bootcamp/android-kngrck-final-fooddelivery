package com.kngrck.fooddeliveryfinal.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentThirdOnBoardingBinding


class ThirdOnBoardingFragment : Fragment() {
    private lateinit var _binding: FragmentThirdOnBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdOnBoardingBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_authFragment)
        }
    }

}