package es.grupogo.cocktailsapp.ui.activities.mainActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import es.grupogo.cocktailsapp.R
import es.grupogo.cocktailsapp.domain.Cocktail
import es.grupogo.cocktailsapp.ui.adapters.CockailsRecyclerAdapter
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity(), FeedContract.View {

    private lateinit var mPresenter : FeedContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        //Instantiate presenter
        FeedPresenter(this)
        //Kick off presenter
        mPresenter.start()
    }

    //Called from presenter once instantiated
    override fun setPresenter(presenter: FeedContract.Presenter) {
        mPresenter = presenter
    }

    override fun setRecyclerItems(items: List<Cocktail>){
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = CockailsRecyclerAdapter(items)
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message,  Toast.LENGTH_SHORT).show()
    }

}
