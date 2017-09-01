package es.grupogo.cocktailsapp.ui.activities.signin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import es.grupogo.cocktailsapp.R
import es.grupogo.cocktailsapp.ui.activities.mainActivity.MainActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.toast

/**
 * Created by jorge_cmata on 28/8/17.
 */
class SignInActivity : AppCompatActivity(), SignInContract.View {

    companion object Intents {

        fun newIntent(context: Context) : Intent {

            val intent = Intent(context, SignInActivity::class.java)
            return intent
        }
    }

    private lateinit var mPresenter : SignInContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //Instantiate presenter
        SignInPresenter(this)
        //Kick off presenter
        mPresenter.start()

        buttonSignIn.setOnClickListener { mPresenter.doSignIn(editTextEmail.text.toString(), editTextPassword.text.toString()) }
    }

    //Called from presenter once instantiated
    override fun setPresenter(presenter: SignInContract.Presenter) {
        mPresenter = presenter
    }

    override fun handleError(throwable: Throwable) {
        toast(throwable.message.toString())
    }

    override fun getContext(): Context = applicationContext


    override fun enterApp() {

        startActivity(MainActivity.newIntent(this))
    }

    override fun hideLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoader() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}