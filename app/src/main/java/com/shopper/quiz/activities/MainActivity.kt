package com.shopper.quiz.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.android.synthetic.main.item_list.view.item_list
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MoviesViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapterMoviesList = GroupAdapter<GroupieViewHolder>().apply {
        setOnItemClickListener { item, _ ->
            if (item is MoviesItemView) {
                val sheet = ImageAndRatingsDialogFragment.newInstance(
                    item.movies.posterPath,
                    item.movies.id,
                    item.movies.voteAverage
                )
                sheet.show(supportFragmentManager, sheet.simpleClassName())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        DaggerViewModelComponent.builder().contextModule(ContextModule(this)).build().inject(this)
        binding.viewModel = viewModel
        bindViewModel()
        initRecyclerView()
    }

    private fun bindViewModel() {
        viewModel.getMovies().observe(this, Observer(this::setMovies))
    }

    private fun setMovies(listMovies: List<Movies>) {
        adapterMoviesList.apply {
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
