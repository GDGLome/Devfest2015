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
import org.gdg_lome.devfest2015.model.Contributor;

import java.util.ArrayList;

/**
 * Created by setico on 25/09/15.
 */
public class ContributorAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Contributor> contributors;
    /**
     * Cache of the children views for a Contributor list item.
     */

    public ContributorAdapter(Context context, ArrayList<Contributor> contributors){
        this.context = context;
        this.contributors = contributors;
    }

    public static class ViewHolder {
        public final ImageView contributor_image;
        public final TextView contributor_name;
        public final TextView contributor_rule;

        public ViewHolder(View view) {
            contributor_image = (ImageView) view.findViewById(R.id.contributor_image);
            contributor_name = (TextView) view.findViewById(R.id.contributor_name);
            contributor_rule = (TextView) view.findViewById(R.id.contributor_rule);
        }
    }

    @Override
    public int getCount() {
        return contributors.size();
    }

    @Override
    public Object getItem(int i) {
        return contributors.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Contributor contributor = contributors.get(i);
        View v = view;
        final ViewHolder viewHolder;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.contributor_items,viewGroup,false);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.contributor_name.setText(contributor.getName());
        viewHolder.contributor_rule.setText(contributor.getRule());
        Glide.with(context)
                .load(Utils.BACKEND_IMAGES_PATH + contributor.getImage())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(viewHolder.contributor_image) {

                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        viewHolder.contributor_image.setImageDrawable(circularBitmapDrawable);
                    }
                });
        return v;

    }


}