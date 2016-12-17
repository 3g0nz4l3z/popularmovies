package com.exequiel.android.popularmovies.app;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Implementation found in https://developer.android.com/training/basics/fragments/fragment-ui.html#AddAtRuntime
         */
        if (findViewById(R.id.fragment_cointainer) != null){
            if (savedInstanceState != null) {return;}

            MoviesFragment mf = new MoviesFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fragment_cointainer, mf).commit();
        }
    }
}
