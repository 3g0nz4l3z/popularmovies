package com.exequiel.android.popularmovies.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by egonzalez on 1/12/17.
 */

public class HelperReview {
    private static final String TAG = HelperReview.class.getCanonicalName();
    private String tableName = "Review";
    private DBHelper DBHelper;
    private SQLiteDatabase database;

    public HelperReview(Context context){
        DBHelper = new DBHelper(context);
        database = DBHelper.getWritableDatabase();
    }


    /**
     * Inserts a row in Review table with the data necesary.
     * @return
     */
    public long insertMovieProfile(String movieId, String author, String review){
        ContentValues values = new ContentValues();
        values.put("idMovie",movieId);
        values.put("author",author);
        values.put("review",review);

        return database.insert(tableName, null, values);
    }

    /**
     * It is a very specific method to get the reviews with a specific id
     * @return
     */
    public ArrayList<String> getMovieProfile(String idMovie)
    {
        ArrayList<String> reviewAux = new ArrayList<String>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+tableName+"where idMovie="+idMovie, null);
        try{
            while(cursor.moveToNext()){
                String author = cursor.getString(cursor.getColumnIndex("author")); // I am no using author currently but you never know
                String review = cursor.getString(cursor.getColumnIndex("review"));
                reviewAux.add(review);
            }
        } finally {
            cursor.close();
        }
        return reviewAux;
    }

    public void truncate(){
        database.execSQL("DELETE FROM "+tableName);
        database.execSQL("VACUUM");
    }
}
