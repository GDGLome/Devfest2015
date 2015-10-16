package org.gdg_lome.devfest2015;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Map;


public class PresentationActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView title;
    private TextView description;
    private TextView lieu;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Présentation");

        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        lieu = (TextView) findViewById(R.id.lieu);
        image = (ImageView) findViewById(R.id.image);

        Devfest2015Application.devfestBackend.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> event = (Map<String, String>) dataSnapshot.getValue();
                title.setText(Html.fromHtml("<u>"+event.get("title")+"</u>"));
                description.setText(Html.fromHtml("<p style=\"text-align:justify\">"+event.get("description")+"</p>"));
                lieu.setText(Html.fromHtml("<u>Lieu</u>"));
                Glide.with(PresentationActivity.this)
                        .load(event.get("image"))
                        .into(image);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.map_fragment, new MapFragment())
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        if(id==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
