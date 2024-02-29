package com.notsatria.tourismapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.notsatria.tourismapp.models.Place

class ListPlaceAdapter(private val placeList:ArrayList<Place>) : RecyclerView.Adapter<ListPlaceAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Place)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPlacePhoto: ImageView = itemView.findViewById(R.id.iv_place_photo)
        val tvPlaceName: TextView = itemView.findViewById(R.id.tv_place_name)
        val tvPlaceCountry: TextView = itemView.findViewById(R.id.tv_place_country)
        val tvPlaceRating: TextView = itemView.findViewById(R.id.tv_detail_place_rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.place_list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = placeList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (placeImage, placeName, placeCity, placeRating) = placeList[position]
        holder.ivPlacePhoto.setImageResource(placeImage)
        holder.tvPlaceName.text = placeName
        holder.tvPlaceCountry.text = placeCity
        holder.tvPlaceRating.text = placeRating

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(placeList[holder.adapterPosition]) }
    }
}