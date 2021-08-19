package com.kngrck.fooddeliveryfinal.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kngrck.fooddeliveryfinal.databinding.FragmentFavoriteBinding
import com.kngrck.fooddeliveryfinal.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment(), IOnDeleteRestaurant {
    private lateinit var _binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()
    private var adapter: FavRestaurantsAdapter = FavRestaurantsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        adapter.setListener(this)
        _binding.favRestaurantsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.getFavoriteRestaurants().observe(viewLifecycleOwner, {
            when (it.status) {

                Resource.Status.LOADING -> {
                    Log.v("Favorite", "Loading")

                }
                Resource.Status.SUCCESS -> {
                    Log.v("Favorite", "Success")
                    val favRestaurants = it.data?.data!!
                    adapter.setFavRestaurants(favRestaurants)
                    _binding.favRestaurantsRecyclerView.adapter = adapter
                }
                Resource.Status.ERROR -> {

                    Log.v("Favorite", "Error")
                }
            }
        })

    }

    override fun onDeleteRestaurant(restaurantId: String) {
        Log.v("Favorite", "restaurant id $restaurantId")
        viewModel.deleteFavoriteRestaurant(restaurantId).observe(viewLifecycleOwner, {
            when (it.status) {

                Resource.Status.LOADING -> {
                    Log.v("Favorite", "Delete Loading")
                }
                Resource.Status.SUCCESS -> {
                    Log.v("Favorite", "Delete Success")
                }
                Resource.Status.ERROR -> {
                    Log.v("Favorite", "Delete Error")
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.removeListeners()
    }

}