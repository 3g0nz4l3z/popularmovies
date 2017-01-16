package com.exequiel.android.popularmovies.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exequiel on 12/12/2016.
 * This class manage everything related to the view of the movie
 */

public class MovieFragment extends Fragment implements Refresher{
    private String TAG = MovieFragment.class.getCanonicalName();
    private TextView textViewTitle;
    private TextView textViewRating;
    private TextView textViewSynopsis;
    private TextView textViewDate;
    private ImageView imageViewCover;
    private AdapterReviews adapterReviews;
    private AdapterTrailers adapterTrailers;
    private ListView listViewTrailers;
    private ListView lsitViewReviews;
    private String coverUrl;
    private String originalTitle;
    private String synopsis;
    private String userRating;
    private String releaseDate;
    private String movieId;
    private ArrayList<String> reviews;
    private ArrayList<String> trailers;
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelableArrayList("Trailers", trailers);
        //outState.putParcelableArrayList("Reviews", reviews);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_trailer_reviews, container, false);
        textViewTitle = (TextView) rootView.findViewById(R.id.textViewTitleTR);
        textViewRating = (TextView) rootView.findViewById(R.id.textViewRateTR);
        textViewSynopsis = (TextView) rootView.findViewById(R.id.textViewSynopsisTR);
        imageViewCover = (ImageView) rootView.findViewById(R.id.imageViewCoverTR);
        textViewDate = (TextView) rootView.findViewById(R.id.textViewDateTR);
        listViewTrailers = (ListView) rootView.findViewById(R.id.listViewTrailersTR);
        lsitViewReviews = (ListView) rootView.findViewById(R.id.listViewTrailersTR);

        Bundle movieBundle = this.getArguments();
        Log.d(TAG, "Before bundle");
        if (movieBundle != null) {
            Log.d(TAG, "Bundle is no null");
            coverUrl = movieBundle.getString("coverUrl", null);
            originalTitle = movieBundle.getString("originalTitle", null);
            synopsis = movieBundle.getString("synopsis", null);
            userRating = movieBundle.getString("userRating", null);
            releaseDate = movieBundle.getString("releaseDate", null);
            reviews = movieBundle.getStringArrayList("reviews");
            trailers = movieBundle.getStringArrayList("trailers");
            movieId = movieBundle.getString("movie_id");

            textViewRating.setText(userRating);
            textViewSynopsis.setText(synopsis);
            textViewTitle.setText(originalTitle);
            textViewDate.setText(releaseDate);
            Glide.with(MovieFragment.this).load(coverUrl).error(R.mipmap.ic_launcher).into(imageViewCover);
            if (savedInstanceState == null) {
                ManagerMovies.getInstance().fetch_by_top_rated(MovieFragment.this);
                reviews = ManagerMovies.getInstance().getReviews(movieId);
                trailers = ManagerMovies.getInstance().getTrailers(movieId);
            } else if (savedInstanceState.getParcelableArrayList("trailers") != null && savedInstanceState.getParcelableArrayList("reviews") != null  ) {
                // reviews = savedInstanceState.getParcelableArray("trailers");
                // trailers = savedInstanceState.getParcelableArray("reviews");
            }


            adapterReviews = new AdapterReviews(MovieFragment.this, reviews);
            adapterTrailers = new AdapterTrailers(MovieFragment.this, trailers);
            //lsitViewReviews.setAdapter(adapterReviews);
            //listViewTrailers.setAdapter(adapterTrailers);

        }
        return rootView;
    }


    @Override
    public void refresh() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "refresh()");
                adapterReviews.notifyDataSetChanged();
                lsitViewReviews.invalidateViews();
                lsitViewReviews.setAdapter(adapterReviews);
                adapterTrailers.notifyDataSetChanged();
                listViewTrailers.invalidateViews();
                listViewTrailers.setAdapter(adapterTrailers);
            }
        });
    }

    @Override
    public void start_progress_bar() {

    }

    @Override
    public void end_progress_bar() {

    }
}
