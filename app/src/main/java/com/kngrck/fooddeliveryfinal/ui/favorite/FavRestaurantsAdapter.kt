package com.kngrck.fooddeliveryfinal.ui.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kngrck.fooddeliveryfinal.databinding.ItemFavRestaurantBinding
import com.kngrck.fooddeliveryfinal.model.entity.restaurant.Restaurant

class FavRestaurantsAdapter :
    RecyclerView.Adapter<FavRestaurantsAdapter.FavRestaurantsViewHolder>() {
    private var favRestaurants = ArrayList<Restaurant>()
    private var listener: IOnDeleteRestaurant? = null

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

                val minimumFeeText = String.format("%.2f", restaurant.minimumFee) + " TL"
                minimumTextView.text = minimumFeeText

                deliveryTimeTextView.text = restaurant.deliveryTime

                restaurantItemCardView.setOnClickListener {
                    val action = FavoriteFragmentDirections.actionFavoriteFragmentToRestaurantFragment(
                        restaurant.id
                    )
                    it.findNavController().navigate(action)
                }

                deleteFavoriteButton.setOnClickListener {
                    favRestaurants.remove(restaurant)
                    listener?.onDeleteRestaurant(restaurant.id)
                    notifyItemRemoved(position)
                }
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFavRestaurants(favRestaurants: ArrayList<Restaurant>) {
        this.favRestaurants = favRestaurants
        notifyDataSetChanged()
    }

    fun setListener(listener: IOnDeleteRestaurant) {
        this.listener = listener
    }

    fun removeListeners() {
        this.listener = null
    }

    override fun getItemCount(): Int = favRestaurants.size
}