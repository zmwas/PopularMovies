package com.zack.popularmovies.data;

import android.app.Application;
import android.content.Context;

import com.zack.popularmovies.BuildConfig;
import com.zack.popularmovies.PopularMoviesApplication;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zack on 7/25/18.
 */
@Module
public class ApiModule {
    Application application;

    public ApiModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return  application;
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(Application application) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor authInterceptor = chain -> {

            HttpUrl url = chain.request().url().newBuilder()
                    .addQueryParameter("api_key", BuildConfig.apikey)
                    .build();
            Request newRequest = chain.request().newBuilder().url(url).build();
            return chain.proceed(newRequest);
        };
        int cacheSize = 50 * 1024 * 1024;

        return new OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .cache(new Cache(application.getCacheDir(), cacheSize))
                .build();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiService apiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
