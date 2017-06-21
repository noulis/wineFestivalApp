package com.example.noulis.winefestivalapplication;

/**
 * Created by noulis on 20/6/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private final String[] item_values;

    public ImageAdapter(Context context, String[] mobileValues) {
        this.context = context;
        this.item_values = mobileValues;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridView = new View(context);

            // get layout from grid_view_item.xml
            gridView = inflater.inflate(R.layout.grid_view_item, parent, false);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            textView.setText(item_values[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);

            String item = item_values[position];

            if (item.equals("Πώς να πας"))
                imageView.setImageResource(R.drawable.google_maps_icon);
            else if (item.equals("Facebook"))
                imageView.setImageResource(R.drawable.facebook_logo);
            else if (item.equals("Twitter"))
                imageView.setImageResource(R.drawable.twitter_icon);
            else if (item.equals("Για τη γιορτή"))
                imageView.setImageResource(R.drawable.grape_icon);
            else if (item.equals("Για τη Ζίτσα"))
                imageView.setImageResource(R.drawable.dimos_zitsas);
            else
                imageView.setImageResource(R.drawable.android_logo);
        }
        else {
            gridView = (View) convertView;
        }
        return gridView;
    }

    @Override
    public int getCount() { return item_values.length; }

    @Override
    public Object getItem(int position) { return null; }

    @Override
    public long getItemId(int position) { return 0; }

}
