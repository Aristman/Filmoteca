package ru.marslab.filmoteca.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.marslab.filmoteca.databinding.RvItemShortMovieBinding
import ru.marslab.filmoteca.ui.model.MovieShortUi

private val diffCallback = object : DiffUtil.ItemCallback<MovieShortUi>() {
    override fun areItemsTheSame(oldItem: MovieShortUi, newItem: MovieShortUi): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieShortUi, newItem: MovieShortUi): Boolean =
        oldItem == newItem

}

class MovieHorizonPagingAdapter(private val callback: (item: MovieShortUi) -> Unit) :
    PagingDataAdapter<MovieShortUi, MovieHorizonViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: MovieHorizonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHorizonViewHolder {
        val binding =
            RvItemShortMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHorizonViewHolder(binding, callback)
    }
}