package com.example.cookbook.presentation.mainflow.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.R
import com.example.cookbook.domain.models.Ingredient
import kotlinx.android.synthetic.main.item_ingredeint.view.*

class IngredientAdapter : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    var ingredients = mutableListOf<Ingredient>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingredeint, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(ingredient: Ingredient) {
            itemView.ingredientName.text = ingredient.name
            itemView.ingredientAmount.text = ingredient.quantity
        }

    }

}