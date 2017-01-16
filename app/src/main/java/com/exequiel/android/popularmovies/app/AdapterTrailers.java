package com.exequiel.android.popularmovies.app;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exequiel on 12/12/2016.
 */

public class AdapterTrailers extends ArrayAdapter<String> {
    private String TAG = AdapterTrailers.this.getClass().getCanonicalName();
    private Fragment fragment;
    private ArrayList<String> trailersKey;
    private TextView textViewTrailer;
    private ImageButton imageButtonTrailer;

    public AdapterTrailers(Fragment fragment, ArrayList<String> trailersKey){
        super(fragment.getContext(), R.layout.watch_movie_view, trailersKey);
        Log.d(TAG, "AdapterTrailers()");
        this.fragment = fragment;
        this.trailersKey = trailersKey;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView()");
        LayoutInflater inflater = (LayoutInflater) fragment.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.watch_movie_view, parent, false);
        textViewTrailer = (TextView) rowView.findViewById(R.id.textViewTrailer);
        imageButtonTrailer = (ImageButton) rowView.findViewById(R.id.imageButtonWatchMovie);

        imageButtonTrailer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                fragment.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+trailersKey.get(position))));
            }
        });

        textViewTrailer.setText("Trailer "+(position+1));
        return rowView;
    }
}
