package com.kngrck.fooddeliveryfinal.ui.meal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kngrck.fooddeliveryfinal.databinding.ItemIngredientBinding

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {
    private var ingredients = ArrayList<String>()
    private var checkedIngredients = ArrayList<String>()

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
                ingredientCheckBox.text = ingredient
                ingredientCheckBox.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) checkedIngredients.add(ingredient)
                    else checkedIngredients.remove(ingredient)
                }
            }

        }
    }

    fun setIngredients(ingredients: ArrayList<String>) {
        this.ingredients = ingredients
        notifyDataSetChanged()
    }

    fun getCheckedIngredients(): ArrayList<String> = checkedIngredients

//    fun setListener(listener: IMealOnClick) {
//        this.listener = listener
//    }
//
//    fun removeListeners() {
//        this.listener = null
//    }

    override fun getItemCount(): Int = ingredients.size
}