package es.grupogo.cocktailsapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import es.grupogo.cocktailsapp.R
import es.grupogo.cocktailsapp.domain.Cocktail
import es.grupogo.cocktailsapp.ui.viewholders.CocktailViewHolder

/**
 * Created by jorge_cmata on 24/8/17.
 */
class CockailsRecyclerAdapter(var items: List<Cocktail>, val itemClick: (Cocktail) -> Unit) : RecyclerView.Adapter<CocktailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        return CocktailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_cocktail, parent, false), {itemClick(it)})
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size


    fun setDataSet(newItems : List<Cocktail>) {
        items = newItems
        notifyDataSetChanged()
    }
}