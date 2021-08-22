package com.example.harvesterapidemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.harvesterapidemo.databinding.CharacterListItemBinding
import com.example.harvesterapidemo.model.rickmorty.Results

class CharListAdapter(private val clickListener: ItemClickListener) : RecyclerView.Adapter<CharListAdapter.CharacterViewHolder>() {

    private var data = mutableListOf<Results>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun addData(listItems: List<Results>) {
        val size = this.data.size
        this.data.addAll(listItems)
        val sizeNew = this.data.size
        notifyItemRangeChanged(size, sizeNew)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }

    override fun getItemCount() = data.size

    class CharacterViewHolder(private val binding: CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Results, clickListener: ItemClickListener) {
            binding.character = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}

class ItemClickListener(val clickListener: (id: Int) -> Unit){
    fun onClick(item: Results) = clickListener(item.id)
}