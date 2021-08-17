package com.kngrck.fooddeliveryfinal.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kngrck.fooddeliveryfinal.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
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
        val favRestaurants = viewModel.getFavRestaurants()
        adapter.setFavRestaurants(favRestaurants)

        with(_binding) {
            favRestaurantsRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            favRestaurantsRecyclerView.adapter = adapter
        }

    }

}