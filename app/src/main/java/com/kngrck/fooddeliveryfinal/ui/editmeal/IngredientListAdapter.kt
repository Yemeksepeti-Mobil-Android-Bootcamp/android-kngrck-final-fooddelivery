package com.kngrck.fooddeliveryfinal.ui.editmeal

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kngrck.fooddeliveryfinal.databinding.ItemEditIngredientBinding

class IngredientListAdapter :
    RecyclerView.Adapter<IngredientListAdapter.IngredientListViewHolder>() {
    private var ingredients = ArrayList<String>()

    inner class IngredientListViewHolder(val binding: ItemEditIngredientBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientListViewHolder {
        val binding = ItemEditIngredientBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientListViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: IngredientListViewHolder, position: Int) {
        val ingredient = ingredients[position]
        with(holder) {
            with(binding) {
                ingredientTextView.text = ingredient
                itemView.setOnClickListener {
                    ingredients.remove(ingredient)
                    notifyDataSetChanged()
                }

            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setIngredients(ingredients: ArrayList<String>) {
        this.ingredients = ingredients
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addIngredient(ingredient: String){
        ingredients.add(ingredient)
        notifyDataSetChanged()
    }

    fun getIngredients(): ArrayList<String> = ingredients


    override fun getItemCount(): Int = ingredients.size
}