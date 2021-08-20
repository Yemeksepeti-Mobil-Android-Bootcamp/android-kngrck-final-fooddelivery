package com.kngrck.fooddeliveryfinal.ui.editrestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.ItemEditRestaurantBinding
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant
import com.kngrck.fooddeliveryfinal.ui.favorite.IOnDeleteRestaurant

class RestaurantListAdapter :
    RecyclerView.Adapter<RestaurantListAdapter.RestaurantListViewHolder>() {
    private var restaurantList = ArrayList<Restaurant>()
    private var listener: IOnDeleteRestaurant? = null

    inner class RestaurantListViewHolder(val binding: ItemEditRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantListViewHolder {
        val binding = ItemEditRestaurantBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantListViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        with(holder) {
            with(binding) {
                restaurantNameTextView.text = restaurant.name

                val options = RequestOptions().placeholder(R.drawable.ic_burger)
                Glide.with(restaurantImageView.context)
                    .applyDefaultRequestOptions(options)
                    .load(restaurant.imageUrl).into(restaurantImageView)

                deleteRestaurantButton.setOnClickListener {
                    restaurantList.remove(restaurant)
                    listener?.onDeleteRestaurant(restaurant.id)
                    notifyDataSetChanged()
                }
            }

        }
    }

    fun setRestaurants(restaurantList: ArrayList<Restaurant>) {
        this.restaurantList = restaurantList
        notifyDataSetChanged()
    }

    fun setListener(listener: IOnDeleteRestaurant) {
        this.listener = listener
    }

    fun removeListeners() {
        this.listener = null
    }

    override fun getItemCount(): Int = restaurantList.size
}