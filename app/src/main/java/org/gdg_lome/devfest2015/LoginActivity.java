package org.gdg_lome.devfest2015;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void login(View v){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void register(View v){
        startActivity(new Intent(this,MainActivity.class));
    }


}
