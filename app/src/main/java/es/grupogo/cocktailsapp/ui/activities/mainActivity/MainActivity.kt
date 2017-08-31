package es.grupogo.cocktailsapp.ui.activities.mainActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import es.grupogo.cocktailsapp.R
import es.grupogo.cocktailsapp.extensions.initToolbar
import es.grupogo.cocktailsapp.extensions.setFragment
import es.grupogo.cocktailsapp.ui.fragments.feed.FeedFragment
import es.grupogo.cocktailsapp.ui.fragments.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by jorge_cmata on 28/8/17.
 */
class MainActivity : AppCompatActivity(), MainContract.View {


    companion object Intents {

        fun newIntent(context: Context) : Intent {

            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }

    private lateinit var mPresenter : MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar(R.id.toolbar, "CocktailsApp")
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

    override fun handleError(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setupBottomNavigationBar(){
        //Set default fragment
        bottom_bar.selectedItemId = 0
        supportFragmentManager.setFragment(R.id.frame_layout, FeedFragment.newInstance(), "feedFragment")

        //Set listener
        bottom_bar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.goto_feed -> supportFragmentManager.setFragment(R.id.frame_layout, FeedFragment.newInstance(), "feedFragment")
                R.id.goto_settings -> supportFragmentManager.setFragment(R.id.frame_layout, SettingsFragment.newInstance(), "settingsFragment")
            }
            true //SetOnNavigationItemSelectedListener has to return a boolean type
        }
    }

}