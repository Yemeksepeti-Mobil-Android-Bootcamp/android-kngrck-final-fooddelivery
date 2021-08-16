package com.kngrck.fooddeliveryfinal.ui.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentMealBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealFragment : Fragment() {
    private lateinit var _binding: FragmentMealBinding
    private val viewModel: MealViewModel by viewModels()
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
        val meal = viewModel.getDummyMeal()
        adapter.setIngredients(meal.ingredients)

        with(_binding) {
            mealCardView.setBackgroundResource(R.drawable.shape_meal_card)
            mealNameTextView.text = meal.name
            mealDetailsTextView.text = meal.details
            ingredientsRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            ingredientsRecyclerView.adapter = adapter
        }

    }
}