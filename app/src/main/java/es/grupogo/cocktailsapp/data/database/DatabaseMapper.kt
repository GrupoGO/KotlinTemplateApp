package es.grupogo.cocktailsapp.data.database

import es.grupogo.cocktailsapp.domain.Cocktail

/**
 * Created by jorge_cmata on 1/9/17.
 */
object DatabaseMapper {

    fun convertToCocktailRealmList(cocktails: List<Cocktail>): List<CocktailRealm> {
        return cocktails.map {
            convertToCocktailRealm(it)
        }
    }

    fun convertToCocktailRealm(cocktail: Cocktail): CocktailRealm {
        return CocktailRealm(cocktail.id, cocktail.name, cocktail.imageUrl)
    }

    fun convertToCocktailList(cocktailsRealm: List<CocktailRealm>): List<Cocktail> {
        return cocktailsRealm.map {
            convertToCocktail(it)
        }
    }

    fun convertToCocktail(cocktailRealm: CocktailRealm): Cocktail {
        return Cocktail(cocktailRealm.id, cocktailRealm.name, cocktailRealm.imageUrl)
    }
}