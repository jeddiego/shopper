package com.shopper.quiz.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shopper.quiz.activities.MovieDetailActivity
import com.shopper.quiz.databinding.DialogMovieRatingBinding
import com.shopper.quiz.models.Movies
import com.shopper.quiz.utils.Constants.Web.IMAGE_URL
import com.shopper.quiz.views.BaseBottomSheet
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class ImageAndRatingsDialogFragment : BaseBottomSheet() {
    private lateinit var binding: DialogMovieRatingBinding
    private lateinit var movie: Movies

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogMovieRatingBinding.inflate(layoutInflater)
        arguments?.getParcelable<Movies>(MOVIE).let {
            if (it != null) {
                movie = it
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.with(context)
            .load(IMAGE_URL + movie.posterPath)
            .transform(RoundedCornersTransformation(15, 1))
            .into(binding.imageViewPoster)
        Picasso.with(context)
            .load(IMAGE_URL + movie.posterPath)
            .fit()
            .transform(BlurTransformation(context, 100, 1))
            .into(binding.imageViewBackground)
        binding.textViewRating.text = movie.voteAverage.toString()
        binding.layoutContainer.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(MovieDetailActivity.ID, movie.id)
                putExtra(MovieDetailActivity.TITLE, movie.title)
                putExtra(MovieDetailActivity.DESCRIPTION, movie.overview)
                putExtra(MovieDetailActivity.POSTER_PATH, movie.posterPath)
                putExtra(MovieDetailActivity.RATING, movie.voteAverage)
            }
            startActivity(intent)
        }
    }

    companion object {
        private const val MOVIE = "movie_json"

        @JvmStatic
        fun newInstance(
            movie: Movies
        ): ImageAndRatingsDialogFragment {
            val args = Bundle().apply {
                putParcelable(MOVIE, movie)
            }

            return ImageAndRatingsDialogFragment().apply {
                arguments = args
            }
        }
    }
}