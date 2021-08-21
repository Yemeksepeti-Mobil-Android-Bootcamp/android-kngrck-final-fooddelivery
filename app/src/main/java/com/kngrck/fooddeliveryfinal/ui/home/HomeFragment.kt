package com.kngrck.fooddeliveryfinal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kngrck.fooddeliveryfinal.databinding.FragmentHomeBinding
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant
import com.kngrck.fooddeliveryfinal.model.helper.Category
import com.kngrck.fooddeliveryfinal.utils.Resource
import com.kngrck.fooddeliveryfinal.utils.gone
import com.kngrck.fooddeliveryfinal.utils.show
import com.kngrck.fooddeliveryfinal.utils.showErrorToast
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
        categoriesAdapter.setCategories(categories)
        categoriesAdapter.setListener(this)

        with(_binding) {
            categoriesRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            categoriesRecyclerView.adapter = categoriesAdapter
        }
        viewModel.getAllRestaurants().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.restaurantsRecyclerView.gone()
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {

                    _binding.restaurantsRecyclerView.show()
                    _binding.progressBar.gone()

                    val restaurants = it.data?.data!!
                    viewModel.restaurantList = restaurants
                    restaurantsAdapter.setRestaurants(restaurants)
                    restaurantsAdapter.setListener(this)

                    _binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            val filterList = viewModel.searchRestaurant(query)
                            restaurantsAdapter.setRestaurants(filterList)
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            val filterList = viewModel.searchRestaurant(newText)
                            restaurantsAdapter.setRestaurants(filterList)
                            return true
                        }

                    })

                    with(_binding) {
                        restaurantsRecyclerView.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        restaurantsRecyclerView.adapter = restaurantsAdapter
                    }
                }
                Resource.Status.ERROR -> {

                    _binding.restaurantsRecyclerView.show()
                    _binding.progressBar.gone()
                    showErrorToast(requireContext())
                }
            }
        })


    }


    override fun onCategoryClick(category: Category) {

        restaurantsAdapter.removeListeners()

        if (category.text != "All") {
            viewModel.getRestaurantsByCategory(category.text).observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        _binding.restaurantsRecyclerView.gone()
                        _binding.progressBar.show()
                    }
                    Resource.Status.SUCCESS -> {

                        _binding.restaurantsRecyclerView.show()
                        _binding.progressBar.gone()
                        val restaurants = it.data?.data!!
                        viewModel.restaurantList = restaurants
                        restaurantsAdapter.setRestaurants(restaurants)
                        restaurantsAdapter.setListener(this)

                        _binding.restaurantsRecyclerView.adapter = restaurantsAdapter

                    }
                    Resource.Status.ERROR -> {

                        _binding.restaurantsRecyclerView.show()
                        _binding.progressBar.gone()
                        showErrorToast(requireContext())
                    }
                }
            })
        } else {
            viewModel.getAllRestaurants().observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        _binding.restaurantsRecyclerView.gone()
                        _binding.progressBar.show()
                    }
                    Resource.Status.SUCCESS -> {

                        _binding.restaurantsRecyclerView.show()
                        _binding.progressBar.gone()
                        val restaurants = it.data?.data!!
                        restaurantsAdapter.setRestaurants(restaurants)
                        restaurantsAdapter.setListener(this)

                        _binding.restaurantsRecyclerView.adapter = restaurantsAdapter

                    }
                    Resource.Status.ERROR -> {

                        _binding.restaurantsRecyclerView.show()
                        _binding.progressBar.gone()
                        showErrorToast(requireContext())
                    }
                }
            })
        }

    }

    override fun onRestaurantClick(restaurant: Restaurant) {
        val action = HomeFragmentDirections.actionHomeFragmentToRestaurantFragment(
            restaurant.id
        )
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        categoriesAdapter.removeListeners()
        restaurantsAdapter.removeListeners()
    }

}