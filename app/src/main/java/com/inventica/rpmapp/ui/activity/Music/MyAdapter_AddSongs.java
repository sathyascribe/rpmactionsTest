package com.inventica.rpmapp.ui.activity.Music;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.inventica.rpmapp.R;

import org.openapitools.client.model.UpdateSongsModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by flaviusmester on 23/02/15.
 */
public class MyAdapter_AddSongs extends RecyclerView.Adapter<MyAdapter_AddSongs.ViewHolder> implements FastScrollRecyclerViewInterface {
    private ArrayList<String> mDataset;
    private HashMap<String, Integer> mMapIndex;
    ArrayList<UpdateSongsModel> msong_datalist;//=new ArrayList<UpdateSongsModel>();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
      //  public android.widget.TextView mTextView;
        TextView txtName;
        TextView txtVersion;
        ImageView icon;
        CheckBox check_status;
        public ViewHolder(View v) {
            super(v);
          //  mTextView = v;
            txtName = (TextView) v.findViewById(R.id.SongName_tv);
            txtVersion = (TextView) v.findViewById(R.id.songDescription_tv);
            icon = (ImageView) v.findViewById(R.id.image_view_shoe);
            check_status = (CheckBox) v.findViewById(R.id.check_status);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter_AddSongs(ArrayList<String> myDataset, HashMap<String, Integer> mapIndex, ArrayList<UpdateSongsModel> song_datalist) {
        mDataset = myDataset;
        mMapIndex = mapIndex;
        msong_datalist = song_datalist;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_allsongslist_item_add, parent, false);
        // set the view's size, margins, paddings and layout parameters
//        ...
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final UpdateSongsModel model = msong_datalist.get(position);
        holder.txtName.setText(model.getTitle());
        holder.txtVersion.setText(model.getArtist());

        if(model.getSelectedSong().toString().equals("1")) {
            holder.check_status.setChecked(true);
            //holder.mStatus.setSelected(true);
        }
        if(model.getSelectedSong().toString().equals("0")) {
            holder.check_status.setChecked(false);
            //holder.mStatus.setSelected(false);
        }

        holder.check_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("insidelistview","listview clicked");

                if(isChecked) {
                    Log.d("ischhecked","true");
                    holder.check_status.setChecked(true);
                    msong_datalist.get(position).setSelectedSong("1");
                }else{
                    Log.d("ischhecked","false");
                    holder.check_status.setChecked(false);
                    msong_datalist.get(position).setSelectedSong("0");
                }

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public HashMap<String, Integer> getMapIndex() {
        return this.mMapIndex;
    }
}