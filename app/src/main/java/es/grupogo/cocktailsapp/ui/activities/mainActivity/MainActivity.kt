package es.grupogo.cocktailsapp.ui.activities.mainActivity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import es.grupogo.cocktailsapp.R
import es.grupogo.cocktailsapp.ui.fragments.FeedFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by jorge_cmata on 28/8/17.
 */
class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var mPresenter : MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNavigationBar()

        //Instantiate presenter
        MainPresenter(this)
        //Kick off presenter
        mPresenter.start()
    }

    //Called from presenter once instantiated
    override fun setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter
    }

    fun setupBottomNavigationBar(){
        //Set default fragment
        bottom_bar.selectedItemId = 0
        showFeedFragment()

        //Set listener
        bottom_bar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.goto_feed -> showFeedFragment()
                R.id.goto_settings -> showFeedFragment()
            }
            true //SetOnNavigationItemSelectedListener has to return a boolean type
        }
    }

    private fun showFeedFragment(){
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, FeedFragment.newInstance(), "feedFragment")
                .commit()
    }


}