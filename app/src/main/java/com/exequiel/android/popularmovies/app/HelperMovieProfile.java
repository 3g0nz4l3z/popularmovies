package com.exequiel.android.popularmovies.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * This class manages the table MovieProfile
 * I should make in the future an abstract class for this sort of things
 * Created by egonzalez on 1/12/17.
 */

public class HelperMovieProfile {
    private static final String TAG = HelperMovieProfile.class.getCanonicalName();
    private String tableName = "MovieProfile";
    private DBHelper DBHelper;
    private SQLiteDatabase database;

    public HelperMovieProfile(Context context){
        DBHelper = new DBHelper(context);
        database = DBHelper.getWritableDatabase();
    }


    /**
     * Inserts a row in MovieProfile table with the data necesary.
     * @return
     */
    public long insertMovieProfile(String movieId, String coverUrl, String originalTitle, String sypnosis, String userRating, String releaseDate){
        ContentValues values = new ContentValues();
        values.put("idMovie",movieId);
        values.put("synopsis",sypnosis);
        values.put("date",releaseDate);
        values.put("rate",userRating);
        values.put("title",originalTitle);
        values.put("coverUrl",""+coverUrl);

        return database.insert(tableName, null, values);
    }

    /**
     * It is a very specific method to get the movies
     * @return
     */
    public ArrayList<Movie> getMovieProfile()
    {
        ArrayList<Movie> movieAux = new ArrayList<Movie>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+tableName, null);
        try{
            while(cursor.moveToNext()){
                String movieId = cursor.getString(cursor.getColumnIndex("idMovie"));
                String synopsis = cursor.getString(cursor.getColumnIndex("synopsis"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String rate = cursor.getString(cursor.getColumnIndex("rate"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String coverUrl = cursor.getString(cursor.getColumnIndex("coverUrl"));
                movieAux.add(new Movie(movieId, coverUrl, title, synopsis, rate, date));
            }
        } finally {
            cursor.close();
        }
        return movieAux;
    }

    public void truncate(){
        database.execSQL("DELETE FROM "+tableName);
        database.execSQL("VACUUM");
    }
}
