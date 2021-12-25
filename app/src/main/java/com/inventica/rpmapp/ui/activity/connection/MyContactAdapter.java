package com.inventica.rpmapp.ui.activity.connection;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.inventica.rpmapp.R;

import org.openapitools.client.model.ListUserModel;

import java.util.ArrayList;
import java.util.Random;


public class MyContactAdapter extends RecyclerView.Adapter<MyContactAdapter.ViewHolder> {

    private ArrayList<ListUserModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private static RadioButton lastChecked = null;
    private static int lastCheckedPos = 0;
    private ArrayList<ContactModel> myContactModels;


    public MyContactAdapter(Context context, ArrayList<ListUserModel> data,ArrayList<ContactModel> myContactModels ) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.myContactModels = myContactModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_view_contact_itmes, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        final ContactModel model = myContactModels.get(position);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));


        holder.textEmail.setText(model.getContactNumber());

        if(model.isChecked())
            holder.imageViewCheck.setVisibility(View.VISIBLE);
        else holder.imageViewCheck.setVisibility(View.INVISIBLE);

        if(model.getContactName()!= null)
        {
            String name =    getAlphabetsOfTheName(model.getContactName());
            holder.textName.setText(model.getContactName());
            holder.imageView.setText(name);
        }
        else {
            holder.imageView.setText("NA");
        }
       // holder.imageView.setBackgroundColor(color);
    }

    private String getAlphabetsOfTheName(String inviteUserName) {
        String finalString= "";

        String[] strings = inviteUserName.split(" ");
        for (String items:strings
        ) {
            finalString = finalString + items.charAt(0);
        }

        return  finalString.toUpperCase();
    }


    @Override
    public int getItemCount() {
        return myContactModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageViewCheck;
        TextView textName, textEmail,imageView;
        ConstraintLayout constraintLayout;

        ViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            imageView = itemView.findViewById(R.id.imageProfile);
            textEmail = itemView.findViewById(R.id.textEmail);
            textName = itemView.findViewById(R.id.textName);
            imageViewCheck = itemView.findViewById(R.id.imageViewCheck);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    ContactModel getItem(int id) {
        return myContactModels.get(id);
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

