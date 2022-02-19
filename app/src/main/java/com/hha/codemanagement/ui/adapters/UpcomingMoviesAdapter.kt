package com.hha.codemanagement.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hha.codemanagement.R
import com.hha.codemanagement.data.entities.PopularMovie
import com.hha.codemanagement.data.entities.UpcomingMovie
import com.hha.codemanagement.databinding.ItemMoviesBinding
import com.hha.codemanagement.databinding.ItemUpcomingMoviesBinding
import com.hha.codemanagement.utils.DiffCallBack
import com.hha.codemanagement.utils.FavoriteDelegate
import com.hha.codemanagement.utils.MovieDelegate
import com.hha.codemanagement.utils.Utils
import kotlin.properties.Delegates

class UpcomingMoviesAdapter(private var movieDelegate : MovieDelegate, private var favoriteDelegate : FavoriteDelegate) : RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingMoviesViewHolder>(), DiffCallBack
{

    var data : MutableList<UpcomingMovie> by Delegates.observable(ArrayList()) { _, old, new ->
        compareItem(old, new) { o, n -> o.id.toString() === n.id.toString() }
    }

    inner class UpcomingMoviesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

        private lateinit var binding : ItemUpcomingMoviesBinding

        fun bindData(upcomingMovie : UpcomingMovie)
        {
            binding = ItemUpcomingMoviesBinding.bind(itemView)
            binding.apply {
                tvUpcomingTitle.text = upcomingMovie.title
                tvUpcomingDescription.text = upcomingMovie.overview
                tvUpcomingPopular.text = upcomingMovie.popularity.toString()
                Glide.with(itemView.context)
                        .load(Utils.IMG_BASE_URL + upcomingMovie.poster_path)
                        .into(ivUpcomingMovie)

                root.setOnClickListener {
                    movieDelegate.onTapMovie(upcomingMovie.id.toString())
                }
                ivUpcomingFavorite.setOnClickListener {

                    favoriteDelegate.onTapFavorite(upcomingMovie.id.toString())
                }
            }
        }

    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : UpcomingMoviesViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_upcoming_movies, parent, false)

        return UpcomingMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder : UpcomingMoviesViewHolder, position : Int)
    {

        holder.bindData(data[position])
    }

    override fun getItemCount() : Int
    {
        return data.size
    }
}