package com.mohamedhashim.healthchain

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView



/**
 * Created by Mohamed Hashim on 6/27/2018.
 */
class MoviesAdapter(private val moviesList: List<Doctor>) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var year: TextView
        var genre: TextView

        init {
            genre = view.findViewById<TextView>(R.id.person_name)
            year = view.findViewById<TextView>(R.id.person_age)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]

        holder.genre.setText(movie.genre)
        holder.year.setText(movie.year)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}