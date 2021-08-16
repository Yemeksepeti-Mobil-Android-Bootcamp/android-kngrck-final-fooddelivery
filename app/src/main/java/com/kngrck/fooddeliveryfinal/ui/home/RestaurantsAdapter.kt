package com.kngrck.fooddeliveryfinal.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kngrck.fooddeliveryfinal.databinding.ItemRestaurantBinding
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant

class RestaurantsAdapter : RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>() {
    private var restaurants = ArrayList<Restaurant>()
    private var listener: IRestaurantOnClick? = null

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
                minimumTextView.text = String.format("%.2f", restaurant.minimumFee) + " TL"
                deliveryTimeTextView.text = restaurant.deliveryTime

            }
            itemView.setOnClickListener {
                listener?.onRestaurantClick(restaurant)

            }
        }
    }

    fun setRestaurants(restaurants: ArrayList<Restaurant>) {
        this.restaurants = restaurants
        notifyDataSetChanged()
    }

    fun setListener(listener: IRestaurantOnClick) {
        this.listener = listener
    }

    fun removeListeners() {
        this.listener = null
    }

    override fun getItemCount(): Int = restaurants.size
}