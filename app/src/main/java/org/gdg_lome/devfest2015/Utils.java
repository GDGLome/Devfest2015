package org.gdg_lome.devfest2015;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.gdg_lome.devfest2015.model.Attendee;
import org.gdg_lome.devfest2015.model.Barcode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by setico on 11/10/15.
 */
public class Utils {
    public static final String TOKEN ="3LRK2TCXC7UVJSXZRNCH";
    public static final String EVENT_ID="18470364357";
    public static final String EVENT_URL="https://www.eventbrite.fr/e/billets-gdg-lome-devfest-2015-18470364357";
    public static final String ATTENDEES_URL="https://www.eventbriteapi.com/v3/events/"+EVENT_ID+"/attendees/?token="+TOKEN;
    public static final String BACKEND_URL = "https://devfestlome.firebaseio.com";
    public static final String PREFS_ATTENDEE_ID="attendee_id";
    public static final String SCHEDULE_ID="schedule_id";
    public static final String SPEAKER_EXTRA="speaker";
    public static final String TRACK_EXTRA="track";
    public static final String SCHEDULE_DATE_EXTRA="schedule_date";
    public static final String BACKEND_ATTENDEE_PATH="attendees";
    public static final String BACKEND_SCHEDULE_PATH="schedules";
    public static final String BACKEND_CONFERENCE_PATH="0";
    public static final String BACKEND_CODELAB_PATH="1";
    public static final String BACKEND_TRACK_PATH="tracks";
    public static final String BACKEND_BOOKMARK_PATH="bookmarks";
    public static final String BACKEND_VENUE_PATH="venue";
    public static final String BACKEND_SPEAKER_PATH="speakers";
    public static final String BACKEND_CONTRIBUTOR_PATH="contributors";
    public static final String BACKEND_SPONSOR_PATH="sponsors";
    public static final String BACKEND_IMAGES_PATH="http://devfest.gdg-lome.org";


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
            Attendee attendee =null ;
            try{
            attendee = new Attendee(jsonObject.getString("id"),
                    jsonObject.getJSONObject("profile").getString("name"),
                    jsonObject.getJSONObject("profile").getString("email"),
                    new Gson().fromJson(jsonObject.getJSONArray("barcodes").getJSONObject(0).toString(), Barcode.class));
            }catch (Exception e){

            }
            if(jsonObject!=null)
            {
                authListener.authSuccessfully(attendee);
            }
            else
                authListener.authFailed();
        }


    }



    public interface AttendeeAuthListener {
        void authSuccessfully(Attendee attendee);
        void authFailed();
    }

    public static Boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null)
            return true;
        return false;
    }

    public static Bitmap createQRCode(String barcode){
        Bitmap bitmap = Bitmap.createBitmap(150, 150, Bitmap.Config.ARGB_8888);
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(barcode, BarcodeFormat.QR_CODE, 150, 150);

            for (int i = 0; i < 150; i++) {//width
                for (int j = 0; j < 150; j++) {//height
                    bitmap.setPixel(i, j, bitMatrix.get(i, j) ? Color.BLACK: Color.WHITE);
                }
            }
        }catch (Exception e){

        }

        return bitmap;
    }

    public static boolean getAgendaPrefs(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(context.getString(R.string.pref_agenda_key),
                Boolean.parseBoolean(context.getString(R.string.pref_agenda_default)));

    }

    public static boolean getNotifPrefs(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(context.getString(R.string.pref_notif_key),
                Boolean.parseBoolean(context.getString(R.string.pref_agenda_default)));

    }

    public static boolean getNotePrefs(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean(context.getString(R.string.pref_note_key),
                Boolean.parseBoolean(context.getString(R.string.pref_agenda_default)));

    }


}
