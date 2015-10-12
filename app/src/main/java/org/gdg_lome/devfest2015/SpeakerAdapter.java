package org.gdg_lome.devfest2015;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import org.gdg_lome.devfest2015.model.Speaker;
import org.gdg_lome.devfest2015.model.Speaker;

import java.util.ArrayList;

/**
 * Created by setico on 25/09/15.
 */
public class SpeakerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Speaker> speakers;
    /**
     * Cache of the children views for a Speaker list item.
     */

    public SpeakerAdapter(Context context, ArrayList<Speaker> speakers){
        this.context = context;
        this.speakers = speakers;
    }

    public static class ViewHolder {
        public final ImageView speaker_image;
        public final TextView speaker_name;
        public final TextView speaker_description;

        public ViewHolder(View view) {
            speaker_image = (ImageView) view.findViewById(R.id.speaker_image);
            speaker_name = (TextView) view.findViewById(R.id.speaker_name);
            speaker_description = (TextView) view.findViewById(R.id.speaker_description);
        }
    }

    @Override
    public int getCount() {
        return speakers.size();
    }

    @Override
    public Object getItem(int i) {
        return speakers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Speaker speaker = speakers.get(i);
        View v = view;
        final ViewHolder viewHolder;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.speaker_items,viewGroup,false);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.speaker_name.setText(speaker.getName());
        viewHolder.speaker_description.setText(speaker.getDescription());
        Glide.with(context)
                .load(Utils.BACKEND_IMAGES_PATH + speaker.getImage())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(viewHolder.speaker_image) {

                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        viewHolder.speaker_image.setImageDrawable(circularBitmapDrawable);
                    }
                });
        return v;

    }


}