package com.kngrck.fooddeliveryfinal.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentSettingsBinding
import com.kngrck.fooddeliveryfinal.ui.StartActivity
import com.kngrck.fooddeliveryfinal.utils.FirebaseAuthManager
import com.kngrck.fooddeliveryfinal.utils.gone
import com.kngrck.fooddeliveryfinal.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private lateinit var _binding: FragmentSettingsBinding
    private val viewModel: SettingsViewModel by viewModels()
    private val args: SettingsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        with(_binding) {
            if (args.userType == "admin") {
                addMealCardView.show()
                addRestaurantCardView.show()
            } else {
                addMealCardView.gone()
                addRestaurantCardView.gone()
            }
        }
    }

    private fun initListeners() {
        with(_binding) {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
            changeUserDetailsCardView.setOnClickListener {
                findNavController().navigate(R.id.action_settingsFragment_to_userDetailsFragment)
            }
            logoutCardView.setOnClickListener {
                viewModel.logOut()
                FirebaseAuthManager.signOut()
                val intent = Intent(context, StartActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }
}