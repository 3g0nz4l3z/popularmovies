package com.exequiel.android.popularmovies.app;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.exequiel.android.popularmovies.app.Movie;
/**
 * Created by exequiel on 12/12/2016.
 */

public class AdapterMovies extends ArrayAdapter<Movie> {
    private Context context;
    private List<Movie> movies;
    private ImageView imgCover;

    public AdapterMovies(Context context, List<Movie> movies){
        super(context, R.layout.cover_view, movies);
        this.context = context;
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cover_view, parent, false);
        imgCover = (ImageView) rowView.findViewById(R.id.ib_cover);
        Glide.with(context).load(movies.get(position).getCoverUrl()).into(imgCover);
        return super.getView(position, convertView, parent);
    }
}
