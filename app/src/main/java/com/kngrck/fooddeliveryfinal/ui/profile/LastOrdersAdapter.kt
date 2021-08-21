package com.kngrck.fooddeliveryfinal.ui.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.ItemLastOrderBinding
import com.kngrck.fooddeliveryfinal.model.entity.order.Order
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class LastOrdersAdapter : RecyclerView.Adapter<LastOrdersAdapter.LastOrdersViewHolder>() {
    private var orders = ArrayList<Order>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastOrdersViewHolder {
        val binding = ItemLastOrderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LastOrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LastOrdersViewHolder, position: Int) {
        val order = orders[position]
        with(holder) {
            with(binding) {

                if (order.createdAt != null) {
                    val date = Date(order.createdAt)
                    val format = SimpleDateFormat("yyyy/MM/dd HH:mm")
                    orderDateTextView.text = format.format(date)
                } else {
                    orderDateTextView.text = ""
                }

                mealNameTextView.text = order.mealName
                restaurantNameTextView.text = order.restaurantName

                val mealPriceText = String.format("%.2f", order.mealPrice * order.count) + " TL"
                mealPriceTextView.text =mealPriceText

                val mealCountText = order.count.toString() + " qty."
                mealCountTextView.text = mealCountText

                val options = RequestOptions().placeholder(R.drawable.ic_burger)
                Glide.with(mealImageView.context)
                    .applyDefaultRequestOptions(options)
                    .load(order.mealImage).into(mealImageView)
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setOrders(orders: ArrayList<Order>) {
        this.orders = orders
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = orders.size

    inner class LastOrdersViewHolder(val binding: ItemLastOrderBinding) :
        RecyclerView.ViewHolder(binding.root)
}