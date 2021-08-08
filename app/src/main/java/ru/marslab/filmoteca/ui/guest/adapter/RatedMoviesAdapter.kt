package ru.marslab.filmoteca.ui.guest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.marslab.filmoteca.databinding.RvItemRatedMovieBinding
import ru.marslab.filmoteca.ui.guest.model.RatedMoviesUi

val diffCallback = object : DiffUtil.ItemCallback<RatedMoviesUi>() {
    override fun areItemsTheSame(oldItem: RatedMoviesUi, newItem: RatedMoviesUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RatedMoviesUi, newItem: RatedMoviesUi): Boolean {
        return oldItem == newItem
    }

}

class RatedMoviesAdapter(private val callback: (item: RatedMoviesUi) -> Unit) :
    ListAdapter<RatedMoviesUi, RatedMovesViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatedMovesViewHolder {
        val binding =
            RvItemRatedMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RatedMovesViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: RatedMovesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class RatedMovesViewHolder(
    private val binding: RvItemRatedMovieBinding,
    private val callback: (item: RatedMoviesUi) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: RatedMoviesUi) {
        item.poster?.let {
            // TODO ("Реализовать загрузку постера по ссылке")
        }
        with(binding) {
            movieTitle.text = item.title
            movieReleaseDate.text = item.releaseDate
            movieRating.text = item.rating
        }
        binding.root.setOnClickListener {
            callback(item)
        }
    }
}
