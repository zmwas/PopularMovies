package com.zack.popularmovies;

import android.app.Application;

import dagger.android.DaggerApplication;

/**
 * Created by zack on 7/25/18.
 */

public class PopularMoviesApplication extends Application {
    ApplicationComponent appComponent;

    @Override
    public void onCreate() {

        super.onCreate();
        appComponent =  DaggerApplicationComponent.builder().app(this).build();
    }

    public ApplicationComponent component() {
        return appComponent;
    }
}
