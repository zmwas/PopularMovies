package com.zack.popularmovies.presentation;

/**
 * Created by zack on 7/25/18.
 */

public interface Presenter<T extends View> {
    void attachView(T view);
}
