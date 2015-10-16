package org.gdg_lome.devfest2015;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.gdg_lome.devfest2015.model.Venue;

/**
 * Created by setico on 15/03/15.
 */
public class MapFragment extends Fragment {

    public GoogleMap map;
    private static View rootView;

        public MapFragment() {
        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpMapIfNeeded();
    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
            rootView = inflater.inflate(R.layout.map_fragment, container, false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }
            return rootView;
        }


    @Override
    public void onResume() {
        super.onResume();
    }


    private void setUpMapIfNeeded() {
        if (map == null) {
            map = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMap();
            if (map != null) {
                setUpMap();

                // allows info windows to be clicked on and open OptionsActivity
                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
                    @Override
                    public void onInfoWindowClick(final Marker marker){
                        if(marker.isVisible()){

                        }
                    }
                });
            }
        }
    }

    private void setUpMap() {

        Devfest2015Application.devfestBackend.child(Utils.BACKEND_VENUE_PATH).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Venue venue = dataSnapshot.getValue(Venue.class);
                map.addMarker(new MarkerOptions()
                                .title(venue.getName())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                                .position(new LatLng(new Double(venue.getLocation().getLat()),
                                        new Double(venue.getLocation().getLng())))
                );
/*
                LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
                LatLng targetLocationLatLng = new LatLng(new Double(venue.getLocation().getLat()),
                        new Double((venue.getLocation().getLng())));
                LatLng currentLocationLatLng = new LatLng(new Double(6.175590),new Double(1.215105));
                boundsBuilder.include(currentLocationLatLng);
                boundsBuilder.include(targetLocationLatLng);
                LatLngBounds bounds = boundsBuilder.build();
                map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 1));*/
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

}
