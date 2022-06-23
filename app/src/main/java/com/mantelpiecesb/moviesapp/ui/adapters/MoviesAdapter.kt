package com.mantelpiecesb.moviesapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mantelpiecesb.moviesapp.databinding.ItemArticleBinding
import com.mantelpiecesb.moviesapp.models.Movie

class MoviesAdapter: PagingDataAdapter<Movie, MoviesAdapter.MoviesViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

        inner class MoviesViewHolder(private val binding: ItemArticleBinding): RecyclerView.ViewHolder(binding.root) {

            fun bind(movie: Movie) {
                binding.apply {
                    Glide.with(itemView).load(movie.multimedia.src).into(binding.movieImg)
                    binding.movieImg.clipToOutline = true
                    binding.movieDescription.text = movie.summary_short
                    binding.movieTitle.text = movie.display_title
                }
            }

    }

}

private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.headline == newItem.headline
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return  oldItem == newItem
    }
}

