package com.exequiel.android.popularmovies.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by egonzalez on 1/12/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String tag = "DBHelper";
    private static final String name = "DBFavoritesMovies";
    private static final int version = 1;

    private static final String DatabaseCreateMovieProfile = "CREATE TABLE MovieProfile (id INTEGER PRIMARY KEY AUTOINCREMENT, idMovie TEXT UNIQUE, coverUrl TEXT UNIQUE, synopsis TEXT, date TEXT, rate TEXT, title TEXT)";
    private static final String DatabaseCreateReview = "CREATE TABLE Review (id INTEGER PRIMARY KEY AUTOINCREMENT, idMovie TEXT UNIQUE, review TEXT UNIQUE, author TEXT UNIQUE)";
    private static final String DatabaseCreateTrailer = "CREATE TABLE Trailer (id INTEGER PRIMARY KEY AUTOINCREMENT, idMovie TEXT UNIQUE, trailerKey TEXT UNIQUE)";
    private static final String DatabaseDropMovieProfile = "DROP TABLE IF EXISTS MovieProfile";
    private static final String DatabaseDropReview = "DROP TABLE IF EXISTS Review";
    private static final String DatabaseDropTrailer = "DROP TABLE IF EXISTS Trailer";

    public DBHelper(Context context) {
        super(context, name, null, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(tag, "onCreate()");
        // TODO Auto-generated method stub
        db.execSQL(DatabaseCreateMovieProfile);
        db.execSQL(DatabaseCreateReview);
        db.execSQL(DatabaseCreateTrailer);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(tag, "onUpgrade()");
        // TODO Auto-generated method stub
        db.execSQL(DatabaseDropMovieProfile);
        db.execSQL(DatabaseDropReview);
        db.execSQL(DatabaseDropTrailer);
        onCreate(db);
    }

}