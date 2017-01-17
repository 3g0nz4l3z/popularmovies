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
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exequiel on 12/12/2016.
 * This class manages everything related to the view of the movies when the app start
 */

public class MoviesFragment extends Fragment implements Refresher{
    private View rootView;
    private String TAG = MoviesFragment.class.getCanonicalName();
    private LinearLayout lLMovies;
    private GridView gVMovies;
    private ArrayAdapter<Movie> aAMovies;
    private ArrayList<Movie> lMovies;
    private LinearLayout progressBarContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("lMovies", lMovies);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");
        setHasOptionsMenu(true);
        if (rootView == null) {


            rootView = inflater.inflate(R.layout.movies, container, false);

            lLMovies = (LinearLayout) rootView.findViewById(R.id.llMovies);
            /**
             * Progress bar based on http://stackoverflow.com/a/12559601
             */
            progressBarContainer = (LinearLayout) lLMovies.findViewById(R.id.progressBarContainer);
            gVMovies = (GridView) lLMovies.findViewById(R.id.movies);
            if (savedInstanceState == null) {
                ManagerMovies.getInstance().fetch_by_top_rated(MoviesFragment.this);
                lMovies = ManagerMovies.getInstance().getMovies();
            } else if (savedInstanceState.getParcelableArrayList("lMovies") != null) {
                lMovies = savedInstanceState.getParcelableArrayList("lMovies");
            }
            Log.d(TAG, lMovies.size() + "");
            aAMovies = new AdapterMovies(MoviesFragment.this, lMovies);
            Log.d(TAG, aAMovies.getCount() + "");
            gVMovies.setAdapter(aAMovies);
            gVMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Movie movie = (Movie) parent.getItemAtPosition(position);
                    Bundle movieBundle = new Bundle();
                    movieBundle.putString("movie_id", movie.getMovie_id());
                    movieBundle.putString("coverUrl", movie.getCoverUrl());
                    movieBundle.putString("originalTitle", movie.getOriginalTitle());
                    movieBundle.putString("synopsis", movie.getSynopsis());
                    movieBundle.putString("userRating", movie.getUserRating());
                    movieBundle.putString("releaseDate", movie.getReleaseDate());
                    // movieBundle.putStringArrayList("reviews", movie.getReviews());
                    // movieBundle.putStringArrayList("trailers", movie.getTrailers());
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    MovieFragment mf = new MovieFragment();
                    mf.setArguments(movieBundle);
                    ft.replace(R.id.fragment_cointainer, mf);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            });
        }
        return rootView;
    }

    @Override
    public void onDestroy() {
        if (rootView.getParent() != null){
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_sort_movies, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * Implementation found in http://stackoverflow.com/a/23533575
     * @param item
     * @return
     */
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
            case R.id.sort_by_favorites:
                Log.d(TAG, "sort by favorites");
                ManagerMovies.getInstance().fetch_favorites(MoviesFragment.this);
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

    @Override
    public void start_progress_bar() {

        progressBarContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void end_progress_bar() {
        progressBarContainer.setVisibility(View.GONE);

    }
}
