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
import androidx.recyclerview.widget.LinearLayoutManager
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentMealListBinding
import com.kngrck.fooddeliveryfinal.utils.Resource
import com.kngrck.fooddeliveryfinal.utils.gone
import com.kngrck.fooddeliveryfinal.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealListFragment : Fragment(), IOnDeleteMeal {

    private lateinit var _binding: FragmentMealListBinding
    private val viewModel: MealListViewModel by viewModels()
    private var mealListAdapter: MealListAdapter = MealListAdapter()
    private val args: MealListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        viewModel.getRestaurantMeals(args.restaurantId).observe(viewLifecycleOwner, { resource ->
            when (resource.status) {
                Resource.Status.LOADING -> {
                    _binding.mealsRecyclerView.gone()
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {

                    _binding.mealsRecyclerView.show()
                    _binding.progressBar.gone()

                    val restaurant = resource.data?.data!!
                    restaurant.meals?.let {
                        mealListAdapter.setMeals(it)
                        mealListAdapter.setListener(this)


                        with(_binding) {

                            mealsRecyclerView.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            mealsRecyclerView.adapter = mealListAdapter
                        }
                    }

                }
                Resource.Status.ERROR -> {

                    _binding.mealsRecyclerView.show()
                    _binding.progressBar.gone()
                }
            }
        })
    }

    private fun initListeners() {
        with(_binding) {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
            addMealButton.setOnClickListener {
                val action = MealListFragmentDirections.actionMealListFragmentToAddMealFragment(args.restaurantId)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDeleteMeal(mealId: String) {
        viewModel.deleteMeal(args.restaurantId, mealId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.mealsRecyclerView.gone()
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {

                    _binding.mealsRecyclerView.show()
                    _binding.progressBar.gone()

                }
                Resource.Status.ERROR -> {

                    _binding.mealsRecyclerView.show()
                    _binding.progressBar.gone()
                    Toast.makeText(
                        requireContext(),
                        "Restaurant could not deleted",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }


}