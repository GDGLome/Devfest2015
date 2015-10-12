package org.gdg_lome.devfest2015;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.gdg_lome.devfest2015.model.Track;

import java.util.ArrayList;

/**
 * Created by setico on 25/09/15.
 */
public class ScheduleAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Track> tracks;
    /**
     * Cache of the children views for a track list item.
     */

    public ScheduleAdapter(Context context, ArrayList<Track> tracks){
        this.context = context;
        this.tracks = tracks;
    }

    public static class ViewHolder {
        public final TextView title;
        public final TextView start;
        //public final TextView end;
        public final TextView speaker_name;

        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.title);
            start = (TextView) view.findViewById(R.id.start);
            //end = (TextView) view.findViewById(R.id.end);
            speaker_name = (TextView) view.findViewById(R.id.speaker_name);
        }
    }

    @Override
    public int getCount() {
        return tracks.size();
    }

    @Override
    public Object getItem(int i) {
        return tracks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Track track = tracks.get(i);
        View v = view;
        ViewHolder viewHolder;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.schedule_items,viewGroup,false);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.title.setText(track.getTitle());
        viewHolder.speaker_name.setText(track.getSpeaker().getName());
        viewHolder.start.setText(track.getStart());
        return v;

    }


}