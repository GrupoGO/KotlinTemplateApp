package es.grupogo.cocktailsapp.data.server

/**
 * Created by jorge_cmata on 24/8/17.
 */
data class CocktailServer(val idDrink: String, val strDrinkThumb: String?, val strDrink: String)

data class DrinksServer(val drinks: List<CocktailServer>)