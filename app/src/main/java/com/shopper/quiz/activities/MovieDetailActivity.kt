package com.shopper.quiz.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.shopper.quiz.R
import com.shopper.quiz.databinding.ActivityMovieDetailBinding
import com.shopper.quiz.di.components.DaggerViewModelComponent
import com.shopper.quiz.di.modules.ContextModule
import com.shopper.quiz.viewmodels.DetailViewModel
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        DaggerViewModelComponent.builder().contextModule(ContextModule(this)).build().inject(this)
        bindViewModel()
    }

    //TODO Add init view data
    private fun bindViewModel() {

    }

}