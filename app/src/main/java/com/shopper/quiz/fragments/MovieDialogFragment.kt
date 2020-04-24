package com.shopper.quiz.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shopper.quiz.activities.MovieDetailActivity
import com.shopper.quiz.databinding.DialogMovieRatingBinding
import com.shopper.quiz.views.BaseBottomSheet
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

const val EXTRA_ID_MOVIE = "id_movie"

class ImageAndRatingsDialogFragment : BaseBottomSheet() {
    lateinit var binding: DialogMovieRatingBinding
    lateinit var posterPath: String
    var idMovie: Int = 0
    private var rating = 0.0
    private val baseUrl = "https://image.tmdb.org/t/p/w440_and_h660_face"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogMovieRatingBinding.inflate(layoutInflater)
        arguments?.let {
            posterPath = it.getString(POSTER_PATH).orEmpty()
            idMovie = it.getInt(ID_MOVIE)
            rating = it.getDouble(RATING)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.with(context)
            .load(baseUrl + posterPath)
            .transform(RoundedCornersTransformation(15, 1))
            .into(binding.imageViewPoster)
        Picasso.with(context)
            .load(baseUrl + posterPath)
            .fit()
            .transform(BlurTransformation(context, 100, 1))
            .into(binding.imageViewBackground)
        binding.textViewRating.text = rating.toString()
        binding.layoutContainer.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(EXTRA_ID_MOVIE, idMovie)
            }
            startActivity(intent)
        }
    }

    companion object {
        private const val POSTER_PATH = "poster_path"
        private const val RATING = "rating"
        private const val ID_MOVIE = "id_movie"

        @JvmStatic
        fun newInstance(
            posterPath: String,
            idMovie: Int,
            rating: Double
        ): ImageAndRatingsDialogFragment {
            val args = Bundle().apply {
                putString(POSTER_PATH, posterPath)
                putInt(ID_MOVIE, idMovie)
                putDouble(RATING, rating)
            }

            return ImageAndRatingsDialogFragment().apply {
                arguments = args
            }
        }
    }
}