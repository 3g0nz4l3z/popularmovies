package com.exequiel.android.popularmovies.app;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by exequiel on 12/12/2016.
 */

public class Movie implements Parcelable{
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

    protected Movie(Parcel in) {
        coverUrl = in.readString();
        originalTitle = in.readString();
        synopsis = in.readString();
        userRating = in.readString();
        releaseDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(coverUrl);
        dest.writeString(originalTitle);
        dest.writeString(synopsis);
        dest.writeString(userRating);
        dest.writeString(releaseDate);
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
