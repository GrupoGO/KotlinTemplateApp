package es.grupogo.cocktailsapp.ui.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import es.grupogo.cocktailsapp.domain.Cocktail
import kotlinx.android.synthetic.main.list_item_cocktail.view.*


/**
 * Created by jorge_cmata on 24/8/17.
 */
class CocktailViewHolder(itemView: View, itemClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener { itemClick(adapterPosition) }
    }

    fun bindItem(c: Cocktail) {

        itemView.textViewName.text = c.name
    }
}