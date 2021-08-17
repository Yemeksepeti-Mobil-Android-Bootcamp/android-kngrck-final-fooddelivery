package com.kngrck.fooddeliveryfinal.ui.meal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentMealBinding
import com.kngrck.fooddeliveryfinal.ui.restaurant.RestaurantFragmentArgs
import com.kngrck.fooddeliveryfinal.utils.Resource
import com.kngrck.fooddeliveryfinal.utils.gone
import com.kngrck.fooddeliveryfinal.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealFragment : Fragment() {
    private lateinit var _binding: FragmentMealBinding
    private val viewModel: MealViewModel by viewModels()
    private val args: MealFragmentArgs by navArgs()
    private var adapter: IngredientsAdapter = IngredientsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealBinding.inflate(inflater, container, false)
        return _binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        viewModel.getMealById(args.mealId,args.restaurantId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.mainLayout.gone()
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.mainLayout.show()
                    _binding.progressBar.gone()
                    val meal = it.data?.data!!
                    adapter.setIngredients(meal.ingredients)

                    with(_binding) {
                        mealCardView.setBackgroundResource(R.drawable.shape_meal_card)
                        mealNameTextView.text = meal.name
                        mealDetailsTextView.text = meal.details
                        ingredientsRecyclerView.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                        ingredientsRecyclerView.adapter = adapter

                        val options = RequestOptions().placeholder(R.drawable.ic_burger)
                        Glide.with(mealImageView.context)
                            .applyDefaultRequestOptions(options)
                            .load(meal.imageUrl).into(mealImageView)
                    }
                }
                Resource.Status.ERROR -> {
                    _binding.mainLayout.show()
                    _binding.progressBar.gone()
                    Log.v("Home", "Error")
                }
            }
        })


    }
}