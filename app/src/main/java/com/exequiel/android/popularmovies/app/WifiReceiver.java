package com.exequiel.android.popularmovies.app;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by exequiel on 21/12/2016.
 */

public class WifiReceiver extends BroadcastReceiver {
    private String TAG = WifiReceiver.class.getCanonicalName();

    /**
     * Implementation found in http://stackoverflow.com/a/4009133
     * @param context
     * @return
     */
    private boolean isOnline(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }



    @Override
    public void onReceive(Context context, Intent intent) {

        if (isOnline(context)){
                Log.d(TAG, "Wifi is on");
        }else{
            Log.d(TAG, "Wifi is off");
            new AlertDialog.Builder(context).setTitle("There is no Internet").setMessage("Pleas connect you dispositive to internet").show();
        }
    }
}
