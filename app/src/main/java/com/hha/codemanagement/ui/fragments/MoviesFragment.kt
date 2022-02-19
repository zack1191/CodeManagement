package com.hha.codemanagement.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hha.codemanagement.databinding.FragmentMoviesBinding
import com.hha.codemanagement.ui.activities.MovieDetailActivity
import com.hha.codemanagement.ui.adapters.PopularMoviesAdapter
import com.hha.codemanagement.ui.adapters.UpcomingMoviesAdapter
import com.hha.codemanagement.ui.viewmodels.MovieViewModel
import com.hha.codemanagement.utils.FavoriteDelegate
import com.hha.codemanagement.utils.MovieDelegate
import com.hha.codemanagement.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(), MovieDelegate,FavoriteDelegate
{
    private var _binding : FragmentMoviesBinding? = null
    private val binding get() = _binding !!

    private val viewModel : MovieViewModel by activityViewModels()

    private lateinit var mAdapterPopular : PopularMoviesAdapter
    private lateinit var mUpcomingAdapter : UpcomingMoviesAdapter

    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?,
                              savedInstanceState : Bundle?) : View?
    {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            mAdapterPopular = PopularMoviesAdapter(this@MoviesFragment,this@MoviesFragment)
            rvMovies.adapter = mAdapterPopular


            rvUpcomingMovies.layoutManager = LinearLayoutManager(requireContext())
            mUpcomingAdapter = UpcomingMoviesAdapter(this@MoviesFragment,this@MoviesFragment)
            rvUpcomingMovies.adapter = mUpcomingAdapter

            setUpObserver()


        }
    }

    private fun setUpObserver()
    {
        viewModel.popularMovie.observe(viewLifecycleOwner, Observer {
            when (it.status)
            {
                Resource.Status.SUCCESS ->
                {
                    //                    binding.progressBar.visibility = View.GONE
                    if (! it.data.isNullOrEmpty()) mAdapterPopular.data = it.data as MutableList
                }

                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    Toast.makeText(requireContext(), "Loading!", Toast.LENGTH_SHORT).show()

                //                    binding.progressBar.visibility = View.VISIBLE
            }
        })
        viewModel.upcomingMovie.observe(viewLifecycleOwner, Observer {
            when (it.status)
            {
                Resource.Status.SUCCESS ->
                {
                    //                    binding.progressBar.visibility = View.GONE
                    if (! it.data.isNullOrEmpty()) mUpcomingAdapter.data = it.data as MutableList
                }

                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    Toast.makeText(requireContext(), "Loading!", Toast.LENGTH_SHORT).show()

                //                    binding.progressBar.visibility = View.VISIBLE
            }
        })

    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }

    override fun onTapMovie(movieId : String)
    {
        val intent = MovieDetailActivity.newIntent(requireContext())
        intent.putExtra("movie_id", movieId)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    override fun onTapFavorite(id : String)
    {
        Toast.makeText(requireContext(), "Favorite!", Toast.LENGTH_SHORT).show()
    }

}