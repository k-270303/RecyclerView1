package com.example.painting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.painting.Datanetwork.DatasetItem
import com.example.painting.databinding.ListViewBinding

class Adapter: ListAdapter <DatasetItem,Adapter.ItemViewHolder>(DiffUtil()) {
    class ItemViewHolder(private val binding:ListViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind (item: DatasetItem){
            binding.apply {
                heading.text = item.user.name
                author.text = item.user.location
                description.text = item.user.bio

                Glide.with(image.context)
                    .load(item.urls.regular)
                    .centerCrop()
                    .into(image)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ItemViewHolder {
        val binding = ListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Adapter.ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

     class DiffUtil: androidx.recyclerview.widget.DiffUtil.ItemCallback<DatasetItem> (){
         override fun areItemsTheSame(oldItem: DatasetItem, newItem: DatasetItem): Boolean {
             return oldItem.id==newItem.id
         }

         override fun areContentsTheSame(oldItem: DatasetItem, newItem: DatasetItem): Boolean {
             return oldItem==newItem
         }
     }


}
