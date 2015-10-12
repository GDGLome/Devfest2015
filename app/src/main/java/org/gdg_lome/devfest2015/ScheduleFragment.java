package org.gdg_lome.devfest2015;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.gdg_lome.devfest2015.model.Schedule;
import org.gdg_lome.devfest2015.model.Track;

import java.util.ArrayList;

/**
 * Created by setico on 26/09/15.
 */
public class ScheduleFragment extends Fragment {

    private ListView list;
    private ScheduleAdapter adapter;
    private ArrayList<Track> tracks = new ArrayList<Track>();
    private Schedule schedule;
    private String schedule_id;
    private Bundle extras;

    public ScheduleFragment(){

    }

    @Override
    public void onPause() {
        // Save ListView state @ onPause
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extras = getArguments();
        schedule_id = extras.getString(Utils.SCHEDULE_ID);
        Devfest2015Application.devfestBackend.child(Utils.BACKEND_SCHEDULE_PATH).child(schedule_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                schedule = dataSnapshot.getValue(Schedule.class);
                tracks.clear();
                for(int i=0;i<schedule.getTracks().length;i++)
                    tracks.add(schedule.getTracks()[i]);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.schedule_fragment, container, false);
        list = (ListView) rootView.findViewById(R.id.list);
        adapter = new ScheduleAdapter(getActivity(),tracks);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), SpeakersDetailActivity.class);
                startActivity(i);
            }
        });
        return rootView;
    }




}