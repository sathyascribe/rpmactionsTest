package com.inventica.rpmapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.adapter.CustomAdapter;
import com.inventica.rpmapp.ui.modles.RpmModule;
import com.inventica.rpmapp.ui.modles.itemModel;

import java.util.ArrayList;

public class AllSongsFragment extends Fragment {

    String[] mobileArray = {"Notification","Manage Permissions","Privacy Policy","Terms of Use",
            "About","Send us Feedback"};
    boolean[] listImages={true, true, true, true, true,true};

    int image[] = {R.drawable.icon_activity,
            R.drawable.icon_activity,
            R.drawable.icon_calendar,
            R.drawable.icon_activity,
            R.drawable.icon_calendar,
            R.drawable.ic_menu_camera,
           };
    String name[] = {"chocolate", "Aubergine", "Banana", "Burger", "Cake", "Ice Cream", "Tomato", "Watermelon"};
    ArrayList<itemModel> arrayList;
    CustomAdapter adapter1;
    int[] images = {R.drawable.music, R.drawable.music, R.drawable.music,R.drawable.music, R.drawable.music};

    ArrayList<RpmModule> dataModels;
    ListView listView;
    private AllSongsListAdapter adapter;
    AllSongsListAdapter lAdapter;
    String[] version = {"Divergent", "Divergent", "Divergent", "Divergent", "Divergent"};

    String[] versionNumber = {"Ooyy", "Ooyy", "Ooyy", "Ooyy", "Ooyy"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_allsongs, container, false);
        listView=(ListView) root.findViewById(R.id.mobile_list);

/*        dataModels= new ArrayList<>();

        dataModels.add(new RpmModule("Sandra Adams", "sent you connection"));
        dataModels.add(new RpmModule("Imma jones", "Has invite you to participate in challenge"));
        dataModels.add(new RpmModule("Mark Smith", "The membership rules"));

        adapter= new CustomAdapterNotification(dataModels,getContext());
        */

        lAdapter = new AllSongsListAdapter(getActivity(), version, versionNumber, images);
        listView.setAdapter(lAdapter);
        return root;
    }
}

class AllSongsListAdapter extends BaseAdapter {

    Context context;
    private final String [] values;
    private final String [] numbers;
    private final int [] images;

    public AllSongsListAdapter(Context context, String [] values, String [] numbers, int [] images){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.values = values;
        this.numbers = numbers;
        this.images = images;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.child_allsongslist_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.Name_tv);
            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.emailId_tv);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.image_view_shoe);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(values[position]);
        viewHolder.txtVersion.setText(numbers[position]);
        viewHolder.icon.setImageResource(images[position]);

        return convertView;
    }

    private static class ViewHolder {

        TextView txtName;
        TextView txtVersion;
        ImageView icon;

    }

}

class CustomAdapterAllSongs extends ArrayAdapter<RpmModule> implements View.OnClickListener{

    private ArrayList<RpmModule> dataSet;
    Context mContext;

    // View lookup cache
    private class ViewHolder {
        TextView txtName;
        TextView txtType;
        ImageView info;
    }

    public CustomAdapterAllSongs(ArrayList<RpmModule> data, Context context) {
        super(context, R.layout.child_farmerlist_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        RpmModule dataModel=(RpmModule)object;

         /*  switch (v.getId())
           {
               case R.id.item_info:
                   Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                           .setAction("No action", null).show();
                   break;
           }*/
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        RpmModule dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.child_farmerlist_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.Name_tv);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.emailId_tv);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.image_view_shoe);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

         /*  Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
           result.startAnimation(animation);*/
        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtType.setText(dataModel.getEmailId());
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}