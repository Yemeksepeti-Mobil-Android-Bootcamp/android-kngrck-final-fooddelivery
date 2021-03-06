package com.kngrck.fooddeliveryfinal.ui.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentMealBinding
import com.kngrck.fooddeliveryfinal.model.entity.cart.AddCartRequest
import com.kngrck.fooddeliveryfinal.utils.Resource
import com.kngrck.fooddeliveryfinal.utils.gone
import com.kngrck.fooddeliveryfinal.utils.show
import com.kngrck.fooddeliveryfinal.utils.showErrorToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealFragment : Fragment() {
    private lateinit var _binding: FragmentMealBinding
    private val viewModel: MealViewModel by viewModels()
    private val args: MealFragmentArgs by navArgs()
    private var adapter: IngredientsAdapter = IngredientsAdapter()
    private var count = 1

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
        initListeners()
    }

    private fun initViews() {
        _binding.orderCount.text = count.toString()
        getMealAndSetViews()
    }

    private fun initListeners() {
        _binding.decreaseCountButton.setOnClickListener {
            decreaseCount()
        }

        _binding.increaseCountButton.setOnClickListener {
            increaseCount()
        }

        _binding.addToCartButton.setOnClickListener {
            addToCartAndNavigateToRestaurant()
        }

        _binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun addToCartAndNavigateToRestaurant() {
        val addCartRequest = AddCartRequest(
            args.restaurantId,
            args.mealId,
            count,
            adapter.getCheckedIngredients()
        )
        viewModel.addToCart(addCartRequest).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    setLoading(true)
                }
                Resource.Status.SUCCESS -> {
                    findNavController().popBackStack()
                }
                Resource.Status.ERROR -> {
                    setLoading(false)
                    showErrorToast(requireContext(), "Add to cart failed.")
                }
            }
        })
    }

    private fun getMealAndSetViews() {
        viewModel.getMealById(args.mealId, args.restaurantId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    setLoading(true)
                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)

                    val meal = it.data?.data!!
                    adapter.setIngredients(meal.ingredients)

                    with(_binding) {
                        mealCardView.setBackgroundResource(R.drawable.shape_meal_card)
                        mealNameTextView.text = meal.name
                        mealDetailsTextView.text = meal.details

                        val mealPriceText = String.format("%.2f", meal.price) + " TL"
                        mealPriceTextView.text = mealPriceText

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
                    setLoading(true)
                    showErrorToast(requireContext())
                }
            }
        })
    }

    private fun increaseCount() {
        count++
        _binding.orderCount.text = count.toString()
    }

    private fun decreaseCount() {
        if (count > 1) count--
        _binding.orderCount.text = count.toString()
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