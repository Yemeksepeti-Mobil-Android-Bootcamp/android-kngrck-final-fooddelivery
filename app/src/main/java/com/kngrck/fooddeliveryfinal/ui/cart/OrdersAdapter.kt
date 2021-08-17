package com.kngrck.fooddeliveryfinal.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kngrck.fooddeliveryfinal.databinding.ItemCartOrderBinding
import com.kngrck.fooddeliveryfinal.model.entity.order.Order

class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {
    private var orders = ArrayList<Order>()

    inner class OrdersViewHolder(val binding: ItemCartOrderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val binding = ItemCartOrderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val order = orders[position]
        with(holder) {
            with(binding) {
                mealNameTextView.text = order.mealName
                restaurantNameTextView.text = order.restaurantName
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