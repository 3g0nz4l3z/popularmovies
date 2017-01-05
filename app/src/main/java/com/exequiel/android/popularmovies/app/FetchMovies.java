package com.exequiel.android.popularmovies.app;

import android.os.AsyncTask;
import android.text.method.MovementMethod;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by exequiel on 16/12/2016.
 */

public class FetchMovies extends AsyncTask<String, Void, Boolean> {
    private String TAG = FetchMovies.this.getClass().getCanonicalName();
    Refresher refresher;
    public FetchMovies(){

    }

    public FetchMovies(Refresher refresher){
        this.refresher = refresher;
    }



    @Override
    protected void onPreExecute() {
        ManagerMovies.getInstance().emptyMovies();
        refresher.start_progress_bar();
    }

    private String getURLMovieTrailer(String movieID){
        return " https://api.themoviedb.org/3/movie/"+movieID+"/videos?api_key="+BuildConfig.THEMOVIEDBAPIKEY;
    }

    private String getURLMoveReview(String movieID){
        return "https://api.themoviedb.org/3/movie/"+movieID+"/reviews?api_key="+BuildConfig.THEMOVIEDBAPIKEY;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        Log.d(TAG, "doInBackground()");
        /**
         * Implementation found https://developer.android.com/reference/java/net/HttpURLConnection.html
         */
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
        url = new URL(params[0]);
        urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            parceJason(in);
        Log.d(TAG, urlConnection.getResponseMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            urlConnection.disconnect();
        }
    return true;
    }

    /**
     * It is not what i wanted but it works
     * @param movie
     */
    private void getTrailers(Movie movie){
        Log.d(TAG, "doInBackground()");
        /**
         * Implementation found https://developer.android.com/reference/java/net/HttpURLConnection.html
         */
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(getURLMovieTrailer(movie.getMovie_id()));
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            String urlString = new Scanner(in).useDelimiter("\\A").next();
            JSONObject jsonTrailer = null;
            try {
                jsonTrailer = new JSONObject(urlString);
                JSONArray jsonATrailers = jsonTrailer.getJSONArray("results");
                for (int i = 0; i < jsonATrailers.length(); i++) {
                    JSONObject jsTrailer = jsonATrailers.getJSONObject(i);
                    String trailerKey = jsTrailer.getString("key");
                    movie.setTrailers(trailerKey);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Log.d(TAG, urlConnection.getResponseMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
    }

    /**
     * It is not what i wanted but it works
     * @param movie
     */
    private void getReviews(Movie movie){
        Log.d(TAG, "doInBackground()");
        /**
         * Implementation found https://developer.android.com/reference/java/net/HttpURLConnection.html
         */
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(getURLMoveReview(movie.getMovie_id()));
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            String urlString = new Scanner(in).useDelimiter("\\A").next();
            JSONObject jsonReview = null;
            try {
                jsonReview = new JSONObject(urlString);
                JSONArray jsonAReviews = jsonReview.getJSONArray("results");
                for (int i = 0; i < jsonAReviews.length(); i++) {
                    JSONObject jsReview = jsonAReviews.getJSONObject(i);
                    String reviewText = jsReview.getString("content");
                    movie.setReview(reviewText);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Log.d(TAG, urlConnection.getResponseMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
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

    private void parceJason(InputStream in){
        String urlString = new Scanner(in).useDelimiter("\\A").next();
        JSONObject jsonMovies = null;
        try {
            jsonMovies = new JSONObject(urlString);
            JSONArray jsonAMovies = jsonMovies.getJSONArray("results");
            for (int i = 0; i < jsonAMovies.length(); i++) {
                JSONObject jsMovie = jsonAMovies.getJSONObject(i);
                String movie_id = jsMovie.getString("id");
                String releaseDate = jsMovie.getString("release_date");
                String userRating = jsMovie.getString("vote_average");
                String synopsis = jsMovie.getString("overview");
                String originalTitle = jsMovie.getString("original_title");
                String coverUrl = jsMovie.getString("poster_path");
                Movie movie = new Movie(movie_id, coverUrl, originalTitle, synopsis, userRating, releaseDate);
                getTrailers(movie);
                getReviews(movie);
                ManagerMovies.getInstance().addMovie(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
