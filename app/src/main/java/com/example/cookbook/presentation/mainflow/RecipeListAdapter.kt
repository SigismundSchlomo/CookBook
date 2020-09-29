package com.example.cookbook.presentation.mainflow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.R
import com.example.cookbook.domain.models.Recipe
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_view_recipe.view.*

class RecipeListAdapter :
    RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    var items = mutableListOf<Recipe>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun removeAt(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_recipe, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(recipe: Recipe) {
            itemView.itemHeader.text = recipe.header
            itemView.recipeBody.text = recipe.header
        }

    }
}