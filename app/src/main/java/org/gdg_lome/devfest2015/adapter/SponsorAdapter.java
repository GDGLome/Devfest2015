package org.gdg_lome.devfest2015.adapter;

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

import org.gdg_lome.devfest2015.R;
import org.gdg_lome.devfest2015.Utils;
import org.gdg_lome.devfest2015.model.Sponsor;

import java.util.ArrayList;

/**
 * Created by setico on 25/09/15.
 */
public class SponsorAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Sponsor> sponsors;
    /**
     * Cache of the children views for a Speaker list item.
     */

    public SponsorAdapter(Context context, ArrayList<Sponsor> sponsors){
        this.context = context;
        this.sponsors = sponsors;
    }

    public static class ViewHolder {
        public final ImageView sponsor_logo;
        public final TextView sponsor_name;

        public ViewHolder(View view) {
            sponsor_logo = (ImageView) view.findViewById(R.id.sponsor_logo);
            sponsor_name = (TextView) view.findViewById(R.id.sponsor_name);
        }
    }

    @Override
    public int getCount() {
        return sponsors.size();
    }

    @Override
    public Object getItem(int i) {
        return sponsors.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Sponsor sponsor = sponsors.get(i);
        View v = view;
        final ViewHolder viewHolder;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.sponsor_items,viewGroup,false);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.sponsor_name.setText(sponsor.getName());
        Glide.with(context)
                .load(Utils.BACKEND_IMAGES_PATH + sponsor.getLogo())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(viewHolder.sponsor_logo) {

                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        viewHolder.sponsor_logo.setImageDrawable(circularBitmapDrawable);
                    }
                });
        return v;

    }


}