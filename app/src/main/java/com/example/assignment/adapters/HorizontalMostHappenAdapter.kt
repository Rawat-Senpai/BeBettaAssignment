package com.example.assignment.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.models.MostlyHappenModel


class HorizontalMostHappenAdapter(private val contactList: ArrayList<MostlyHappenModel>,

) :
    RecyclerView.Adapter<HorizontalMostHappenAdapter.ItemViewHolder>() {


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.imageView);
        val name: TextView = itemView.findViewById(R.id.tvName);
        val imageVerification: ImageView = itemView.findViewById(R.id.imgVerified);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_mostly_happen, parent, false)
        return ItemViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = contactList[position]
        holder.imageView.setImageResource(item.imageId)
        holder.name.text = item.name

        // for now I have mark some of the position for verified account statically visible  for some position
        val absolutePosition = holder.layoutPosition;
        if(absolutePosition%4==0)
        {
            holder.imageVerification.visibility=View.VISIBLE
        }else{
            holder.imageVerification.visibility=View.GONE
        }



    }

    override fun getItemCount(): Int {
        return contactList.size
    }


}
