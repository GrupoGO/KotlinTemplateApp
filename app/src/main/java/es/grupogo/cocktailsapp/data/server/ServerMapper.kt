package es.grupogo.cocktailsapp.data.server

import es.grupogo.cocktailsapp.domain.Cocktail

/**
 * Created by jorge_cmata on 25/8/17.
 */
object ServerMapper {

    fun convertToCocktailList(drinksObj: DrinksJSONObj): List<Cocktail> {
        val cocktailJsonObjList: List<CocktailJSONObj> = drinksObj.drinks
        return cocktailJsonObjList.map{
            convertToCocktail(it)
        }
    }

    fun convertToCocktail(cocktailJSONObj: CocktailJSONObj): Cocktail {
        return Cocktail(cocktailJSONObj.idDrink, cocktailJSONObj.strDrink, cocktailJSONObj.strDrinkThumb)
    }
}