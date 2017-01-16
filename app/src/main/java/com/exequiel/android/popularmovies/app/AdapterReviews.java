package com.exequiel.android.popularmovies.app;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exequiel on 12/12/2016.
 */

public class AdapterReviews extends ArrayAdapter<String> {
    private String TAG = AdapterReviews.this.getClass().getCanonicalName();
    private Fragment fragment;
    private ArrayList<String> reviews;
    private TextView textViewReviews;

    public AdapterReviews(Fragment fragment, ArrayList<String> reviews){
        super(fragment.getContext(), R.layout.view_review_view);
        Log.d(TAG, "AdapterReviews()");
        this.fragment = fragment;
        this.reviews = reviews;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView()");
        LayoutInflater inflater = (LayoutInflater) fragment.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.view_review_view, parent, false);
        textViewReviews = (TextView) rowView.findViewById(R.id.textViewReview);
        textViewReviews.setText(reviews.get(position));
        return rowView;
    }
}
