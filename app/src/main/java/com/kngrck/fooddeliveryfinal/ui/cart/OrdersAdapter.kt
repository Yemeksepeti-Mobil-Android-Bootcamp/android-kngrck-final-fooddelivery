package com.kngrck.fooddeliveryfinal.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kngrck.fooddeliveryfinal.databinding.ItemCartOrderBinding
import com.kngrck.fooddeliveryfinal.model.entity.order.Order

class OrdersAdapter : RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {
    private var orders = ArrayList<Order>()
    private var listener: ICountChangeListener? = null

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
                mealPriceTextView.text =
                    String.format("%.2f", order.mealPrice * order.count) + " TL"
                mealCountTextView.text = order.count.toString() + " ad."

                decreaseCountButton.setOnClickListener {
                    var count = order.count
                    if (count > 1) {
                        count--
                        orders[position].count = count
                        listener?.countChanged(order.id, orders[position].count,orders)
                        notifyItemChanged(position)
                    } else if (count == 1) {
                        orders.remove(order)
                        listener?.countChanged(order.id, 0, orders)
                        notifyItemRemoved(position)
                    }


                }

                increaseCountButton.setOnClickListener {
                    var count = order.count
                    count++
                    orders[position].count = count
                    listener?.countChanged(order.id, orders[position].count, orders)
                    notifyItemChanged(position)
                }
            }

        }
    }

    fun setOrders(orders: ArrayList<Order>) {
        this.orders = orders
        notifyDataSetChanged()
    }


    fun setListener(listener: ICountChangeListener) {
        this.listener = listener
    }

    fun removeListeners() {
        this.listener = null
    }

    override fun getItemCount(): Int = orders.size
}