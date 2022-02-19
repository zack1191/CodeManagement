package com.hha.codemanagement.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hha.codemanagement.R
import com.hha.codemanagement.data.entities.PopularMovie
import com.hha.codemanagement.databinding.ItemMoviesBinding
import com.hha.codemanagement.utils.DiffCallBack
import com.hha.codemanagement.utils.FavoriteDelegate
import com.hha.codemanagement.utils.MovieDelegate
import com.hha.codemanagement.utils.Utils
import kotlin.properties.Delegates

class PopularMoviesAdapter(private var movieDelegate : MovieDelegate, private var favoriteDelegate : FavoriteDelegate) : RecyclerView.Adapter<PopularMoviesAdapter.MoviesViewHolder>(), DiffCallBack
{

    var data : MutableList<PopularMovie> by Delegates.observable(ArrayList()) { _, old, new ->
        compareItem(old, new) { o, n -> o.id.toString() === n.id.toString() }
    }

    inner class MoviesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        private lateinit var binding : ItemMoviesBinding
        fun bindData(popularMovie : PopularMovie)
        {
            binding = ItemMoviesBinding.bind(itemView)
            binding.apply {
                tvTitle.text = popularMovie.title
                tvRating.text = popularMovie.vote_average.toString()

                Glide.with(itemView.context)
                        .load(Utils.IMG_BASE_URL + popularMovie.poster_path)
                        .into(ivMovie)

                root.setOnClickListener {
                    movieDelegate.onTapMovie(popularMovie.id.toString())
                }
                ivMovieFavorite.setOnClickListener {
                    favoriteDelegate.onTapFavorite(popularMovie.id.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MoviesViewHolder
    {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)

        return MoviesViewHolder(view)

    }

    override fun onBindViewHolder(holder : MoviesViewHolder, position : Int)
    {
        holder.bindData(data[position])
    }

    override fun getItemCount() : Int
    {
        return data.size
    }

}