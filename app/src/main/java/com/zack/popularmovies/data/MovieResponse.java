package com.zack.popularmovies.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;


    @SerializedName("results")
    @Expose

    public List<PopularMovie> movies;

    public List<PopularMovie> getMovies() {
        return movies;
    }


}
