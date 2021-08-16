package com.kngrck.fooddeliveryfinal.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentHomeBinding
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant
import com.kngrck.fooddeliveryfinal.model.helper.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), IRestaurantOnClick, ICategoryOnClick {
    private lateinit var _binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var categoriesAdapter: CategoriesAdapter = CategoriesAdapter()
    private var restaurantsAdapter: RestaurantsAdapter = RestaurantsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val categories = viewModel.getCategories()
        val restaurants = viewModel.getAllRestaurants()

        categoriesAdapter.setCategories(categories)
        restaurantsAdapter.setRestaurants(restaurants)

        categoriesAdapter.setListener(this)
        restaurantsAdapter.setListener(this)

        with(_binding) {
            categoriesRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            restaurantsRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            categoriesRecyclerView.adapter = categoriesAdapter
            restaurantsRecyclerView.adapter = restaurantsAdapter
        }

    }

    override fun onCategoryClick(category: Category) {
        Log.v("Home", "category clicked")
    }

    override fun onRestaurantClick(restaurant: Restaurant) {
        findNavController().navigate(R.id.action_homeFragment_to_restaurantFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        categoriesAdapter.removeListeners()
        restaurantsAdapter.removeListeners()
    }

}