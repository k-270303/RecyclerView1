package com.example.painting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import org.w3c.dom.Text

class Adapter(private val context: Context, val menu: List<Abc>) :
    RecyclerView.Adapter<Adapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_view, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = menu[position]
        Glide.with(context).load(item.image).into(holder.image)
        holder.heading.text = item.heading
        holder.author.text = item.author
        holder.description.text = item.description
    }

    override fun getItemCount(): Int {
        return menu.size
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
        var heading: TextView = itemView.findViewById(R.id.heading)
        var author: TextView = itemView.findViewById(R.id.author)
        var description: TextView = itemView.findViewById(R.id.description)


    }


}