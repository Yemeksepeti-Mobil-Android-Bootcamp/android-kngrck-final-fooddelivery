package com.kngrck.fooddeliveryfinal.ui.editmeal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentAddMealBinding
import com.kngrck.fooddeliveryfinal.model.entity.meal.AddMealRequest
import com.kngrck.fooddeliveryfinal.utils.Resource
import com.kngrck.fooddeliveryfinal.utils.gone
import com.kngrck.fooddeliveryfinal.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMealFragment : Fragment() {
    private lateinit var _binding: FragmentAddMealBinding
    private val viewModel: AddMealViewModel by viewModels()
    private val args: AddMealFragmentArgs by navArgs()
    private var ingredientListAdapter = IngredientListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMealBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initListeners()
    }

    private fun initViews() {
        val ingredients = arrayListOf<String>()
        ingredientListAdapter.setIngredients(ingredients)
        _binding.ingredientsRecyclerView.layoutManager = GridLayoutManager(context, 2)
        _binding.ingredientsRecyclerView.adapter = ingredientListAdapter
    }

    private fun initListeners() {
        with(_binding) {
            mealIngredients.setEndIconOnClickListener {
                val ingredient = mealIngredients.editText?.text.toString()
                if (ingredient.isNotEmpty()) ingredientListAdapter.addIngredient(ingredient)
            }

            applyButton.setOnClickListener {
                checkInputsAndAddMeal()
            }
        }

    }

    private fun checkInputsAndAddMeal() {
        with(_binding) {
            val name = mealName.editText?.text.toString()
            val details = mealDetails.editText?.text.toString()
            val price = mealPrice.editText?.text.toString().toDoubleOrNull()
            val imageUrl = mealImage.editText?.text.toString()
            val ingredients = ingredientListAdapter.getIngredients()

            if (name.isNotEmpty() &&
                details.isNotEmpty() &&
                price != null &&
                imageUrl.isNotEmpty()
            ) {

                val addMealRequest = AddMealRequest(
                    name, details, imageUrl, price, ingredients
                )

                viewModel.addMeal(args.restaurantId, addMealRequest).observe(viewLifecycleOwner, {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                            setLoading(true)
                        }
                        Resource.Status.SUCCESS -> {
                            setLoading(false)
                            findNavController().navigate(R.id.action_addMealFragment_to_profileFragment)

                        }
                        Resource.Status.ERROR -> {
                            setLoading(false)
                            Toast.makeText(
                                requireContext(),
                                "Meal could not added",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
            } else {
                if (name.isEmpty()) mealName.error = "Do not leave blank."
                if (details.isEmpty()) mealDetails.error = "Do not leave blank."
                if (imageUrl.isEmpty()) mealImage.error = "Do not leave blank."
                if (price == null) mealPrice.error = "Do not leave blank."
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