package com.zack.popularmovies;

import android.app.Application;

import com.zack.popularmovies.data.ApiModule;
import com.zack.popularmovies.data.ApiService;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by zack on 7/25/18.
 */
@Singleton @Component(modules = {ApiModule.class})
public interface ApplicationComponent {

    ApiService apiService();

    @Component.Builder
    interface Builder {
        ApplicationComponent build();

        Builder apiModule(ApiModule apiModule);
        @BindsInstance
        Builder app(PopularMoviesApplication application);

    }
}
