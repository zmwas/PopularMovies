package com.zack.popularmovies.presentation.injection;

import com.zack.popularmovies.data.ApiService;
import com.zack.popularmovies.presentation.MoviesPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zack on 7/25/18.
 */
@Module()
public class PopularMoviesModule {

    @Provides
    @ActivityScoped
    MoviesPresenter providesMoviePresenter(ApiService apiService){
        return new MoviesPresenter(apiService);
    }
}
