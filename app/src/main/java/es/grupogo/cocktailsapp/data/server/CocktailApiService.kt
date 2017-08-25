package es.grupogo.cocktailsapp.data.server

import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Created by jorge_cmata on 24/8/17.
 */
interface CocktailApiService {

    @retrofit2.http.GET("filter.php")
    fun requestCocktails(@retrofit2.http.Query("c") query: String) : Observable<DrinksJSONObj>


    /**
     * Companion object for the factory
     */
    companion object Factory {
        fun create(): CocktailApiService {
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .baseUrl("http://www.thecocktaildb.com/api/json/v1/1/")
                    .build()

            return retrofit.create(CocktailApiService::class.java)
        }
    }
}