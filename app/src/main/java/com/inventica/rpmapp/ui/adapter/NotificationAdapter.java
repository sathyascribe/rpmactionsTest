package com.inventica.rpmapp.ui.adapter;

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
import com.inventica.rpmapp.ui.activity.connection.MyListUserModel;

import org.openapitools.client.model.GetNotificationRequests;
import org.openapitools.client.model.UpdateSongsModel;

import java.util.ArrayList;



public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private ArrayList<GetNotificationRequests> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private static RadioButton lastChecked = null;
    private static int lastCheckedPos = 0;

    public NotificationAdapter(Context context, ArrayList<GetNotificationRequests> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_view_notification_list_itmes, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        final GetNotificationRequests model = mData.get(position);
        holder.textName.setText(model.getUserName());
        holder.textEmail.setText(model.getUserEmailId().toString());

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textName, textEmail, textTime;


        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageProfile);
            textEmail = itemView.findViewById(R.id.textEmail);
            textName = itemView.findViewById(R.id.textName);
            textTime = itemView.findViewById(R.id.textTime);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    GetNotificationRequests getItem(int id) {
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

