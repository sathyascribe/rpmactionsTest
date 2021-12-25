package com.inventica.rpmapp.ui.activity.gear;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.inventica.rpmapp.R;

import org.openapitools.client.model.GetUserGearModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;




public class RetiredGearAdaptor extends RecyclerView.Adapter<RetiredGearAdaptor.ViewHolder> {

    private ArrayList<GetUserGearModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public RetiredGearAdaptor(Context context, ArrayList<GetUserGearModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_view_retired_gear, parent, false);
        return new ViewHolder(view);
    }


    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final GetUserGearModel model = mData.get(position);

        holder.modelName.setText(model.getBrandName());
        holder.brandName.setText(model.getModelName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(model.getStartDate());
        String expDate = dateFormat.format(model.getExpiryDate());

        holder.expiryDate.setText(expDate);
        holder.date.setText(date);
        holder.type.setText(model.getIsWalking()? "Walking" : "Running");

    }

    void showCustomNameDialog(View root) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = root.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = mInflater.inflate(R.layout.dialog_retire,viewGroup, false);

        //  EditText challengeName= dialogView.findViewById(R.id.challengeName);
        Button submit=dialogView.findViewById(R.id.buttonRetire);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(mInflater.getContext());

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Log.e("tag","name: "+challengeName.getText().toString());
                alertDialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.cancel();
            }
        });
        //finally creating the alert dialog and displaying it
        alertDialog.show();
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView brandName,modelName,distance,type,date, expiryDate;


        CircleImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.text_brand);
            modelName = itemView.findViewById(R.id.text_model);
            distance = itemView.findViewById(R.id.text_distance);
            type = itemView.findViewById(R.id.text_type);
            date = itemView.findViewById(R.id.text_date);
            expiryDate = itemView.findViewById(R.id.expiry_date);
            imageView = itemView.findViewById(R.id.image_view_shoe);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    GetUserGearModel getItem(int id) {
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
