package com.kngrck.fooddeliveryfinal.ui.editmeal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.kngrck.fooddeliveryfinal.databinding.FragmentMealListBinding
import com.kngrck.fooddeliveryfinal.utils.Resource
import com.kngrck.fooddeliveryfinal.utils.gone
import com.kngrck.fooddeliveryfinal.utils.show
import com.kngrck.fooddeliveryfinal.utils.showErrorToast
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
                    setLoading(true)
                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)

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
            addMealButton.setOnClickListener {
                val action =
                    MealListFragmentDirections.actionMealListFragmentToAddMealFragment(args.restaurantId)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDeleteMeal(mealId: String) {
        viewModel.deleteMeal(args.restaurantId, mealId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                }
                Resource.Status.SUCCESS -> {
                }
                Resource.Status.ERROR -> {
                    showErrorToast(
                        requireContext(),
                        "Failed to delete meal. Please try again later."
                    )
                }
            }
        })
    }

    private fun setLoading(isLoading: Boolean) {
        with(_binding) {
            if (isLoading) {
                mealsRecyclerView.gone()
                progressBar.show()
            } else {
                mealsRecyclerView.show()
                progressBar.gone()
            }
        }
    }

}