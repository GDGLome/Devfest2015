package org.gdg_lome.devfest2015;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import org.gdg_lome.devfest2015.scanner.ScannerActivity;


public class LoginActivity extends AppCompatActivity {
    private EditText tf_confirmation_code;
    private String mScanFormat = "Format:";
    private String mScanContents = "Contents:";
    private final String BARCODE="barcode";
    private final int SCANNER_ID=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tf_confirmation_code = (EditText) findViewById(R.id.confirmation_code);

    }

    public void login(View v){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void scan(View v){
        startActivityForResult(new Intent(this, ScannerActivity.class), SCANNER_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case SCANNER_ID:
                if (resultCode == RESULT_OK) {
                    Bundle res = data.getExtras();
                    tf_confirmation_code.setText(res.getString(BARCODE));
                }
                break;
        }
    }



}
