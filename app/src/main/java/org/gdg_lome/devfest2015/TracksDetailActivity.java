package org.gdg_lome.devfest2015;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import org.gdg_lome.devfest2015.model.Speaker;
import org.gdg_lome.devfest2015.model.Track;
import org.w3c.dom.Text;


public class TracksDetailActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private CardView speaker;
    private ImageView speaker_image;
    private ImageView schedule_icon;
    private TextView speaker_name;
    private TextView speaker_description;
    private TextView track_title;
    private TextView track_date;
    private TextView track_start_end;
    private TextView track_description;
    private TextView track_room;
    private Track track;
    private Bundle extras;
    private String schedule_date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_detail);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("");

        extras = getIntent().getExtras();

        track = (Track) extras.getSerializable(Utils.TRACK_EXTRA);
        schedule_date = extras.getString(Utils.SCHEDULE_DATE_EXTRA);

        speaker = (CardView) findViewById(R.id.speaker);
        speaker_image = (ImageView) findViewById(R.id.speaker_image);
        speaker_name = (TextView) findViewById(R.id.speaker_name);
        speaker_description = (TextView) findViewById(R.id.speaker_description);
        schedule_icon = (ImageView) findViewById(R.id.schedule_icon);
        track_title = (TextView) findViewById(R.id.track_title);
        track_description = (TextView) findViewById(R.id.track_description);
        track_start_end = (TextView) findViewById(R.id.track_start_end);
        track_date = (TextView) findViewById(R.id.track_date);
        track_room = (TextView) findViewById(R.id.track_room);

        Drawable schedule_base_icon = getResources().getDrawable(R.drawable.ic_schedule);
        schedule_base_icon = DrawableCompat.wrap(schedule_base_icon);
        DrawableCompat.setTint(schedule_base_icon, getResources().getColor(android.R.color.white));
        schedule_icon.setImageDrawable(schedule_base_icon);

        track_title.setText(track.getTitle());
        track_date.setText(schedule_date);
        track_start_end.setText(track.getStart()+" - "+track.getEnd());
        track_description.setText(track.getDescription());
        track_room.setText(track.getRoom());

        speaker_name.setText(track.getSpeaker().getName());
        speaker_description.setText(track.getSpeaker().getDescription());
        Glide.with(this)
                .load(Utils.BACKEND_IMAGES_PATH + track.getSpeaker().getImage())
                .asBitmap()
                .centerCrop()
                .placeholder(R.drawable.ic_speaker)
                .into(new BitmapImageViewTarget(speaker_image) {

                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        speaker_image.setImageDrawable(circularBitmapDrawable);
                    }
                });

        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(TracksDetailActivity.this, SpeakersDetailActivity.class);
                i.putExtra(Utils.SPEAKER_EXTRA, track.getSpeaker());
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_track, menu);
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
