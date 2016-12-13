package com.exequiel.android.popularmovies.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by exequiel on 12/12/2016.
 * This class manages everything related to the view of the movies when the app start
 */

public class MoviesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movies, container, false);
    }
}
