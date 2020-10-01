package com.example.cookbook.presentation.mainflow.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.R
import com.example.cookbook.domain.models.CookingStep
import kotlinx.android.synthetic.main.item_cookingstep.view.*

class CookingAdapter : RecyclerView.Adapter<CookingAdapter.ViewHolder>() {

    var cookingSteps = mutableListOf<CookingStep>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CookingAdapter.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cookingstep, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CookingAdapter.ViewHolder, position: Int) {
        holder.bind(cookingSteps[position])
    }

    override fun getItemCount(): Int {
        return cookingSteps.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(cookingStep: CookingStep) {
            itemView.descriptionTextView.text = cookingStep.description
        }

    }

}