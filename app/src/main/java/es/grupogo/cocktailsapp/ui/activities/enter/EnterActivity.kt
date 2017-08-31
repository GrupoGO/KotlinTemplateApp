package es.grupogo.cocktailsapp.ui.activities.enter

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import es.grupogo.cocktailsapp.R
import es.grupogo.cocktailsapp.ui.activities.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_enter.*

/**
 * Created by jorge_cmata on 28/8/17.
 */
class EnterActivity : AppCompatActivity(), EnterContract.View {


    private lateinit var mPresenter : EnterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)

        //Instantiate presenter
        EnterPresenter(this)
        //Kick off presenter
        mPresenter.start()

        buttonSignIn.setOnClickListener { startActivity(SignInActivity.newIntent(this)) }
        //buttonSignUp.setOnClickListener { startActivity(SignUpActivity.newIntent(this)) }
    }

    //Called from presenter once instantiated
    override fun setPresenter(presenter: EnterContract.Presenter) {
        mPresenter = presenter
    }

    override fun goToSignIn() {
        startActivity(SignInActivity.newIntent(this))
    }

    override fun goToSignUp() {
        //startActivity(SignUpActivity.newIntent(this))
    }

    override fun handleError(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}