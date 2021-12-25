package com.inventica.rpmapp.ui.activity.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.inventica.rpmapp.R;

import org.openapitools.client.model.GetVideoDetailsModel;

import java.util.ArrayList;


public class VideoCardAdaptor extends RecyclerView.Adapter<VideoCardAdaptor.ViewHolder> {

    private ArrayList<GetVideoDetailsModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private static RadioButton lastChecked = null;
    private static int lastCheckedPos = 0;

    public VideoCardAdaptor(Context context, ArrayList<GetVideoDetailsModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_video_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final GetVideoDetailsModel model = mData.get(position);

//        Picasso.with(mInflater.getContext())
//                .load(model.getVideoImagePath()) // Equivalent of what ends up in onBitmapLoaded
//                .placeholder(R.drawable.image_not_found)
//                .error(R.drawable.image_not_found)
//                .centerCrop()
//                .fit()
//                .into(holder.imageView);

        holder.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(mInflater.getContext(), VideoPlayerActivity.class);
            intent.putExtra("VideoUrl", model.getVideoFilePath());
            intent.putExtra("CategoryName", model.getCategoryName());
            mInflater.getContext().startActivity(intent);

        });
        holder.time.setText(model.getDuration());

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView time;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_video);
            time = itemView.findViewById(R.id.textTime);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    GetVideoDetailsModel getItem(int id) {
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

