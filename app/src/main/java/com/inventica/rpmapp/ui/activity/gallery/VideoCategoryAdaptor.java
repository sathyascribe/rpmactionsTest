package com.inventica.rpmapp.ui.activity.gallery;

import android.content.Context;
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

import org.openapitools.client.model.GetVideoCateoryModel;

import java.util.ArrayList;


public class VideoCategoryAdaptor extends RecyclerView.Adapter<VideoCategoryAdaptor.ViewHolder> {

    private ArrayList<GetVideoCateoryModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private static RadioButton lastChecked = null;
    private static int lastCheckedPos = 0;

    public VideoCategoryAdaptor(Context context, ArrayList<GetVideoCateoryModel> data) {
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

        final GetVideoCateoryModel item = mData.get(position);
        holder.title.setText(item.getVideoType());
        holder.time.setVisibility(View.GONE);

        if (item.getId() == 0) {
          holder.imageView.setImageResource(R.drawable.gallery_3);
        } else if (item.getId() == 1) {
            holder.imageView.setImageResource(R.drawable.gallery_2);
        } else if (item.getId() == 2) {
            holder.imageView.setImageResource(R.drawable.gallery_1);
        } else if (item.getId() == 3) {
            holder.imageView.setImageResource(R.drawable.gallery_3);
        } else if (item.getId() == 4) {
            holder.imageView.setImageResource(R.drawable.gallery_2);
        }



//        Picasso.with(this)
//                .load("https://media.geeksforgeeks.org/wp-content/uploads/20210101144014/gfglogo-300x300.png") // Equivalent of what ends up in onBitmapLoaded
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.drawable.error_gfg)
//                .centerCrop()
//                .fit()
//                .into(imageView);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView imageView;
        TextView title;
        TextView time;


        ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewSlide);
            title = itemView.findViewById(R.id.textTitle);
            time = itemView.findViewById(R.id.textTime);


        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    GetVideoCateoryModel getItem(int id) {
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

