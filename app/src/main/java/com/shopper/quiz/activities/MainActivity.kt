package com.shopper.quiz.activities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shopper.quiz.R
import com.shopper.quiz.databinding.ActivityMainBinding
import com.shopper.quiz.di.components.DaggerViewModelComponent
import com.shopper.quiz.di.modules.ContextModule
import com.shopper.quiz.fragments.ImageAndRatingsDialogFragment
import com.shopper.quiz.models.Movies
import com.shopper.quiz.utils.simpleClassName
import com.shopper.quiz.viewmodels.MoviesViewModel
import com.shopper.quiz.views.MoviesItemView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.item_list.view.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MoviesViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapterMoviesList = GroupAdapter<GroupieViewHolder>().apply {
        setOnItemClickListener { item, _ ->
            if (item is MoviesItemView) {
                val sheet = ImageAndRatingsDialogFragment.newInstance(item.movies)
                sheet.show(supportFragmentManager, sheet.simpleClassName())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.appBar.toolbar)
        DaggerViewModelComponent.builder().contextModule(ContextModule(this)).build().inject(this)
        binding.viewModel = viewModel
        bindViewModel()
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val searchManager =
            this.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView =
            menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
            searchManager.getSearchableInfo(componentName)
        )
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchMovieByName(newText)
                return false
            }
        })
        return true
    }

    private fun bindViewModel() {
        viewModel.movies.observe(this, Observer(this::setMovies))
    }

    private fun setMovies(listMovies: List<Movies>) {
        adapterMoviesList.apply {
            if(listMovies.isNotEmpty()){ clear() }
            listMovies.forEach {
                add(MoviesItemView(it))
            }
        }
    }

    private fun initRecyclerView() {
        binding.frameLayout.item_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterMoviesList
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

}
