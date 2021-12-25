package com.inventica.rpmapp.ui.activity.calendar;

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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.InviteFriendsActivity;
import com.inventica.rpmapp.ui.activity.connection.MyListUserModel;
import com.inventica.rpmapp.ui.activity.connection.UserDetailsForInvitationConnection;

import org.openapitools.client.model.ListUserModel;

import java.util.ArrayList;



public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.ViewHolder> {

    private ArrayList<ListUserModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private static RadioButton lastChecked = null;
    private static int lastCheckedPos = 0;


    public FriendListAdapter(Context context, ArrayList<ListUserModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
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

        final ListUserModel model = mData.get(position);
//        Random rnd = new Random();
//        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));


//        public MyListUserModel(Long inviteUserId, String inviteUserName, String inviteUserContactNumber,
//                String inviteUserEmailId, String inviteUserProfilePhoto, Long groupId, String groupName, Boolean isConnected) {
        final MyListUserModel parcelableModel = new MyListUserModel(model.getInviteUserId(),model.getInviteUserName(),model.getInviteUserContactNumber(),
                model.getInviteUserEmailId(),model.getInviteUserProfilePhoto(),model.getGroupId(),model.getGroupName(),model.getIsConnected());

        holder.textName.setText(model.getInviteUserName());
        holder.textEmail.setText(model.getInviteUserEmailId());

//        if(model.getIsConnected())
//
//            holder.imageViewCheck.setVisibility(View.VISIBLE);
//        else holder.imageViewCheck.setVisibility(View.INVISIBLE);



        String name =    getAlphabetsOfTheName(model.getInviteUserName());
        holder.imageView.setText(name);
//         holder.imageView.setBackgroundColor(color);
//
        if(model.getIsSelected())

            holder.constraintLayout.setBackgroundColor(mInflater.getContext().getColor(R.color.view_color));
            holder.imageViewCheck.setVisibility(View.VISIBLE);
        {
            holder.constraintLayout.setBackgroundColor(mInflater.getContext().getColor(R.color.white));
            holder.imageViewCheck.setVisibility(View.INVISIBLE);
        }

       holder.constraintLayout.setOnClickListener(v -> {

           if(model.getIsSelected())
           {
               holder.constraintLayout.setBackgroundColor(mInflater.getContext().getColor(R.color.white));
              holder.imageViewCheck.setVisibility(View.INVISIBLE);
           }

           else {
               holder.constraintLayout.setBackgroundColor(mInflater.getContext().getColor(R.color.view_color));
              holder.imageViewCheck.setVisibility(View.VISIBLE);
           }
           mData.get(position).setIsSelected(!model.getIsSelected());

       //   ((InviteFriendsActivity)mInflater.getContext()).returnIntent(mData);

          // sendInvitation();
       });


    }

    private void sendInvitation() {


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
        return mData.size();
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
    ListUserModel getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(FriendListAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


}

