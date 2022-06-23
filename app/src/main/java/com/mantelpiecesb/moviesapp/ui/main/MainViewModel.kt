package com.mantelpiecesb.moviesapp.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.mantelpiecesb.moviesapp.data.api.MoviesRepository
import com.mantelpiecesb.moviesapp.models.Movie
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MoviesRepository): ViewModel() {

    val moviesLiveData: MutableLiveData<PagingData<Movie>> = MutableLiveData()

    fun searchMovies(context: Context) {
        viewModelScope.launch {
            val newResult = repository.getSearchResultStream(context).cachedIn(viewModelScope)
            newResult.collectLatest {
                moviesLiveData.postValue(it)
            }
        }
    }

}