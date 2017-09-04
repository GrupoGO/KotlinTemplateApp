package es.grupogo.cocktailsapp.data.server

import es.grupogo.cocktailsapp.domain.Cocktail

/**
 * Created by jorge_cmata on 25/8/17.
 */
object ServerMapper {

    fun convertToCocktailList(drinksObj: DrinksServer): List<Cocktail> {
        val cocktailServerList: List<CocktailServer> = drinksObj.drinks
        return cocktailServerList.map{
            convertToCocktail(it)
        }
    }

    fun convertToCocktail(cocktailServer: CocktailServer): Cocktail {
        return Cocktail(cocktailServer.idDrink, cocktailServer.strDrink, cocktailServer.strDrinkThumb)
    }
}