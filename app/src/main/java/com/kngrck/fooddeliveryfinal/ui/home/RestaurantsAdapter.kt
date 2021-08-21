package com.kngrck.fooddeliveryfinal.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.ItemRestaurantBinding
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant

class RestaurantsAdapter : RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder>() {
    private var restaurants = ArrayList<Restaurant>()
    private var listener: IRestaurantOnClick? = null

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

                val minimumFeeText = String.format("%.2f", restaurant.minimumFee) + " TL"
                minimumTextView.text = minimumFeeText

                deliveryTimeTextView.text = restaurant.deliveryTime

                val options = RequestOptions().placeholder(R.drawable.ic_burger)
                Glide.with(restaurantImageView.context)
                    .applyDefaultRequestOptions(options)
                    .load(restaurant.imageUrl).into(restaurantImageView)

            }
            itemView.setOnClickListener {
                listener?.onRestaurantClick(restaurant)

            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
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

    inner class RestaurantsViewHolder(val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root)
}