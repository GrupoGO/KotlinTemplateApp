package es.grupogo.cocktailsapp.ui.activities.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import es.grupogo.cocktailsapp.R
import es.grupogo.cocktailsapp.domain.Cocktail
import es.grupogo.cocktailsapp.extensions.initToolbar
import es.grupogo.cocktailsapp.extensions.loadImage
import es.grupogo.cocktailsapp.ui.dialogs.EditTextDialog
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.toast

/**
 * Created by jorge_cmata on 4/9/17.
 */
class DetailActivity : AppCompatActivity(), DetailContract.View {

    companion object Intents {

        private val EXTRA_ID = "extra_id"
        fun newIntent(context: Context, id: String): Intent {

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            return intent
        }
    }

    private lateinit var mPresenter: DetailContract.Presenter
    private lateinit var mCocktailId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        this.initToolbar(R.id.toolbar, "", true)
        //Get extras
        mCocktailId = intent.getStringExtra(EXTRA_ID)

        //Instantiate presenter
        DetailPresenter(this)
        //Kick off presenter
        mPresenter.start()
        mPresenter.getDetailData(mCocktailId)

        //Set listeners
        setListeners()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    //Called from presenter once instantiated
    override fun setPresenter(presenter: DetailContract.Presenter) {
        mPresenter = presenter
    }

    override fun handleError(throwable: Throwable) {
        toast(throwable.message.toString())
    }

    fun setListeners(){
        cocktail_name_textview.setOnClickListener {
            val dialog = EditTextDialog.newInstance("New cocktail's name", null,"Ok", "Cancel")
            dialog.setCallback(object : EditTextDialog.EditTextDialogListener {
                override fun onPositiveClick(text: String) = mPresenter.updateCocktail(mCocktailId, text)

                override fun onNegativeClick() {}
            })
            dialog.show(supportFragmentManager, "tag")
        }
    }

    override fun getContext(): Context = applicationContext

    override fun showData(cocktail: Cocktail) {
        cocktail.imageUrl?.let { cocktail_detail_imageview.loadImage(it) }
        cocktail_name_textview.text = cocktail.name
    }

    override fun refreshView() {
        mPresenter.getDetailData(mCocktailId)
    }


    override fun showLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out)
    }
}


