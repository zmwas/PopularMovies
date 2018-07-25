package com.zack.popularmovies.presentation.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by zack on 7/25/18.
 */
@Scope @Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped {
}
