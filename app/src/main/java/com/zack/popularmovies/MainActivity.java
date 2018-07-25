package com.zack.popularmovies;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zack.popularmovies.data.MovieResponse;
import com.zack.popularmovies.data.PopularMovie;
import com.zack.popularmovies.databinding.ActivityMainBinding;
import com.zack.popularmovies.presentation.MoviesAdapter;
import com.zack.popularmovies.presentation.MoviesPresenter;
import com.zack.popularmovies.presentation.MoviesView;
import com.zack.popularmovies.presentation.RecyclerClickListener;
import com.zack.popularmovies.presentation.injection.DaggerMoviesPresenterComponent;
import com.zack.popularmovies.presentation.injection.PopularMoviesModule;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MoviesView,RecyclerClickListener {
    @Inject
    MoviesPresenter moviesPresenter;

    RecyclerView recyclerView;
    MoviesAdapter moviesAdapter;
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        recyclerView = binding.movieList;
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ApplicationComponent applicationComponent = ((PopularMoviesApplication) getApplication())
                .component();
        DaggerMoviesPresenterComponent.builder()
                .applicationComponent(applicationComponent)
                .popularMoviesModule(new PopularMoviesModule())
                .build()
                .inject(this);
        moviesPresenter.attachView(this);
        moviesPresenter.getMovies();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        moviesPresenter.onDestroy();
    }

    @Override
    public void displayMovies(MovieResponse movies) {

        moviesAdapter = new MoviesAdapter(movies.getMovies(), this,this);
        recyclerView.setAdapter(moviesAdapter);

    }

    @Override
    public void onItemClick(int position, PopularMovie movie, View v) {
        Intent intent = new Intent(this, MovieDetail.class);
        intent.putExtra("movie",movie);
        startActivity(intent);

    }
}
