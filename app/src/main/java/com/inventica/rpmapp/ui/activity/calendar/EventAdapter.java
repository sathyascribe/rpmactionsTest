package com.inventica.rpmapp.ui.activity.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.gallery.GalleryAdaptor;

import org.openapitools.client.model.GetEventdetailsModel;

import java.util.ArrayList;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private ArrayList<String> mData;
    private ArrayList<GetEventdetailsModel> mData1;
    private LayoutInflater mInflater;
    private GalleryAdaptor.ItemClickListener mClickListener;
    private static RadioButton lastChecked = null;
    private static int lastCheckedPos = 0;

    public EventAdapter(Context context, ArrayList<GetEventdetailsModel>date1,ArrayList<String> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.mData1 = date1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_view_calendar_event_items, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       // final  GetEventdetailsModel model = mData1.get(position);

        if (position % 2 == 0) {
            holder.constraintLayout.setBackgroundResource(R.drawable.bg_30_white);
            holder.textTitle.setTextColor(mInflater.getContext().getColor(R.color.event_pink));
            holder.textTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.event_dot, 0, 0, 0);

        } else {
            holder.constraintLayout.setBackgroundResource(R.drawable.bg_30_grey);
            holder.textTitle.setTextColor(mInflater.getContext().getColor(R.color.event_green));
            holder.textTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.event_dot_green, 0, 0, 0);
        }

//        holder.constraintLayout.setOnClickListener(v -> {
//
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("Object", mData1.get(position));
//
//            mInflater.getContext().startActivity(new Intent(mInflater.getContext(), EventDetailsActivity.class).putExtras(bundle));
//
//        });

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ConstraintLayout constraintLayout;
        CardView cardView;
        TextView textTitle, textDistance, textStartDate, textEndDate, textStartTime, textEndTime;

        ViewHolder(View itemView) {
            super(itemView);

            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            textDistance = itemView.findViewById(R.id.textDistance);
            textStartDate = itemView.findViewById(R.id.textStartDate);
            textEndDate = itemView.findViewById(R.id.textEndDate);
            textStartTime = itemView.findViewById(R.id.textStartTime);
            textEndTime = itemView.findViewById(R.id.textEndTime);
            textTitle = itemView.findViewById(R.id.textEventName);



            // cardView = itemView.findViewById(R.id.cardView);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(GalleryAdaptor.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

