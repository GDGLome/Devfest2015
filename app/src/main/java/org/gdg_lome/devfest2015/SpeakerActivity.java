package org.gdg_lome.devfest2015;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.gdg_lome.devfest2015.model.Speaker;

import java.util.ArrayList;


public class SpeakerActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView list;
    private SpeakerAdapter adapter;
    private ArrayList<Speaker> speakers = new ArrayList<Speaker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Les speakers");
        list = (ListView) findViewById(R.id.list);
        adapter = new SpeakerAdapter(this,speakers);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SpeakerActivity.this, SpeakersDetailActivity.class);
                i.putExtra(Utils.SPEAKER_EXTRA, speakers.get(position));
                startActivity(i);
            }
        });

        Devfest2015Application.devfestBackend.child(Utils.BACKEND_SPEAKER_PATH).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                speakers.clear();
                for(DataSnapshot speaker:dataSnapshot.getChildren()){
                    speakers.add(speaker.getValue(Speaker.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

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
