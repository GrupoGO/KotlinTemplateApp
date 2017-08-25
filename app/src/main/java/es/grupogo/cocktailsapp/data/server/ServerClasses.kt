package es.grupogo.cocktailsapp.data.server

/**
 * Created by jorge_cmata on 24/8/17.
 */
data class CocktailJSONObj(val idDrink: String, val strDrinkThumb: String?, val strDrink: String)

data class DrinksJSONObj(val drinks: List<CocktailJSONObj>)