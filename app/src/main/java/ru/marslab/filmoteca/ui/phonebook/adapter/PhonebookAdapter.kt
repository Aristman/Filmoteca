package ru.marslab.filmoteca.ui.phonebook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.marslab.filmoteca.databinding.RvItemPhonebookBinding

private val diffCallback = object : DiffUtil.ItemCallback<PhonebookItem>() {
    override fun areItemsTheSame(oldItem: PhonebookItem, newItem: PhonebookItem): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: PhonebookItem, newItem: PhonebookItem): Boolean =
        oldItem == newItem

}

class PhonebookAdapter(private val callback: (item: PhonebookItem) -> Unit) :
    ListAdapter<PhonebookItem, PhonebookAdapter.PhonebookViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhonebookViewHolder {
        val binding =
            RvItemPhonebookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhonebookViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: PhonebookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PhonebookViewHolder(
        private val binding: RvItemPhonebookBinding,
        private val callback: (item: PhonebookItem) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PhonebookItem) {
            binding.run {
                phoneName.text = item.name
                phoneNumber.text = item.number
                root.setOnClickListener {
                    callback(item)
                }
            }
        }
    }
}
