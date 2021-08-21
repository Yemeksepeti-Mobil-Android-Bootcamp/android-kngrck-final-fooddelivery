package com.kngrck.fooddeliveryfinal.ui.editmeal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.ItemEditMealBinding
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal

class MealListAdapter : RecyclerView.Adapter<MealListAdapter.MealListViewHolder>() {
    private var meals = ArrayList<Meal>()
    private var listener: IOnDeleteMeal? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListViewHolder {
        val binding = ItemEditMealBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MealListViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MealListViewHolder, position: Int) {
        val meal = meals[position]
        with(holder) {
            with(binding) {

                mealName.text = meal.name

                val options = RequestOptions().placeholder(R.drawable.ic_burger)
                Glide.with(mealImageView.context)
                    .applyDefaultRequestOptions(options)
                    .load(meal.imageUrl).into(mealImageView)

                deleteMealButton.setOnClickListener {
                    meals.remove(meal)
                    listener?.onDeleteMeal(meal.id)
                    notifyDataSetChanged()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setMeals(meals: ArrayList<Meal>) {
        this.meals = meals
        notifyDataSetChanged()
    }

    fun setListener(listener: IOnDeleteMeal) {
        this.listener = listener
    }

    fun removeListeners() {
        this.listener = null
    }

    override fun getItemCount(): Int = meals.size

    inner class MealListViewHolder(val binding: ItemEditMealBinding) :
        RecyclerView.ViewHolder(binding.root)
}