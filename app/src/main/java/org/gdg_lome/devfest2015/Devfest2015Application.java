package org.gdg_lome.devfest2015;

import android.app.Application;
import android.content.SharedPreferences;

import com.firebase.client.Firebase;

/**
 * Created by setico on 12/10/15.
 */
public class Devfest2015Application extends Application {

    public static Firebase devfestBackend;
    public static SharedPreferences prefs;


    @Override
    public void onCreate() {
        super.onCreate();
        prefs = getSharedPreferences("org.gdg_lome.devfest2015",MODE_PRIVATE);
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        devfestBackend = new Firebase(Utils.BACKEND_URL);
    }
}
