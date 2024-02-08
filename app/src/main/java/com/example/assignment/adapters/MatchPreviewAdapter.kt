package com.example.assignment.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.assignment.R
import com.example.assignment.models.SportDataClass





class MatchPreviewAdapter(
    private val itemList: ArrayList<Int>) :
    RecyclerView.Adapter<MatchPreviewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageCardView = itemView.findViewById<ImageView>(R.id.imageCardView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView=
            LayoutInflater.from(parent.context).inflate(R.layout.match_preview_layout, parent, false)

        val layoutParams = itemView.layoutParams as RecyclerView.LayoutParams
        layoutParams.width = (parent.width * 0.5).toInt() // Adjust the percentage as needed
        itemView.layoutParams = layoutParams

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.imageCardView.setImageResource(item)




    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
