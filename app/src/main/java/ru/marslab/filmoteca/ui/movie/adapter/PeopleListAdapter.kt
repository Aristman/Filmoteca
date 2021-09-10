package ru.marslab.filmoteca.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.marslab.filmoteca.databinding.RvItemPeopleListBinding
import ru.marslab.filmoteca.ui.model.PeopleShortUi

private val diffCallback = object : DiffUtil.ItemCallback<PeopleShortUi>() {
    override fun areItemsTheSame(oldItem: PeopleShortUi, newItem: PeopleShortUi): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PeopleShortUi, newItem: PeopleShortUi): Boolean =
        oldItem == newItem
}

class PeopleListAdapter(
    private val callback: (item: PeopleShortUi) -> Unit
) : ListAdapter<PeopleShortUi, PeopleViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val binding =
            RvItemPeopleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeopleViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class PeopleViewHolder(
    private val binding: RvItemPeopleListBinding,
    private val callback: (item: PeopleShortUi) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PeopleShortUi) {
        binding.run {
            itemName.text = item.name
            itemPhoto.load(item.photo)
            root.setOnClickListener { callback(item) }
        }
    }
}