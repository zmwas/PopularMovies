package com.zack.popularmovies.presentation;

import com.zack.popularmovies.data.ApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zack on 7/25/18.
 */

public class MoviesPresenter implements Presenter<MoviesView>{
    ApiService apiService;
    MoviesView view;
    Disposable disposable;

    public MoviesPresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getMovies() {
        disposable = apiService
                .getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::displayMovies);
    }


    @Override
    public void attachView(MoviesView view) {
        this.view = view;
    }

    public void onDestroy(){
        if(!disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
