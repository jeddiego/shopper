package com.shopper.quiz.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.shopper.quiz.R
import com.shopper.quiz.databinding.ActivityMovieDetailBinding
import com.shopper.quiz.di.components.DaggerViewModelComponent
import com.shopper.quiz.di.modules.ContextModule
import com.shopper.quiz.models.Movies
import com.shopper.quiz.utils.Constants.Web.IMAGE_URL
import com.shopper.quiz.viewmodels.DetailViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityMovieDetailBinding
    private val id: Int by lazy {
        intent.getIntExtra(ID, 0)
    }
    private val title: String by lazy {
        intent.getStringExtra(TITLE)
    }
    private val description: String by lazy {
        intent.getStringExtra(DESCRIPTION)
    }
    private val posterPath: String by lazy {
        intent.getStringExtra(POSTER_PATH)
    }
    private val rating: Double by lazy {
        intent.getDoubleExtra(RATING, 0.0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        DaggerViewModelComponent.builder().contextModule(ContextModule(this)).build().inject(this)
        bindView()
        binding.movie = Movies(
            id,
            title,
            description,
            posterPath,
            "",
            rating,
            0
        )
    }

    private fun bindView() {
        Picasso.with(this)
            .load(IMAGE_URL + posterPath)
            .fit()
            .transform(BlurTransformation(this, 100, 1))
            .into(binding.imageViewBackground)
        Picasso.with(this)
            .load(IMAGE_URL + posterPath)
            .transform(RoundedCornersTransformation(15, 1))
            .into(binding.imageViewPoster)
        binding.checkBoxSinopsis.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.description.visibility = if (isChecked) View.VISIBLE else View.GONE
        }
    }

    companion object {
        const val ID = "id"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val POSTER_PATH = "poster_path"
        const val RATING = "rating"

        @JvmStatic
        fun getIntent(
            context: Context,
            id: Int,
            title: String,
            description: String,
            posterPath: String,
            rating: Double
        ): Intent {
            return Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(ID, id)
                putExtra(TITLE, title)
                putExtra(DESCRIPTION, description)
                putExtra(POSTER_PATH, posterPath)
                putExtra(RATING, rating)
            }
        }
    }

}