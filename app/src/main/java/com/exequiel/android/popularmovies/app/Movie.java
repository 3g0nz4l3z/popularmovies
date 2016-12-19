package com.exequiel.android.popularmovies.app;

/**
 * Created by exequiel on 12/12/2016.
 */

public class Movie {
    private String coverUrl;
    private String originalTitle;
    private String synopsis;
    private String userRating;
    private String releaseDate;

    public Movie(String coverUrl, String originalTitle, String synopsis, String userRating, String releaseDate)
    {
        this.coverUrl = coverUrl;
        this.originalTitle = originalTitle;
        this.synopsis = synopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    public String getCoverUrl() {
        return "http://image.tmdb.org/t/p/w185"+coverUrl;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getUserRating() {
        return userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

}
