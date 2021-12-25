package com.inventica.rpmapp.ui.activity.connection;


import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;

import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;

import com.inventica.rpmapp.R;

import java.util.ArrayList;

 class AchievementGridAdapter extends ArrayAdapter<String> {
    public AchievementGridAdapter(@NonNull Context context, ArrayList<String> courseModelArrayList) {
        super(context, 0, courseModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_view_achievement, parent, false);
        }
        String courseModel = getItem(position);

        ImageView courseIV = listitemView.findViewById(R.id.imageProfile);


        return listitemView;
    }
}