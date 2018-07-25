package com.zack.popularmovies.data;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by zack on 7/25/18.
 */

public interface ApiService {
    @GET("/3/discover/movie/")
    Observable<MovieResponse> getPopularMovies();
}
