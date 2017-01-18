package com.exequiel.android.popularmovies.app;

import java.util.List;

import android.app.Fragment;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by exequiel on 12/12/2016.
 */

public class AdapterMovies extends ArrayAdapter<Movie> {
    private String TAG = AdapterMovies.this.getClass().getCanonicalName();
    private Fragment fragment;
    private List<Movie> movies;
    private ImageView imgCover;

    public AdapterMovies(Fragment fragment, List<Movie> movies){
        super(fragment.getContext(), R.layout.cover_view, movies);
        Log.d(TAG, "AdapterMovies()");
        this.fragment = fragment;
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView()");
        LayoutInflater inflater = (LayoutInflater) fragment.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.cover_view, parent, false);
        imgCover = (ImageView) rowView.findViewById(R.id.imageViewCover);
        Log.d(TAG, movies.get(position).getCoverUrl());
        Glide.with(fragment).load(movies.get(position).getCoverUrl()).error(R.mipmap.ic_launcher).into(imgCover);
        return rowView;
    }
}
