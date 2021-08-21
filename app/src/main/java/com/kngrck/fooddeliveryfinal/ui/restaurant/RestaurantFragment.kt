package com.kngrck.fooddeliveryfinal.ui.restaurant

import android.os.Bundle
import android.util.Log
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
import com.kngrck.fooddeliveryfinal.databinding.FragmentRestaurantBinding
import com.kngrck.fooddeliveryfinal.model.entity.favorite.AddFavoriteRestaurantRequest
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal
import com.kngrck.fooddeliveryfinal.utils.Resource
import com.kngrck.fooddeliveryfinal.utils.gone
import com.kngrck.fooddeliveryfinal.utils.show
import com.kngrck.fooddeliveryfinal.utils.showErrorToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment : Fragment(), IMealOnClick {
    private lateinit var _binding: FragmentRestaurantBinding
    private val args: RestaurantFragmentArgs by navArgs()
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
        initListeners()
    }


    private fun initViews() {
        _binding.mealsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        _binding.mealsRecyclerView.adapter = adapter
        getRestaurantAndSetViews()
    }

    private fun initListeners() {
        _binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        _binding.favoriteButton.setOnClickListener {
            val addFavoriteRestaurantRequest = AddFavoriteRestaurantRequest(args.restaurantId)

            viewModel.addRestaurantToFavorite(addFavoriteRestaurantRequest)
                .observe(viewLifecycleOwner, {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                        }
                        Resource.Status.SUCCESS -> {
                        }
                        Resource.Status.ERROR -> {
                            showErrorToast(requireContext(),"Add favorite restaurant failed.")
                        }
                    }
                })
        }
    }

    private fun getRestaurantAndSetViews(){
        viewModel.getRestaurantById(args.restaurantId).observe(viewLifecycleOwner, {
            when (it.status) {

                Resource.Status.LOADING -> {
                    setLoading(true)

                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)

                    val restaurant = it.data?.data!!
                    adapter.setMeals(restaurant.meals!!)
                    adapter.setListener(this)

                    with(_binding) {
                        restaurantNameTextView.text = restaurant.name

                        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor)
                        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor)

                        val minimumFeeText = String.format("%.2f", restaurant.minimumFee) + " TL"
                        minimumFeeTextView.text =minimumFeeText

                        deliveryInfoTextView.text = restaurant.deliveryTime
                        restaurantDescriptionTextView.text = restaurant.description

                        val options = RequestOptions().placeholder(R.drawable.ic_burger)
                        Glide.with(restaurantImageView.context)
                            .applyDefaultRequestOptions(options)
                            .load(restaurant.imageUrl).into(restaurantImageView)
                    }
                }
                Resource.Status.ERROR -> {
                    setLoading(false)
                    showErrorToast(requireContext())
                }
            }
        })
    }

    override fun onClick(meal: Meal) {
        val action = RestaurantFragmentDirections.actionRestaurantFragmentToMealFragment(
            meal.id,
            args.restaurantId
        )
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.removeListeners()
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