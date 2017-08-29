package es.grupogo.cocktailsapp.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import es.grupogo.cocktailsapp.R

/**
 * Created by carlosolmedo on 28/8/17.
 */
fun ImageView.loadImage(url: String) {

    val options = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)

    Glide.with(this).load(url).apply(options).into(this)
}