package com.example.moviesdatabase.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdatabase.databinding.ItemLayoutBinding
import com.example.moviesdatabase.model.Movie
import com.example.moviesdatabase.model.MovieList
import com.squareup.picasso.Picasso

class MovieAdapter(private val listener: MovieItemListener, private val users: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    interface MovieItemListener {
        fun onClickedMovie(movieId: Int)
    }

    class ViewHolder(
        private val itemLayoutBinding: ItemLayoutBinding,
        private val listener: MovieItemListener) : RecyclerView.ViewHolder(itemLayoutBinding.root),
        View.OnClickListener {
        private lateinit var movie: Movie

        init {
            itemLayoutBinding.root.setOnClickListener(this)
        }

        fun bindItems(item: Movie) {
            this.movie = item
            var testString = "https://image.tmdb.org/t/p/w500" + movie.poster_path
            Picasso.get().load(testString)
                .placeholder(com.example.moviesdatabase.R.color.black)
                .into(itemLayoutBinding.moviePoster)
        }

        override fun onClick(v: View?) {
            Log.d("benjaminTest", "benjamin")
            listener.onClickedMovie(movie.id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemLayoutBinding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("benjamin", "benjaminTost")
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindItems(users[position])

    override fun getItemCount(): Int = users.size

    fun addUsers(users: MovieList) {
        Log.d("benjamin", "benjamin" + users.results)
        this.users.apply {
            clear()
            addAll(users.results)
            notifyDataSetChanged()
        }
    }
}