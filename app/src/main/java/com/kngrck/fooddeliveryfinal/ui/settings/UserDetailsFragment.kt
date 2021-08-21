package com.kngrck.fooddeliveryfinal.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentUserDetailsBinding
import com.kngrck.fooddeliveryfinal.model.entity.profile.UpdateProfileRequest
import com.kngrck.fooddeliveryfinal.utils.Resource
import com.kngrck.fooddeliveryfinal.utils.gone
import com.kngrck.fooddeliveryfinal.utils.show
import com.kngrck.fooddeliveryfinal.utils.showErrorToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
    private lateinit var _binding: FragmentUserDetailsBinding
    private val viewModel: UserDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        viewModel.getProfile().observe(viewLifecycleOwner, {
            when (it.status) {

                Resource.Status.LOADING -> {
                    setLoading(true)

                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)

                    val profile = it.data!!.data

                    with(_binding) {
                        userNameTextInput.editText?.setText(profile.name)
                        userPhoneTextInput.editText?.setText(profile.phone)
                        userProfileImageTextInput.editText?.setText(profile.profileImage)
                    }
                }

                Resource.Status.ERROR -> {
                    setLoading(false)
                    showErrorToast(requireContext())
                }
            }
        })
    }

    private fun initListeners() {
        with(_binding) {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }

            applyButton.setOnClickListener {
                val name = userNameTextInput.editText?.text.toString()
                val phone = userPhoneTextInput.editText?.text.toString()
                val profileImage = userProfileImageTextInput.editText?.text.toString()
                if (name.isNotEmpty() && phone.isNotEmpty() && profileImage.isNotEmpty()) {
                    val updateProfileRequest = UpdateProfileRequest(name, phone, profileImage)
                    viewModel.updateProfile(updateProfileRequest).observe(viewLifecycleOwner, {
                        when (it.status) {

                            Resource.Status.LOADING -> {
                                setLoading(true)
                            }
                            Resource.Status.SUCCESS -> {
                                findNavController().navigate(R.id.action_userDetailsFragment_to_profileFragment)
                            }

                            Resource.Status.ERROR -> {
                                setLoading(true)
                                showErrorToast(requireContext(), "Failed to apply changes.")
                            }
                        }
                    })

                } else {

                    if (name.isEmpty()) _binding.userNameTextInput.error = "Do not leave blank."
                    if (phone.isEmpty()) _binding.userPhoneTextInput.error = "Do not leave blank."
                    if (profileImage.isEmpty()) _binding.userPhoneTextInput.error =
                        "Do not leave blank."
                }
            }

        }
    }

    private fun setLoading(isLoading: Boolean) {
        with(_binding) {
            if (isLoading) {
                mainLayout.gone()
                progressBar.show()
            } else {
                mainLayout.show()
                progressBar.gone()
            }
        }

    }
}