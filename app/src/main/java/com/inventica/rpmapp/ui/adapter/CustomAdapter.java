package com.inventica.rpmapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.modles.itemModel;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<itemModel> arrayList;
    public CustomAdapter(Context context, ArrayList<itemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public  View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.setting_listview, parent, false);
        }
        ImageView image, delete;
        TextView name;
        image = (ImageView) convertView.findViewById(R.id.image);
       // delete = (ImageView) convertView.findViewById(R.id.delete);
        name = (TextView) convertView.findViewById(R.id.label);
//        image.setBackgroundResource(arrayList.get(position).getImgRes());
        name.setText(arrayList.get(position).getName());

       /* delete.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                Toast.makeText(context, arrayList.get(position).getName() + " Removed", Toast.LENGTH_LONG).show();
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });*/
        return convertView;
    }
}
