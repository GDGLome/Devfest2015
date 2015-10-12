package org.gdg_lome.devfest2015;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by setico on 11/10/15.
 */
public class Utils {
    public static final String TOKEN ="";
    public static final String EVENT_ID="17036476560";
    public static final String EVENT_URL="https://www.eventbrite.com/e/google-io-extended-lome-2015-tickets-17036476560";
    public static final String ATTENDEES_URL="https://www.eventbriteapi.com/v3/events/"+EVENT_ID+"/attendees/?token="+TOKEN;


    public static String request(String URL){
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            Request request = new Request
                    .Builder()
                    .url(URL)
                    .build();
            Response response = okHttpClient.
                    newCall(request).
                    execute();
            return response.body().string();
        }catch (IOException e){

        }
        return null;
    }


    public static class AuthTask extends AsyncTask<String,Void,JSONObject>{
        AttendeeAuthListener authListener;

        public AuthTask(){

        }
        public void setAuthListener(AttendeeAuthListener authListener) {
            this.authListener = authListener;
        }

        @Override
        protected JSONObject doInBackground(String... params) {

            try {

                int page_count = 1;
                for(int i =1;i<=page_count;i++) {
                    JSONObject result = new JSONObject(request(ATTENDEES_URL + "&page=" + i));
                    JSONObject pagination = result.getJSONObject("pagination");
                    page_count = pagination.getInt("page_count");
                    JSONArray attendees = result.getJSONArray("attendees");
                    for (int j = 0; j < attendees.length(); j++) {
                        if (attendees.getJSONObject(j).getJSONArray("barcodes").getJSONObject(0).getString("barcode").equals(params[0])) {
                            return attendees.getJSONObject(j);
                        }
                    }
                }

            }
            catch (Exception e){

            }
            return null;

        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            if(jsonObject!=null)
            {
                authListener.authSuccessfully(jsonObject);
            }
            else
                authListener.authFailed();
        }


    }



    public interface AttendeeAuthListener {
        void authSuccessfully(JSONObject data);
        void authFailed();
    }

    public static Boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null)
            return true;
        return false;
    }
}
