package com.exequiel.android.popularmovies.app;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exequiel on 12/12/2016.
 * This class manages everything related to the view of the movies when the app start
 */

public class MoviesFragment extends Fragment {
    private String TAG = MoviesFragment.class.getCanonicalName();
    private GridView gVMovies;
    private ArrayAdapter<Movie> AAMovies;
    private List<Movie> lMovies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movies, container, false);
        gVMovies =  (GridView) rootView.findViewById(R.id.movies);
        Movie m1 = new Movie("https://i.imgur.com/DvpvklR.png","","", 0, "");
        Movie m2 = new Movie("https://i.imgur.com/DvpvklR.png","","", 0, "");
        Movie m3 = new Movie("https://i.imgur.com/DvpvklR.png","","", 0, "");
        Movie m4 = new Movie("https://i.imgur.com/DvpvklR.png","","", 0, "");
        lMovies = new ArrayList<Movie>();
        lMovies.add(m1);
        lMovies.add(m2);
        lMovies.add(m3);
        lMovies.add(m4);
        AAMovies = new AdapterMovies(MoviesFragment.this, lMovies);
        gVMovies.setAdapter(AAMovies);
        return rootView;
    }

}
