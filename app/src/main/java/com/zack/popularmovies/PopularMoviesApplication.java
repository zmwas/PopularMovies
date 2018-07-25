package com.zack.popularmovies;

import android.app.Application;

import com.zack.popularmovies.data.ApiModule;

import dagger.android.DaggerApplication;

/**
 * Created by zack on 7/25/18.
 */

public class PopularMoviesApplication extends Application {
    ApplicationComponent appComponent;

    @Override
    public void onCreate() {

        super.onCreate();
        appComponent = DaggerApplicationComponent.builder()
                .apiModule(new ApiModule(this))
                .app(this).build();
    }

    public ApplicationComponent component() {
        return appComponent;
    }
}
