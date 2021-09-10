package ru.marslab.filmoteca.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.marslab.filmoteca.databinding.RvItemShortMovieBinding
import ru.marslab.filmoteca.ui.model.MovieShortUi

private val diffCallback = object : DiffUtil.ItemCallback<MovieShortUi>() {
    override fun areItemsTheSame(oldItem: MovieShortUi, newItem: MovieShortUi): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieShortUi, newItem: MovieShortUi): Boolean =
        oldItem == newItem
}

class HorizonListAdapter(private val callback: (item: MovieShortUi) -> Unit) :
    ListAdapter<MovieShortUi, HorizonViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizonViewHolder {
        val binding =
            RvItemShortMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HorizonViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: HorizonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class HorizonViewHolder(
    private val binding: RvItemShortMovieBinding,
    private val callback: (item: MovieShortUi) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MovieShortUi) {
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
    }
}
