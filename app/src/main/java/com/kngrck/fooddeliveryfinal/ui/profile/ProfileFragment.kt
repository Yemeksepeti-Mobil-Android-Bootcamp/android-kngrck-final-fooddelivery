package com.kngrck.fooddeliveryfinal.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kngrck.fooddeliveryfinal.databinding.FragmentProfileBinding
import com.kngrck.fooddeliveryfinal.ui.StartActivity
import com.kngrck.fooddeliveryfinal.utils.FirebaseAuthManager
import com.kngrck.fooddeliveryfinal.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var _binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private var adapter: LastOrdersAdapter = LastOrdersAdapter()

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
            FirebaseAuthManager.signOut()
            val intent = Intent(context, StartActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        viewModel.getLastOrdersOfUser().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
//                    _binding.mainLayout.gone()
//                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
//                    _binding.mainLayout.show()
//                    _binding.progressBar.gone()
                    val orders = it.data?.data!!
                    Log.v("Profile", orders.toString())
                    adapter.setOrders(orders)

                    with(_binding) {
                        lastOrdersRecyclerView.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        lastOrdersRecyclerView.adapter = adapter
                    }
                }
                Resource.Status.ERROR -> {
//                    _binding.mainLayout.show()
//                    _binding.progressBar.gone()
                    Log.v("Profile", "error $it.message")
                }
            }
        })


    }


}