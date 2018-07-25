package com.zack.popularmovies.presentation;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.zack.popularmovies.R;
import com.zack.popularmovies.data.PopularMovie;
import com.zack.popularmovies.databinding.MovieItemBinding;

import java.util.List;

/**
 * Created by zack on 7/25/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieItemView> {
    List<PopularMovie> movies;
    Context context;
    RecyclerClickListener recyclerClickListener;

    public MoviesAdapter(List<PopularMovie> movies, Context context, RecyclerClickListener recyclerClickListener) {
        this.movies = movies;
        this.context = context;
        this.recyclerClickListener = recyclerClickListener;
    }

    @NonNull
    @Override
    public MovieItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MovieItemBinding binding = MovieItemBinding.inflate(inflater, parent, false);
        return new MovieItemView(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemView holder, int position) {
        PopularMovie movie = movies.get(position);
        holder.bind(movie);
        holder.binding.getRoot()
                .setOnClickListener(view ->
                        recyclerClickListener.onItemClick(position, movie, view));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieItemView extends RecyclerView.ViewHolder {
        MovieItemBinding binding;

        public MovieItemView(MovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PopularMovie movie) {
            String imageBase = "http://image.tmdb.org/t/p/w185//";
            String imagePath = imageBase + movie.getPosterPath();
            binding.titleText.setText(movie.getTitle());
            Picasso.get().load(imagePath)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .fit()
                    .into(binding.movieImage);
        }
    }
}
