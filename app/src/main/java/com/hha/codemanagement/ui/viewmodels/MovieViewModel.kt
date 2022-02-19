package com.hha.codemanagement.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hha.codemanagement.data.entities.Favorite
import com.hha.codemanagement.data.entities.MovieDetail
import com.hha.codemanagement.data.repositories.MoviesRepository
import com.hha.codemanagement.data.vo.Cast
import com.hha.codemanagement.utils.DataWrapper
import com.hha.codemanagement.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository : MoviesRepository) : ViewModel()
{
    private val _getMovieDetail = MutableLiveData<DataWrapper<MovieDetail?>>()
    val getMovieDetail : MutableLiveData<DataWrapper<MovieDetail?>> get() = _getMovieDetail

    private val _getCast = MutableLiveData<DataWrapper<Cast?>>()
    val cast : MutableLiveData<DataWrapper<Cast?>> get() = _getCast

    val popularMovie = repository.getPopularMovies()
    val upcomingMovie = repository.getUpcomingMovies()

    fun getMovieDetail(id : String)
    {
        viewModelScope.launch {
            try
            {
                _getMovieDetail.value = DataWrapper(repository.getMovieDetail(id), null)
            }
            catch (e : Exception)
            {
                _getMovieDetail.value = DataWrapper(null, e.toString())
            }

        }

    }

    fun getCast(id : String)
    {

        viewModelScope.launch {
            try
            {
                _getCast.value = DataWrapper(repository.getCast(id), null)
            }
            catch (e : Exception)
            {
                _getCast.value = DataWrapper(null, e.message)
            }
        }
    }

    fun insertFavorite(favorite : Favorite) = viewModelScope.launch {

        repository.insertFavorite(favorite)
    }

    fun deleteFavorite(id : String) = viewModelScope.launch {

        repository.deleteFavorite(id)
    }

    fun getFavorite(id : String) : LiveData<Favorite>
    {
        return repository.getFavorite(id)
    }

}