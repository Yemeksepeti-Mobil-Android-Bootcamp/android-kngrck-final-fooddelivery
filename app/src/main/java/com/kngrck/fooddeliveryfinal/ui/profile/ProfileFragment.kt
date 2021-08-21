package com.kngrck.fooddeliveryfinal.ui.profile

import android.os.Bundle
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
import com.kngrck.fooddeliveryfinal.utils.showErrorToast
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
        initListeners()
    }

    private fun initViews() {

        viewModel.getProfile().observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> {
                    setLoading(true)
                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)

                    val profile = resource.data!!.data
                    userType = profile.type
                    val ordersSortByDate = profile.orders
                    ordersSortByDate.sortByDescending { it.createdAt }
                    adapter.setOrders(ordersSortByDate)

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
                    setLoading(false)
                    showErrorToast(requireContext())
                }
            }
        })
    }

    private fun initListeners() {
        _binding.settingsButton.setOnClickListener {
            userType?.let {
                val action = ProfileFragmentDirections.actionProfileFragmentToSettingsFragment(it)
                findNavController().navigate(action)
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