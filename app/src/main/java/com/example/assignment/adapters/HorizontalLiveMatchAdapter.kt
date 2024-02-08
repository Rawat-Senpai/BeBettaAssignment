package com.example.assignment.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.assignment.R
import com.example.assignment.models.SportDataClass


class HorizontalLiveMatchAdapter(
    private val itemList: ArrayList<SportDataClass.DataListItem>) :
    RecyclerView.Adapter<HorizontalLiveMatchAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivTeam1 = itemView.findViewById<ImageView>(R.id.ivTeam1)
        val tvTeam1Name = itemView.findViewById<TextView>(R.id.team1Name)
        val ivTeam2 = itemView.findViewById<ImageView>(R.id.ivTeam2)
        val tvTeam2Name = itemView.findViewById<TextView>(R.id.team2Name)
        val tvTeam1Score = itemView.findViewById<TextView>(R.id.team1Score)
        val tvTeam2Score = itemView.findViewById<TextView>(R.id.team2Score)
        val tvTimeLeft=  itemView.findViewById<TextView>( R.id.timeLeft)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView=
            LayoutInflater.from(parent.context).inflate(R.layout.layout_live_match, parent, false)

        val layoutParams = itemView.layoutParams as RecyclerView.LayoutParams
        layoutParams.width = (parent.width * 0.5).toInt() // Adjust the percentage as needed
        itemView.layoutParams = layoutParams

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        Log.d("checkingItemView",item.toString())
        try {

            val context = holder.itemView.context // Assuming you're inside onBindViewHolder in your adapter
            val imageResId1 = context.resources.getIdentifier(item.team1Logo, "drawable", context.packageName)
            val imageResId2 = context.resources.getIdentifier(item.team2Logo, "drawable", context.packageName)

            val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.fc_team)

            holder.ivTeam1.setImageResource(imageResId1)
            holder.ivTeam2.setImageResource(imageResId2)

//            Glide.with(holder.ivTeam1).load(item.team1Logo).apply(requestOptions).into(holder.ivTeam1)

        } catch (e: Exception) {
            Log.d("crash",e.toString())
        }

        try {
            val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.fc_team)

            Glide.with(holder.ivTeam2).load(item.team2Logo).apply(requestOptions).into(holder.ivTeam2)

        } catch (e: Exception) {
            Log.d("crash",e.toString())
        }

        holder.tvTeam1Name.text= item.team1
        holder.tvTeam1Score.text= item.teamOneScore

        holder.tvTeam2Name.text = item.team2
        holder.tvTeam2Score.text= item.teamTwoScore


        if(item.timeRemaining!=0)
        {       holder.tvTimeLeft.text = "${item.timeRemaining} min"
            if(item.timeRemaining!! <50){
                holder.tvTimeLeft.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.teal_200))
            }else{
                holder.tvTimeLeft.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.teal_700))
            }


        }






    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
