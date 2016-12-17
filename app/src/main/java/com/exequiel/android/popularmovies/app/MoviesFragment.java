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
        Movie m1 = new Movie("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","Super locos","Estos amigos estan relocos y nadie los puede parar", "5", "20161212");
        Movie m2 = new Movie("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","Un castillo llamado amor","El amor est en la puerta", "10", "20151212");
        Movie m3 = new Movie("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","Perdidos en Peru","Un viaje de placer termina en granja esclavizados sacando papas", "9.3", "19981010");
        Movie m4 = new Movie("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","","", "", "");
        Movie m5 = new Movie("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","","", "", "");
        Movie m6 = new Movie("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","","", "", "");
        Movie m7 = new Movie("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","","", "", "");
        Movie m8 = new Movie("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","","", "", "");
        Movie m9 = new Movie("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","","", "", "");
        Movie m10 = new Movie("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","","", "", "");
        Movie m11 = new Movie("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","","", "", "");
        Movie m12 = new Movie("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg","","", "", "");
        lMovies = new ArrayList<Movie>();
        lMovies.add(m1);
        lMovies.add(m2);
        lMovies.add(m3);
        lMovies.add(m4);
        lMovies.add(m5);
        lMovies.add(m6);
        lMovies.add(m7);
        lMovies.add(m8);
        lMovies.add(m9);
        lMovies.add(m10);
        lMovies.add(m11);
        lMovies.add(m12);
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
