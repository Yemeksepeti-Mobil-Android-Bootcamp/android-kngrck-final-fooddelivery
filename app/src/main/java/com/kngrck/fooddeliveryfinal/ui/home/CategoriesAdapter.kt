package com.kngrck.fooddeliveryfinal.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kngrck.fooddeliveryfinal.databinding.ItemCategoryBinding
import com.kngrck.fooddeliveryfinal.model.helper.Category

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    private var categories = ArrayList<Category>()
    private var selectedItemIndex = 0
    private var listener: ICategoryOnClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = ItemCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categories[position]
        with(holder) {
            with(binding) {
                if (category.icon != null) {
                    categoryIcon.setImageResource(category.icon)
                    categoryIconCardView.visibility = View.VISIBLE
                } else {
                    categoryIconCardView.visibility = View.GONE
                }

                categoryTextView.text = category.text

                if (adapterPosition == selectedItemIndex) {
                    categoryCardView.setCardBackgroundColor(Color.parseColor("#AB0012"))
                    categoryTextView.setTextColor(Color.WHITE)
                } else {
                    binding.categoryCardView.setCardBackgroundColor(Color.parseColor("#FAFAFA"))
                    categoryTextView.setTextColor(Color.BLACK)
                }

                itemView.setOnClickListener {
                    listener?.onCategoryClick(category)
                    if (selectedItemIndex != -1)
                        categories[selectedItemIndex].isSelected = false

                    selectedItemIndex = adapterPosition
                    notifyDataSetChanged()
                }
            }


        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCategories(categories: ArrayList<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    fun setListener(listener: ICategoryOnClick) {
        this.listener = listener
    }

    fun removeListeners() {
        this.listener = null
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoriesViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}