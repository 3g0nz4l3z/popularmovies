package com.exequiel.android.popularmovies.app;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by exequiel on 17/01/2017.
 */

public class FetchFavorites extends AsyncTask<String, Void, Boolean> {
    private String TAG = FetchFavorites.this.getClass().getCanonicalName();
    Refresher refresher;
    Context context;


    public FetchFavorites(MoviesFragment moviesFragment){
        this.context = moviesFragment.getContext();
        this.refresher = moviesFragment;
    }



    @Override
    protected void onPreExecute() {
        ManagerMovies.getInstance().emptyMovies();
        refresher.start_progress_bar();
    }



    @Override
    protected Boolean doInBackground(String... params) {
        Log.d(TAG, "doInBackground()");
        HelperMovieProfile hmp = new HelperMovieProfile(context);
        HelperReview hr = new HelperReview(context);
        HelperTrailer ht = new HelperTrailer(context);
        ManagerMovies.getInstance().setMovies(hmp.getMovieProfile());
        for (Movie movie: ManagerMovies.getInstance().getMovies())
        {
            ArrayList<String> reviews = hr.getReviews(movie.getMovie_id());
            movie.setReviews(reviews);
            ArrayList<String> trailers = ht.getTrailers(movie.getMovie_id());
            movie.setTrailers(trailers);
        }
        return true;
    }

    /**
     * It is not what i wanted but it works
     * @param movie
     */
    private void getTrailers(Movie movie){
        Log.d(TAG, "getTrailers()");

    }

    /**
     * It is not what i wanted but it works
     * @param movie
     */
    private void getReviews(Movie movie){
        Log.d(TAG, "getReviews()");

    }
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        try {
            refresher.refresh();
            refresher.end_progress_bar();
        }catch(Exception e){
            e.printStackTrace();

        }
    }

}
