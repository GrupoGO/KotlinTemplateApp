package es.grupogo.cocktailsapp.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import es.grupogo.cocktailsapp.R
import es.grupogo.cocktailsapp.domain.Cocktail
import es.grupogo.cocktailsapp.ui.adapters.CockailsRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_feed.*

/**
 * Created by jorge_cmata on 28/8/17.
 */
class FeedFragment : Fragment() , FeedContract.View {

    //New instance
    companion object {
        fun newInstance(): FeedFragment {
            val fragment = FeedFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mPresenter : FeedContract.Presenter
    private lateinit var mRecycler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Instantiate presenter
        FeedPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)

        //Kick off presenter
        mPresenter.start()
    }

    private fun bindViews(view: View){
        mRecycler = view.findViewById(R.id.recycler)
        mRecycler.layoutManager = LinearLayoutManager(context)
    }

    override fun setPresenter(presenter: FeedContract.Presenter) {
        mPresenter = presenter
    }


    //-------View Functions-------//

    override fun setRecyclerItems(items: List<Cocktail>){
        mRecycler.adapter = CockailsRecyclerAdapter(items)
    }

    override fun toast(message: String?) {
        Toast.makeText(context, message,  Toast.LENGTH_SHORT).show()
    }
}