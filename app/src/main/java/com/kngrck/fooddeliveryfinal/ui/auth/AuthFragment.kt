package com.kngrck.fooddeliveryfinal.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {
    private lateinit var _binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        val loginFragment = LoginFragment()
        val signUpFragment = SignUpFragment()
        _binding.loginButton.setOnClickListener {

            loginFragment.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.ThemeOverlay_BottomSheetDialog
            )
            loginFragment.show(requireActivity().supportFragmentManager, "BottomSheetDialog")

        }

        _binding.signUpButton.setOnClickListener {

            signUpFragment.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.ThemeOverlay_BottomSheetDialog
            )
            signUpFragment.show(requireActivity().supportFragmentManager, "BottomSheetDialog")

        }
    }

}