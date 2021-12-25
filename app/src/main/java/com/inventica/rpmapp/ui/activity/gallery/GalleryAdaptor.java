package com.inventica.rpmapp.ui.activity.gallery;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.modles.MyVideoModel;

import java.util.ArrayList;

public class GalleryAdaptor extends RecyclerView.Adapter<GalleryAdaptor.ViewHolder> {

    private ArrayList<MyVideoModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private static RadioButton lastChecked = null;
    private static int lastCheckedPos = 0;

    public GalleryAdaptor(Context context, ArrayList<MyVideoModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_view_gallery_video_categories_items, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final MyVideoModel model = mData.get(position);
        holder.title.setText(model.getTitle());

        RecyclerView.Adapter adapter = new VideoCardAdaptor(mInflater.getContext(),model.getDataList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mInflater.getContext(), RecyclerView.HORIZONTAL, false);
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setLayoutManager(layoutManager);


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        RecyclerView recyclerView;
        TextView title;


        ViewHolder(View itemView) {
            super(itemView);

             recyclerView = itemView.findViewById(R.id.recyclerViewVideoCategory);
             title = itemView.findViewById(R.id.textTitle);


        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    MyVideoModel getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

