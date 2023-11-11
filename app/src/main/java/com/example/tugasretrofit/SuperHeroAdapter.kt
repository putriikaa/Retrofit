package com.example.tugasretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tugasretrofit.model.SuperHeroData


class SuperHeroAdapter(var con: Context, var list: List<SuperHeroData>) : RecyclerView.Adapter<SuperHeroAdapter.SuperHeroViewHolder>() {

    inner class SuperHeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageHero: ImageView = itemView.findViewById(R.id.imageHero)
        val idHero: TextView = itemView.findViewById(R.id.idHero)
        val titleHero: TextView = itemView.findViewById(R.id.titleHero)

        fun bind(superHero: SuperHeroData) {
            Glide.with(itemView)
                .load(superHero.image)
                .into(imageHero)
            idHero.text = superHero.id.toString()
            titleHero.text = superHero.title
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_hero, parent, false)
        return SuperHeroViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val currentItem = list[position]
        holder.bind(currentItem)
    }
}
