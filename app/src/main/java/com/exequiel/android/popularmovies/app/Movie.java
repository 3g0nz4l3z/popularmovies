package com.exequiel.android.popularmovies.app;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exequiel on 12/12/2016.
 */

public class Movie implements Parcelable{
    private String movie_id;
    private String coverUrl;
    private String originalTitle;
    private String synopsis;
    private String userRating;
    private String releaseDate;
    private List<String> reviews = new ArrayList<String>();
    private List<String> trailers = new ArrayList<String>();

    public Movie(String movie_id, String coverUrl, String originalTitle, String synopsis, String userRating, String releaseDate)
    {
        this.movie_id = movie_id;
        this.coverUrl = coverUrl;
        this.originalTitle = originalTitle;
        this.synopsis = synopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    protected Movie(Parcel in) {
        movie_id = in.readString();
        coverUrl = in.readString();
        originalTitle = in.readString();
        synopsis = in.readString();
        userRating = in.readString();
        releaseDate = in.readString();
        in.readList(reviews, Movie.class.getClassLoader());
        in.readList(trailers, Movie.class.getClassLoader());

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movie_id);
        dest.writeString(coverUrl);
        dest.writeString(originalTitle);
        dest.writeString(synopsis);
        dest.writeString(userRating);
        dest.writeString(releaseDate);
        dest.writeList(reviews);
        dest.writeList(trailers);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getCoverUrl() {
        return "http://image.tmdb.org/t/p/w185"+coverUrl;
    }

    public String getMovie_id() {
        return this.movie_id;
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

    public void setReview(String review){
        this.reviews.add(review);
    }
    public List<String> getReviews(){
        return reviews;
    }

    public void setTrailers(String trailer){
        this.trailers.add(trailer);
    }
    public List<String> getTrailers(){
        return trailers;
    }
}
