package com.exequiel.android.popularmovies.app;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by exequiel on 12/12/2016.
 */

public class AdapterTrailers extends ArrayAdapter<Movie> {
    private String TAG = AdapterTrailers.this.getClass().getCanonicalName();
    private Fragment fragment;
    private List<Movie> movies;
    private ImageView imgCover;

    public AdapterTrailers(Fragment fragment, List<Movie> movies){
        super(fragment.getContext(), R.layout.watch_movie_view, movies);
        Log.d(TAG, "AdapterMovies()");
        this.fragment = fragment;
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView()");
        LayoutInflater inflater = (LayoutInflater) fragment.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.watch_movie_view, parent, false);
        return rowView;
    }
}
