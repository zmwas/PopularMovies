package com.zack.popularmovies.presentation.injection;

import com.zack.popularmovies.ApplicationComponent;
import com.zack.popularmovies.MainActivity;

import dagger.Component;

/**
 * Created by zack on 7/25/18.
 */
@ActivityScoped @Component(modules = {PopularMoviesModule.class} ,dependencies = {ApplicationComponent.class})
public interface MoviesPresenterComponent {
    void inject(MainActivity activity);
}
