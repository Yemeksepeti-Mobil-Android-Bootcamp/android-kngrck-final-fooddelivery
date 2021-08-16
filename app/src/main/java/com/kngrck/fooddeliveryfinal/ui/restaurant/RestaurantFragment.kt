package com.kngrck.fooddeliveryfinal.ui.restaurant

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
import com.kngrck.fooddeliveryfinal.databinding.FragmentRestaurantBinding
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment : Fragment() ,IMealOnClick{
    private lateinit var _binding: FragmentRestaurantBinding
    private val viewModel: RestaurantViewModel by viewModels()
    private var adapter: MealsAdapter = MealsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val restaurant = viewModel.getDummyRestaurant()
        adapter.setMeals(restaurant.meals!!)
        adapter.setListener(this)
        with(_binding) {
            restaurantNameTextView.text = restaurant.name
            collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor)
            collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor)
            mealsRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            mealsRecyclerView.adapter = adapter
        }
    }

    override fun onClick(meal: Meal) {
        findNavController().navigate(R.id.action_restaurantFragment_to_mealFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.removeListeners()
    }

}