package com.exequiel.android.popularmovies.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
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
    private ListView listViewReviews;
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
        outState.putStringArrayList("trailers", trailers);
        outState.putStringArrayList("reviews", reviews);
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
        listViewReviews = (ListView) rootView.findViewById(R.id.listViewReviewsTR);

        Bundle movieBundle = this.getArguments();
        Log.d(TAG, "Before bundle");
        if (movieBundle != null) {
            Log.d(TAG, "Bundle is no null");
            coverUrl = movieBundle.getString("coverUrl", null);
            originalTitle = movieBundle.getString("originalTitle", null);
            synopsis = movieBundle.getString("synopsis", null);
            userRating = movieBundle.getString("userRating", null);
            releaseDate = movieBundle.getString("releaseDate", null);
            movieId = movieBundle.getString("movie_id");

            textViewRating.setText(userRating);
            textViewSynopsis.setText(synopsis);
            textViewTitle.setText(originalTitle);
            textViewDate.setText(releaseDate);
            Glide.with(MovieFragment.this).load(coverUrl).error(R.mipmap.ic_launcher).into(imageViewCover);
            if (savedInstanceState == null) {
                reviews = ManagerMovies.getInstance().getReviews(movieId);
                for (String string : reviews)
                {
                    Log.d(TAG, string);
                }
                trailers = ManagerMovies.getInstance().getTrailers(movieId);
            } else if (savedInstanceState.getStringArrayList("trailers") != null && savedInstanceState.getStringArrayList("reviews") != null  ) {
                reviews = savedInstanceState.getStringArrayList("reviews");
                trailers = savedInstanceState.getStringArrayList("trailers");
            }


            adapterReviews = new AdapterReviews(MovieFragment.this, reviews);
            adapterTrailers = new AdapterTrailers(MovieFragment.this, trailers);
            listViewReviews.setAdapter(adapterReviews);
            new Utility().setListViewHeightBasedOnChildren(listViewReviews);
            listViewTrailers.setAdapter(adapterTrailers);
            new Utility().setListViewHeightBasedOnChildren(listViewTrailers);

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
                listViewReviews.invalidateViews();
                listViewReviews.setAdapter(adapterReviews);
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
