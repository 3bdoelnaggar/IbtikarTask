package com.elnaggar.ibtikartask.features.personDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.elnaggar.ibtikartask.R

class ImagesAdapter(private val items:List<String>):RecyclerView.Adapter<ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_list_item,parent,false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ImageViewHolder(view:View):RecyclerView.ViewHolder(view) {
fun bind(profile:String){
val imageView=itemView as ImageView
    imageView.load(profile)

}
}
