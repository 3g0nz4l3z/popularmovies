package com.exequiel.android.popularmovies.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class MoviesFragment extends Fragment implements AdapterRefresher{
    private String TAG = MoviesFragment.class.getCanonicalName();
    private GridView gVMovies;
    private ArrayAdapter<Movie> aAMovies;
    private List<Movie> lMovies;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ManagerMovies.getInstance().fetch_by_top_rated(MoviesFragment.this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.movies, container, false);
        gVMovies =  (GridView) rootView.findViewById(R.id.movies);
        lMovies = ManagerMovies.getInstance().getMovies();
        Log.d(TAG, lMovies.size()+"");
        aAMovies = new AdapterMovies(MoviesFragment.this, lMovies);
        Log.d(TAG, aAMovies.getCount()+"");
        gVMovies.setAdapter(aAMovies);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_sort_movies, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.sort_by_popularity:
                Log.d(TAG, "sort by popularity");
                ManagerMovies.getInstance().fetch_by_popularity(MoviesFragment.this);
                break;
            case R.id.sort_by_rated:
                Log.d(TAG, "sort by rated");
                ManagerMovies.getInstance().fetch_by_top_rated(MoviesFragment.this);
                break;
        }
        return true;
    }

    @Override
    public void refresh() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "refresh()");
                aAMovies.notifyDataSetChanged();
                gVMovies.invalidateViews();
                gVMovies.setAdapter(aAMovies);
            }
        });
    }
}
