package org.gdg_lome.devfest2015;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParser;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;
import com.google.gson.Gson;

import org.gdg_lome.devfest2015.model.Attendee;
import org.gdg_lome.devfest2015.model.Barcode;
import org.gdg_lome.devfest2015.scanner.ScannerActivity;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {
    private EditText tf_confirmation_code;
    private TextView tv_register;
    private final String BARCODE="barcode";
    private final int SCANNER_ID=100;
    private String attendee_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attendee_id = Devfest2015Application.prefs.getString(Utils.PREFS_ATTENDEE_ID,null);
        if(attendee_id!=null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);
        tf_confirmation_code = (EditText) findViewById(R.id.confirmation_code);
        tv_register = (TextView)findViewById(R.id.register);
        tv_register.setText(Html.fromHtml("<u>" + tv_register.getText().toString() + "</u>"));

    }

    public void login(View v){
        Utils.AuthTask authTask = new Utils.AuthTask();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Authentification...");

        authTask.setAuthListener(new Utils.AttendeeAuthListener() {

            @Override
            public void authSuccessfully(Attendee attendee) {
                Devfest2015Application.devfestBackend.child("attendees").child(attendee.getId()).setValue(attendee);
                Devfest2015Application.prefs.edit().putString(Utils.PREFS_ATTENDEE_ID,attendee.getId()).commit();
                progressDialog.dismiss();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();

            }

            @Override
            public void authFailed() {
                progressDialog.dismiss();

                Toast.makeText(LoginActivity.this, "Code de confirmation invalide! Inscrivez vous.", Toast.LENGTH_LONG).show();
            }
        });
        if(Utils.isConnected(this)) {
            progressDialog.show();
            authTask.execute(tf_confirmation_code.getText().toString());
        }else {
            Toast.makeText(LoginActivity.this, "Veuillez activer votre connexion internet.", Toast.LENGTH_LONG).show();

        }
    }

    public void scan(View v){
        startActivityForResult(new Intent(this, ScannerActivity.class), SCANNER_ID);
    }

    public void register(View v){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Utils.EVENT_URL)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case SCANNER_ID:
                if (resultCode == RESULT_OK) {
                    Bundle res = data.getExtras();
                    tf_confirmation_code.setText(res.getString(BARCODE));
                    login(null);
                }
                break;
        }
    }



}
