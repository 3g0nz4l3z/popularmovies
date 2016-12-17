package com.exequiel.android.popularmovies.app;

import android.os.AsyncTask;
import android.text.method.MovementMethod;

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
    @Override
    protected Boolean doInBackground(String... params) {
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

    private void parceJason(InputStream in){
        String urlString = new Scanner(in).useDelimiter("\\A").next();
        JSONObject jsonMovies = null;
        try {
            jsonMovies = new JSONObject(urlString);
            JSONArray jsonAMovies = jsonMovies.getJSONArray("results");
            ManagerMovies.getInstance().emptyMovies();
            for (int i = 0; i < jsonAMovies.length(); i++) {
                JSONObject jsMovie = jsonAMovies.getJSONObject(i);

                String releaseDate = jsMovie.getString("posterPath");
                String userRating = jsMovie.getString("vote_average");
                String synopsis = jsMovie.getString("overview");
                String originalTitle = jsMovie.getString("original_title");
                String coverUrl = jsMovie.getString("poster_path");
                Movie movie = new Movie(coverUrl, originalTitle, synopsis, userRating, releaseDate);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
