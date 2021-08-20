package com.kngrck.fooddeliveryfinal.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentProfileBinding
import com.kngrck.fooddeliveryfinal.utils.Resource
import com.kngrck.fooddeliveryfinal.utils.gone
import com.kngrck.fooddeliveryfinal.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var _binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private var adapter: LastOrdersAdapter = LastOrdersAdapter()
    private var userType: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        _binding.settingsButton.setOnClickListener {
            userType?.let {
                val action = ProfileFragmentDirections.actionProfileFragmentToSettingsFragment(
                    it
                )
                findNavController().navigate(action)
            }

        }

        viewModel.getProfile().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.mainLayout.gone()
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.mainLayout.show()
                    _binding.progressBar.gone()
                    val profile = it.data!!.data
                    userType = profile.type
                    Log.v("Profile", "profile $profile")
                    adapter.setOrders(profile.orders)

                    with(_binding) {
                        lastOrdersRecyclerView.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        val options = RequestOptions().placeholder(R.drawable.ic_user)
                        Glide.with(profileImage.context)
                            .applyDefaultRequestOptions(options)
                            .load(profile.profileImage).into(profileImage)
                        userName.text = profile.name
                        userMail.text = profile.email
                        userPhone.text = profile.phone
                        lastOrdersRecyclerView.adapter = adapter
                    }
                }
                Resource.Status.ERROR -> {
                    _binding.mainLayout.show()
                    _binding.progressBar.gone()
                    Log.v("Profile", "error $it.message")
                }
            }
        })


    }


}