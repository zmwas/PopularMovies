package com.zack.popularmovies;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.zack.popularmovies.data.PopularMovie;
import com.zack.popularmovies.databinding.MovieDetailBinding;

/**
 * Created by zack on 7/25/18.
 */

public class MovieDetail extends AppCompatActivity {
    MovieDetailBinding binding;
    String title, overview, rating, imagePath, imageBase, imageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.movie_detail);
        PopularMovie movie = (PopularMovie) getIntent().getSerializableExtra("movie");
        imageBase = "http://image.tmdb.org/t/p/w342//";
        title = movie.getTitle();
        overview = movie.getOverview();
        rating = String.valueOf(movie.getVoteAverage());
        imagePath = movie.getPosterPath();
        imageUrl = imageBase + imagePath;
        getSupportActionBar().setTitle(title);
        Picasso.get().load(imageUrl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .fit()
                .into(binding.moviePoster);
        binding.rating.setText(rating);
        binding.movieOverview.setText(overview);
        binding.share.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra("title", title);
            intent.putExtra("overview", overview);
            startActivity(Intent.createChooser(intent, "share movie"));
        });
    }
}
