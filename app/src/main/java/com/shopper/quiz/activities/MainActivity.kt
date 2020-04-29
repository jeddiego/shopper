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
    var length = 0
    var oldLength = 0
    var flagLocalSearch = false
    var queryFlagLocalSearch = ""

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
                localSearch(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                oldLength = length
                length = query.length

                if (oldLength > length) {
                    localSearch(query)
                }
                return false
            }
        })
        return true
    }

    private fun localSearch(query: String) {
        if (query.length > 2) {
            viewModel.searchMovieByName(query, onlyLocal = true, findMovieOnline = false)
        } else {
            viewModel.searchMovieByName("", onlyLocal = true, findMovieOnline = false)
        }
        flagLocalSearch = true
        queryFlagLocalSearch = query
    }

    private fun bindViewModel() {
        viewModel.movies.observe(this, Observer(this::setMovies))
    }

    private fun setMovies(listMovies: List<Movies>) {
        adapterMoviesList.apply {
            clear()
            listMovies.forEach {
                add(MoviesItemView(it))
            }
        }
        if (listMovies.isNullOrEmpty() && flagLocalSearch) {
            viewModel.searchMovieByName(
                queryFlagLocalSearch,
                onlyLocal = false,
                findMovieOnline = true
            )
            flagLocalSearch = false
            queryFlagLocalSearch = ""
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
