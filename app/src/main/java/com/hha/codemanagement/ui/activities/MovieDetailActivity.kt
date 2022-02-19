package com.hha.codemanagement.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hha.codemanagement.databinding.ActivityMainBinding
import com.hha.codemanagement.databinding.ActivityMovieDetailBinding
import com.hha.codemanagement.ui.adapters.CastAdapter
import com.hha.codemanagement.ui.viewmodels.MovieViewModel
import com.hha.codemanagement.utils.Resource
import com.hha.codemanagement.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import android.R
import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import com.hha.codemanagement.data.entities.Favorite

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity()
{

    companion object
    {
        fun newIntent(context : Context) : Intent
        {
            return Intent(context, MovieDetailActivity::class.java)
        }
    }

    private lateinit var binding : ActivityMovieDetailBinding

    private lateinit var mAdapter : CastAdapter

    private val viewModel : MovieViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            rvCast.layoutManager = LinearLayoutManager(this@MovieDetailActivity, LinearLayoutManager.HORIZONTAL, false)

            mAdapter = CastAdapter()
            rvCast.adapter = mAdapter

            val id = intent.getStringExtra("movie_id")
            if (id != null)
            {

                viewModel.getMovieDetail(id)
                viewModel.getCast(id)

                viewModel.getFavorite(id).observe(this@MovieDetailActivity, Observer {
                    if (it != null)
                    {
                        ivFavorite.setImageResource(com.hha.codemanagement.R.drawable.ic_baseline_favorite_24)
                        Log.i("hhafavorite", it.id)
                    }
                    else
                    {

                        ivFavorite.setImageResource(com.hha.codemanagement.R.drawable.ic_baseline_favorite_24_white)
                    }
                })
                ivFavorite.setOnClickListener(object : View.OnClickListener
                {
                    var button01pos = 0
                    override fun onClick(v : View?)
                    {
                        if (button01pos == 0)
                        {
                            ivFavorite.setImageResource(com.hha.codemanagement.R.drawable.ic_baseline_favorite_24)
                            button01pos = 1

                            val favorite = Favorite(id)
                            viewModel.insertFavorite(favorite)
                            Toast.makeText(applicationContext, "Added to Favorite!", Toast.LENGTH_SHORT).show()

                        }
                        else if (button01pos == 1)
                        {
                            ivFavorite.setImageResource(com.hha.codemanagement.R.drawable.ic_baseline_favorite_24_white)
                            button01pos = 0

                            viewModel.deleteFavorite(id !!)
                            Toast.makeText(applicationContext, "Remove from Favorite!", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }

        }
        viewModel.getMovieDetail.observe(this, Observer {
            if (it.data != null)
            {

                binding.apply {
                    tvDetailTitle.text = it.data.title
                    tvDetailDate.text = it.data.release_date
                    tvDetailDuration.text = it.data.runtime.toString()+" min"
                    tvLanguage.text = it.data.original_language
                    tvVote.text = it.data.vote_count.toString()
                    tvDetailOverViewDescription.text = it.data.overview
                    tvPopular.text = it.data.popularity.toString()
                    Glide.with(applicationContext)
                            .load(Utils.IMG_BASE_URL + it.data.backdrop_path)
                            .into(ivDetailImage)

                }

            }
            else
            {
                Toast.makeText(applicationContext, it.errorMessage.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.cast.observe(this, Observer {
            if (it.data != null)
            {
                mAdapter.data = it.data.cast as MutableList
            }
        })

    }
}