package com.exequiel.android.popularmovies.app;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.app.FragmentManager;
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
        lMovies = ManagerMovies.getInstance().getMovies();
        AAMovies = new AdapterMovies(MoviesFragment.this, lMovies);
        gVMovies.setAdapter(AAMovies);
        gVMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) parent.getItemAtPosition(position);
                Bundle movieBundle = new Bundle();
                movieBundle.putString("coverUrl", movie.getCoverUrl());
                movieBundle.putString("originalTitle", movie.getOriginalTitle());
                movieBundle.putString("synopsis", movie.getSynopsis());
                movieBundle.putString("userRating", movie.getUserRating());
                movieBundle.putString("releaseDate", movie.getReleaseDate());
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                MovieFragment mf = new MovieFragment();
                mf.setArguments(movieBundle);
                ft.replace(R.id.fragment_cointainer, mf).commit();
            }
        });
        return rootView;
    }

}
