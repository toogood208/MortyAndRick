package com.example.mortyandrick.helpers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mortyandrick.R
import com.example.mortyandrick.models.MortyRick
import com.squareup.picasso.Picasso

class MortyAdapter(private val mortyList:List<MortyRick.Result>):
        RecyclerView.Adapter<MortyAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MortyAdapter.ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.list_items,parent,false)
        return MortyAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return mortyList.size
    }

    override fun onBindViewHolder(holder: MortyAdapter.ViewHolder, position: Int) {
        Log.d("Response", "List Count :${mortyList.size} ")


        return holder.bind(mortyList[position])

    }

    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {


        var nameView = itemView.findViewById<TextView>(R.id.name)
        var statusView = itemView.findViewById<TextView>(R.id.status)
        var speciesView = itemView.findViewById<TextView>(R.id.species)
        var imageView = itemView.findViewById<ImageView>(R.id.image)
        fun bind(morty: MortyRick.Result) {

            nameView.text = morty.name
            statusView.text = morty.status
            speciesView.text = morty.species
            Picasso.get().load(morty.image).into(imageView)
            val name ="Cases :${morty.name}"

        }


    }
}

