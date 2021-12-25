package com.inventica.rpmapp.ui.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inventica.rpmapp.R;

public class ImageAdapter extends BaseAdapter {

    public  String[] projList;
    Activity activity;
    private String[] mDisplayedValues;

    public ImageAdapter(Activity activity, String[] projList) {
        super();
        this.activity = activity;
        this.projList = projList;
       // this.mDisplayedValues = projList;
    }

    @Override
    public int getCount() {
        //return projList.size();
        Log.e("size", String.valueOf(mDisplayedValues.length));
        return mDisplayedValues.length;

    }

    @Override
    public String getItem(int position) {

        //return projList.get(position);
        return mDisplayedValues[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.setting_listview, null);
            holder = new ViewHolder();

            holder.label = (TextView) convertView.findViewById(R.id.label);
            holder.arrow = (ImageView) convertView.findViewById(R.id.arrow);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

                holder.label.setText(projList[position]);


        return convertView;
    }
    public static class ViewHolder {
        TextView label;
        ImageView arrow;
    }

}