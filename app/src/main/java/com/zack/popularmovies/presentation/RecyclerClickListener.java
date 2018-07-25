package com.zack.popularmovies.presentation;

import com.zack.popularmovies.data.PopularMovie;
import android.view.View;

/**
 * Created by zack on 7/25/18.
 */

public interface RecyclerClickListener {
    void onItemClick(int position, PopularMovie movie, View v);
}
