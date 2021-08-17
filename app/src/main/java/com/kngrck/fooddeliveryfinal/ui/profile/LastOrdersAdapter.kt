package com.kngrck.fooddeliveryfinal.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kngrck.fooddeliveryfinal.databinding.ItemLastOrderBinding
import com.kngrck.fooddeliveryfinal.model.entity.order.Order

class LastOrdersAdapter : RecyclerView.Adapter<LastOrdersAdapter.LastOrdersViewHolder>() {
    private var orders = ArrayList<Order>()

    inner class LastOrdersViewHolder(val binding: ItemLastOrderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastOrdersViewHolder {
        val binding = ItemLastOrderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LastOrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LastOrdersViewHolder, position: Int) {
        val order = orders[position]
        with(holder) {
            with(binding) {
                mealNameTextView.text = order.mealName
                restaurantNameTextView.text = order.restaurantName
                orderDateTextView.text = order.createdAt
                mealPriceTextView.text = String.format("%.2f", order.mealPrice) + " TL"
                mealCountTextView.text = order.mealCount.toString() + " ad."
                mealImageView.setImageResource(order.mealImage)

            }

        }
    }

    fun setOrders(orders: ArrayList<Order>) {
        this.orders = orders
        notifyDataSetChanged()
    }

//    fun setListener(listener: IMealOnClick) {
//        this.listener = listener
//    }
//
//    fun removeListeners() {
//        this.listener = null
//    }

    override fun getItemCount(): Int = orders.size
}