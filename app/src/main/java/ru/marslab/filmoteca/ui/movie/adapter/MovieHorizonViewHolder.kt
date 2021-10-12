package ru.marslab.filmoteca.ui.movie.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.marslab.filmoteca.databinding.RvItemShortMovieBinding
import ru.marslab.filmoteca.ui.model.MovieShortUi

open class MovieHorizonViewHolder(
    private val binding: RvItemShortMovieBinding,
    private val callback: (item: MovieShortUi) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieShortUi?) {
        if (item != null) {
            item.poster?.let {
                binding.moviePoster.load(it)
            }
            with(binding) {
                movieTitle.text = item.title
                movieReleaseDate.text = item.releaseDate
                movieRating.text = item.userRating.toString()
            }
            binding.root.setOnClickListener {
                callback(item)
            }
        } else {
            // TODO ("Обработка отсутствия элемента списка")
        }
    }
}