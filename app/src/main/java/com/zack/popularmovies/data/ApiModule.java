package com.zack.popularmovies.data;

import com.zack.popularmovies.BuildConfig;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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
    @Provides @Singleton
    OkHttpClient providesOkHttpClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor authInterceptor = chain -> {

            HttpUrl url = chain.request().url().newBuilder()
                    .addQueryParameter("api_key", BuildConfig.apikey)
                    .build();
            Request newRequest = chain.request().newBuilder().url(url).build();
            return chain.proceed(newRequest);
        };

        return new OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
    }
    @Provides @Singleton
    Retrofit providesRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    @Provides @Singleton
    ApiService apiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
