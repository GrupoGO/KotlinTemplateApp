package es.grupogo.cocktailsapp.ui.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import es.grupogo.cocktailsapp.domain.Cocktail
import kotlinx.android.synthetic.main.list_item_cocktail.view.*


/**
 * Created by jorge_cmata on 24/8/17.
 */
class CocktailViewHolder(itemView: View, itemClick: (Cocktail) -> Unit) : RecyclerView.ViewHolder(itemView) {

    private lateinit  var item : Cocktail

    init {
        itemView.setOnClickListener { itemClick(item) }
    }

    fun bindItem(c: Cocktail) {

        item = c
        itemView.textViewName.text = c.name
    }
}