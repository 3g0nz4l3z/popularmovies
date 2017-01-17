package com.exequiel.android.popularmovies.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by egonzalez on 1/12/17.
 */

public class HelperTrailer {
    private static final String TAG = HelperTrailer.class.getCanonicalName();
    private String tableName = "Trailer";
    private DBHelper DBHelper;
    private SQLiteDatabase database;

    public HelperTrailer(Context context){
        DBHelper = new DBHelper(context);
        database = DBHelper.getWritableDatabase();
    }


    /**
     * Inserts a row in Review table with the data necesary.
     * @return
     */
    public long insertMovieTrailer(String movieId,  String trailer){
        ContentValues values = new ContentValues();
        values.put("idMovie",movieId);
        values.put("trailer",trailer);

        return database.insert(tableName, null, values);
    }

    /**
     * It is a very specific method to get the reviews with a specific id
     * @return
     */
    public ArrayList<String> getMovieProfile(String idMovie)
    {
        ArrayList<String> trailerAux = new ArrayList<String>();
        Cursor cursor = database.rawQuery("SELECT * FROM "+tableName+"where idMovie="+idMovie, null);
        try{
            while(cursor.moveToNext()){
               String trailer = cursor.getString(cursor.getColumnIndex("trailer"));
                trailerAux.add(trailer);
            }
        } finally {
            cursor.close();
        }
        return trailerAux;
    }

    public void truncate(){
        database.execSQL("DELETE FROM "+tableName);
        database.execSQL("VACUUM");
    }
}
