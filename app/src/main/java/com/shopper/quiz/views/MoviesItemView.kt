package com.shopper.quiz.views

import com.shopper.quiz.R
import com.shopper.quiz.databinding.ItemMovieBinding
import com.shopper.quiz.models.Movies
import com.xwray.groupie.databinding.BindableItem

class MoviesItemView(
    val movies: Movies
) : BindableItem<ItemMovieBinding>() {
    override fun getLayout(): Int = R.layout.item_movie

    override fun bind(viewBinding: ItemMovieBinding, position: Int) {
        viewBinding.textViewTitle.text = movies.title
    }

}