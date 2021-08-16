package com.kngrck.fooddeliveryfinal.ui.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kngrck.fooddeliveryfinal.databinding.ItemIngredientBinding
import com.kngrck.fooddeliveryfinal.model.entity.meal.Ingredient
import com.kngrck.fooddeliveryfinal.model.entity.meal.Meal
import com.kngrck.fooddeliveryfinal.ui.restaurant.IMealOnClick

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {
    private var ingredients = ArrayList<Ingredient>()

    inner class IngredientsViewHolder(val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val binding = ItemIngredientBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val ingredient = ingredients[position]
        with(holder) {
            with(binding) {
                ingredientCheckBox.text = ingredient.name
                ingredientCheckBox.isChecked = ingredient.isChecked
            }

        }
    }

    fun setIngredients(ingredients: ArrayList<Ingredient>) {
        this.ingredients = ingredients
        notifyDataSetChanged()
    }

//    fun setListener(listener: IMealOnClick) {
//        this.listener = listener
//    }
//
//    fun removeListeners() {
//        this.listener = null
//    }

    override fun getItemCount(): Int = ingredients.size
}