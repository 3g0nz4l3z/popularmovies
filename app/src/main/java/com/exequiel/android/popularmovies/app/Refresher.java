package com.exequiel.android.popularmovies.app;

/**
 * Created by egonzalez on 12/19/16.
 */
public interface Refresher {

    void refresh();
    void start_progress_bar();
    void end_progress_bar();
}
