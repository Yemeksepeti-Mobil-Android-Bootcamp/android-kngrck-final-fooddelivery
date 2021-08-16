package com.kngrck.fooddeliveryfinal.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kngrck.fooddeliveryfinal.databinding.ItemRestaurantBinding
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant

class RestaurantsAdapter : RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>() {
    private var restaurants = ArrayList<Restaurant>()

    inner class RestaurantsViewHolder(val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantsViewHolder {
        val binding = ItemRestaurantBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantsViewHolder, position: Int) {
        val restaurant = restaurants[position]
        with(holder) {
            with(binding) {
                restaurantNameTextView.text = restaurant.name
                ratingTextView.text = restaurant.rating.toString()
                minimumTextView.text = restaurant.minimumFee.toString()
                deliveryTimeTextView.text = restaurant.deliveryTime
            }
        }
    }

    fun setRestaurants(restaurants: ArrayList<Restaurant>) {
        this.restaurants = restaurants
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = restaurants.size
}