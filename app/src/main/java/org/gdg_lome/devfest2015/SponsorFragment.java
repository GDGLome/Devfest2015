package org.gdg_lome.devfest2015;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.gdg_lome.devfest2015.adapter.ScheduleAdapter;
import org.gdg_lome.devfest2015.adapter.SponsorAdapter;
import org.gdg_lome.devfest2015.model.Schedule;
import org.gdg_lome.devfest2015.model.Sponsor;
import org.gdg_lome.devfest2015.model.Track;

import java.util.ArrayList;

/**
 * Created by setico on 26/09/15.
 */
public class SponsorFragment extends Fragment {

    private GridView list;
    private SponsorAdapter adapter;
    private ArrayList<Sponsor> sponsors = new ArrayList<Sponsor>();
    private Bundle extras;
    private TextView title;

    public SponsorFragment(){

    }

    @Override
    public void onPause() {
        // Save ListView state @ onPause
        super.onPause();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        extras = getArguments();
        sponsors = (ArrayList<Sponsor>) extras.getSerializable(Utils.SPONSOR_EXTRA);
        View rootView = inflater.inflate(R.layout.sponsor_fragment, container, false);
        list = (GridView) rootView.findViewById(R.id.list);
        title = (TextView) rootView.findViewById(R.id.title);
        title.setText(extras.getString(Utils.SPONSOR_ID));
        adapter = new SponsorAdapter(getActivity(),sponsors);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(sponsors.get(position).getUrl()));
                startActivity(i);
            }
        });
        return rootView;
    }




}