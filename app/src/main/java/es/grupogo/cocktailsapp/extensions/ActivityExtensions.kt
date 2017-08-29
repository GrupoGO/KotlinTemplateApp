package es.grupogo.cocktailsapp.extensions

import android.support.v7.app.AppCompatActivity
import android.widget.TextView

/**
 * Created by jorge_cmata on 28/8/17.
 */
fun AppCompatActivity.initToolbar(idToolbar: Int, idToolbarTitle: Int, title: String, ishomeEnabled: Boolean){
    this.setSupportActionBar(findViewById(idToolbar))
    this.supportActionBar?.title = "" //Default title
    this.supportActionBar?.setHomeButtonEnabled(ishomeEnabled)

    val titleTV: TextView = findViewById(idToolbarTitle)
    titleTV.text = title
}
