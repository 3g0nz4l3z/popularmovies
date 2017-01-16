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
    private final String api_key = BuildConfig.THEMOVIEDBAPIKEY; //Please insert api key here
    private  static ArrayList<Movie> movies = new ArrayList<Movie>();
    private ManagerMovies(){};

    public static ManagerMovies getInstance() {
        return INSTANCE;
    }

    public void fetch_by_popularity(String api_key){
        new FetchMovies().execute("http://api.themoviedb.org/3/movie/popular?api_key="+api_key);
    };
    public void fetch_by_popularity(){
        new FetchMovies().execute("http://api.themoviedb.org/3/movie/popular?api_key="+api_key);
    };

    public void fetch_by_popularity(Refresher refresher){
        new FetchMovies(refresher).execute("http://api.themoviedb.org/3/movie/popular?api_key="+api_key);
    };

    public void fetch_by_top_rated(String api_key){
        new FetchMovies().execute("http://api.themoviedb.org/3/movie/top_rated?api_key="+api_key);
    };

    public void fetch_by_top_rated(){
        new FetchMovies().execute("http://api.themoviedb.org/3/movie/top_rated?api_key="+api_key);
    };

    public void fetch_by_top_rated(Refresher refresher){
        new FetchMovies(refresher).execute("http://api.themoviedb.org/3/movie/top_rated?api_key="+api_key);
    };
    public void emptyMovies(){
        movies.clear();
    }

    public void addMovie(Movie movie){
        movies.add(movie);
    }

    public Movie getMovie(int index)
    {
        return movies.get(index);
    }

    public ArrayList<Movie> getMovies(){
        return movies;
    }


    public ArrayList<String> getTrailers(String movie_id){
        for (Movie movie: movies)
        {
            if (movie.getMovie_id().equals(movie_id))
            {
                return movie.getTrailers();
            }

        }
        return null;
    }


    public ArrayList<String> getReviews(String movie_id){
        for (Movie movie: movies)
        {
            if (movie.getMovie_id().equals(movie_id))
            {
                return movie.getTrailers();
            }

        }
        return null;
    }
}
