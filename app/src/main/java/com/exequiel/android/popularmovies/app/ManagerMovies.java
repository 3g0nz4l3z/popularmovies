package com.exequiel.android.popularmovies.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exequiel on 16/12/2016.
 * This class manages everything related to the Movies, as download the data, store the data, sort it, etc.
 *
 * For now the data is stored in a static array of Object:Movie, in the future it will be stored in sharedpreferences or sqlite
 */

public class ManagerMovies {
    private static final ManagerMovies INSTANCE = new ManagerMovies();
    private  static List<Movie> movies = new ArrayList<Movie>();
    private ManagerMovies(){};

    public static ManagerMovies getInstance() {
        return INSTANCE;
    }

    public void fetch_by_popularity(String api_key){
        new FetchMovies().execute("http://api.themoviedb.org/3/movie/popular?api_key="+api_key);
    };

    public void fetch_by_top_rated(String api_key){
        new FetchMovies().execute("http://api.themoviedb.org/3/movie/top_rated?api_key="+api_key);
    };

    public void emptyMovies(){
        movies = new ArrayList<Movie>();
    }

    public void addMovie(Movie movie){
        movies.add(movie);
    }

    public Movie getMovie(int index)
    {
        return movies.get(index);
    }

    public List<Movie> getMovies(){
        return movies;
    }
}
