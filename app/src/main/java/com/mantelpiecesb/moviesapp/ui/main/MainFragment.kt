package com.mantelpiecesb.moviesapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.mantelpiecesb.moviesapp.databinding.FragmentMainBinding
import com.mantelpiecesb.moviesapp.ui.adapters.MoviesAdapter

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()
    lateinit var mMoviesAdapter: MoviesAdapter
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        searchMovies()
    }

    private fun initAdapter() {
        mMoviesAdapter = MoviesAdapter()
        news_adapter.apply {
            adapter = mMoviesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun searchMovies() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            context?.let {
                viewModel.searchMovies(it)
                viewModel.moviesLiveData.observe(viewLifecycleOwner) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        mMoviesAdapter.submitData(it)
                    }

                }
            }
        }
    }
}