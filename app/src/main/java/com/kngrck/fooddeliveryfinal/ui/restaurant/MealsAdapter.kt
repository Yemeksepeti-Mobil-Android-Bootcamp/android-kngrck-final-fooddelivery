package com.kngrck.fooddeliveryfinal.ui.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.ItemMealBinding
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal

class MealsAdapter : RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {
    private var meals = ArrayList<Meal>()
    private var listener: IMealOnClick? = null

    inner class MealsViewHolder(val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val binding = ItemMealBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        val meal = meals[position]
        with(holder) {
            with(binding) {

                var ingredientsText = ""
                for (i in 0 until 3) {
                    ingredientsText += meal.ingredients[i]
                }
                mealNameTextView.text = meal.name
                mealIngredientsTextView.text = ingredientsText
                mealPrice.text = String.format("%.2f", meal.price) + " TL"

                val options = RequestOptions().placeholder(R.drawable.ic_burger)
                Glide.with(mealImageView.context)
                    .applyDefaultRequestOptions(options)
                    .load(meal.imageUrl).into(mealImageView)
            }

            itemView.setOnClickListener {
                listener?.onClick(meal)

            }

        }
    }

    fun setMeals(meals: ArrayList<Meal>) {
        this.meals = meals
        notifyDataSetChanged()
    }

    fun setListener(listener: IMealOnClick) {
        this.listener = listener
    }

    fun removeListeners() {
        this.listener = null
    }

    override fun getItemCount(): Int = meals.size
}