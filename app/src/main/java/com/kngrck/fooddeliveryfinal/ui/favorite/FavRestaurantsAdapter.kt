package com.kngrck.fooddeliveryfinal.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kngrck.fooddeliveryfinal.databinding.ItemFavRestaurantBinding
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant

class FavRestaurantsAdapter :
    RecyclerView.Adapter<FavRestaurantsAdapter.FavRestaurantsViewHolder>() {
    private var favRestaurants = ArrayList<Restaurant>()

    inner class FavRestaurantsViewHolder(val binding: ItemFavRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavRestaurantsViewHolder {
        val binding = ItemFavRestaurantBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FavRestaurantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavRestaurantsViewHolder, position: Int) {
        val restaurant = favRestaurants[position]
        with(holder) {
            with(binding) {
                restaurantNameTextView.text = restaurant.name
                ratingTextView.text = restaurant.rating.toString()
                minimumTextView.text = String.format("%.2f", restaurant.minimumFee) + " TL"
                deliveryTimeTextView.text = restaurant.deliveryTime
            }

        }
    }

    fun setFavRestaurants(favRestaurants: ArrayList<Restaurant>) {
        this.favRestaurants = favRestaurants
        notifyDataSetChanged()
    }

//    fun setListener(listener: IMealOnClick) {
//        this.listener = listener
//    }
//
//    fun removeListeners() {
//        this.listener = null
//    }

    override fun getItemCount(): Int = favRestaurants.size
}