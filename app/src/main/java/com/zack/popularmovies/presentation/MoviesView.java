package com.zack.popularmovies.presentation;

import com.zack.popularmovies.data.MovieResponse;


/**
 * Created by zack on 7/25/18.
 */

public interface MoviesView extends View {
    void displayMovies(MovieResponse movies);
}
