package com.exequiel.android.popularmovies.app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by exequiel on 12/12/2016.
 * This class manage everything related to the view of the movie
 */

public class MovieFragment extends Fragment {
    private TextView textViewTitle;
    private TextView textViewRating;
    private TextView textViewSynopsis;
    private ImageView imageViewCover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie, container, false);

        return rootView;
    }

}
