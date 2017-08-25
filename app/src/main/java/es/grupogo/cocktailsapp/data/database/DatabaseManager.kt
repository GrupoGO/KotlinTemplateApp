package es.grupogo.cocktailsapp.data.database

import es.grupogo.cocktailsapp.domain.Cocktail

/**
 * Created by jorge_cmata on 25/8/17.
 */
class DatabaseManager : DatabaseInterface{

    //Singleton
    companion object DatabaseManagerProvider {
        fun provideDatabaseManager(): DatabaseManager {
            return DatabaseManager()
        }
    }

    override fun saveCocktails(cocktails: List<Cocktail>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun retrieveCocktails(): List<Cocktail> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}