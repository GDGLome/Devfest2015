package org.gdg_lome.devfest2015;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import org.gdg_lome.devfest2015.model.Speaker;


public class SpeakersDetailActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ImageView speaker_image;
    private TextView speaker_name;
    private TextView speaker_description;
    private Speaker speaker;
    private Bundle extras;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_detail);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("");

        extras = getIntent().getExtras();

        speaker = (Speaker) extras.getSerializable(Utils.SPEAKER_EXTRA);

        speaker_image = (ImageView) findViewById(R.id.speaker_image);
        speaker_name = (TextView) findViewById(R.id.speaker_name);
        speaker_description = (TextView) findViewById(R.id.speaker_description);

        speaker_name.setText(speaker.getName());
        speaker_description.setText(speaker.getDescription());
        Glide.with(this)
                .load(Utils.BACKEND_IMAGES_PATH + speaker.getImage())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(speaker_image) {

                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        speaker_image.setImageDrawable(circularBitmapDrawable);
                    }
                });

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
            return true;
        }

        if(id==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
