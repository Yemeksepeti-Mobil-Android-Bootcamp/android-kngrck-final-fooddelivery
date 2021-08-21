package com.kngrck.fooddeliveryfinal.ui.editrestaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentRestaurantListBinding
import com.kngrck.fooddeliveryfinal.ui.favorite.IOnDeleteRestaurant
import com.kngrck.fooddeliveryfinal.utils.Resource
import com.kngrck.fooddeliveryfinal.utils.gone
import com.kngrck.fooddeliveryfinal.utils.show
import com.kngrck.fooddeliveryfinal.utils.showErrorToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantListFragment : Fragment(), IOnDeleteRestaurant {
    private lateinit var _binding: FragmentRestaurantListBinding
    private val viewModel: RestaurantListViewModel by viewModels()
    private var restaurantListAdapter: RestaurantListAdapter = RestaurantListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        viewModel.getAllRestaurants().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    setLoading(true)
                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)

                    val restaurants = it.data?.data!!
                    restaurantListAdapter.setRestaurants(restaurants)
                    restaurantListAdapter.setListener(this)

                    with(_binding) {
                        restaurantsRecyclerView.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        restaurantsRecyclerView.adapter = restaurantListAdapter
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
            addRestaurantButton.setOnClickListener {
                findNavController().navigate(R.id.action_restaurantListFragment_to_addRestaurantFragment)
            }
        }
    }

    override fun onDeleteRestaurant(restaurantId: String) {
        viewModel.deleteRestaurant(restaurantId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    setLoading(true)
                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)
                }
                Resource.Status.ERROR -> {
                    setLoading(false)

                    showErrorToast(
                        requireContext(),
                        "Delete restaurant failed. Please try again later"
                    )
                }
            }
        })
    }

    private fun setLoading(isLoading: Boolean) {
        with(_binding) {
            if (isLoading) {
                restaurantsRecyclerView.gone()
                progressBar.show()
            } else {
                restaurantsRecyclerView.show()
                progressBar.gone()
            }
        }
    }

}