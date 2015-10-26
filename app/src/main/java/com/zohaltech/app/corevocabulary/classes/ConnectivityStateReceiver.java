package com.zohaltech.app.corevocabulary.classes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ConnectivityStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        WebApiClient.sendUserData();
        WebApiClient.checkForUpdate();
    }
}