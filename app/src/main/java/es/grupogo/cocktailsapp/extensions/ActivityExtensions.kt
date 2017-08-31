package es.grupogo.cocktailsapp.extensions

import android.support.v7.app.AppCompatActivity
import es.grupogo.cocktailsapp.R

/**
 * Created by jorge_cmata on 28/8/17.
 */
fun AppCompatActivity.initToolbar(idToolbar: Int, title: String = "", ishomeEnabled: Boolean = true){

    setSupportActionBar(findViewById(idToolbar))
    supportActionBar?.title = title
    supportActionBar?.setHomeButtonEnabled(ishomeEnabled)
}
