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
import com.squareup.picasso.Picasso;

import org.openapitools.client.model.GetVideoDetailsModel;

import java.util.ArrayList;


public class VideoCategory2 extends RecyclerView.Adapter<VideoCategory2.ViewHolder> {

    private ArrayList<GetVideoDetailsModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private static RadioButton lastChecked = null;
    private static int lastCheckedPos = 0;

    public VideoCategory2(Context context, ArrayList<GetVideoDetailsModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_view_video_category, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final GetVideoDetailsModel item = mData.get(position);

        holder.title.setVisibility(View.GONE);
        int imageID = R.drawable.gallery_3;
        if (item.getId() == 0) {
            imageID = R.drawable.gallery_3;
        } else if (item.getId() == 1) {

            imageID = R.drawable.gallery_2;
        } else if (item.getId() == 2) {

            imageID = R.drawable.gallery_1;
        }

        Picasso.with(mInflater.getContext())
                .load(item.getVideoImagePath()) // Equivalent of what ends up in onBitmapLoaded
                .placeholder(R.mipmap.ic_launcher)
                .error(imageID)
                .centerCrop()
                .fit()
                .into(holder.imageView);

        holder.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(mInflater.getContext(), VideoPlayerActivity.class);
            intent.putExtra("VideoUrl", item.getVideoFilePath());
            intent.putExtra("CategoryName", item.getCategoryName());
            mInflater.getContext().startActivity(intent);

        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView imageView;
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewSlide);
            title = itemView.findViewById(R.id.textTitle);



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

