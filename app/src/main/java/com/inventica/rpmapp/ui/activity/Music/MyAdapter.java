package com.inventica.rpmapp.ui.activity.Music;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.connection.ContactModel;

import org.openapitools.client.model.UpdateSongsModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by flaviusmester on 23/02/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements FastScrollRecyclerViewInterface {
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
        public ViewHolder(View v) {
            super(v);
          //  mTextView = v;
            txtName = (TextView) v.findViewById(R.id.Name_tv);
            txtVersion = (TextView) v.findViewById(R.id.emailId_tv);
            icon = (ImageView) v.findViewById(R.id.image_view_shoe);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> myDataset, HashMap<String, Integer> mapIndex, ArrayList<UpdateSongsModel> song_datalist) {
        mDataset = myDataset;
        mMapIndex = mapIndex;
        msong_datalist = song_datalist;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        android.view.View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_allsongslist_item, parent, false);
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