package com.exequiel.android.popularmovies.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by exequiel on 12/12/2016.
 * This class manage everything related to the view of the movie
 */

public class MovieFragment extends Fragment {
    private String TAG = MovieFragment.class.getCanonicalName();
    private TextView textViewTitle;
    private TextView textViewRating;
    private TextView textViewSynopsis;
    private ImageView imageViewCover;
    private String coverUrl;
    private String originalTitle;
    private String synopsis;
    private String userRating;
    private String releaseDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie, container, false);
        textViewTitle = (TextView) rootView.findViewById(R.id.textViewTitle);
        textViewRating = (TextView) rootView.findViewById(R.id.textViewRate);
        textViewSynopsis = (TextView) rootView.findViewById(R.id.textViewSynopsis);
        imageViewCover = (ImageView) rootView.findViewById(R.id.imageViewCover);

        Bundle movieBundle = this.getArguments();
        Log.d(TAG, "Before bundle");
        if (movieBundle != null) {
            Log.d(TAG, "Bundle is no null");
            coverUrl = movieBundle.getString("coverUrl", null);
            originalTitle = movieBundle.getString("originalTitle", null);
            synopsis = movieBundle.getString("synopsis", null);
            userRating = movieBundle.getString("userRating", null);
            releaseDate = movieBundle.getString("releaseDate", null);
            textViewRating.setText(userRating);
            textViewSynopsis.setText(synopsis);
            textViewTitle.setText(originalTitle+ " (" +releaseDate+")");
            Glide.with(MovieFragment.this).load(coverUrl).error(R.mipmap.ic_launcher).into(imageViewCover);
        }
        return rootView;
    }


}
