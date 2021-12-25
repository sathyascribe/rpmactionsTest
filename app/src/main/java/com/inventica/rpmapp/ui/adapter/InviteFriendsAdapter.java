package com.inventica.rpmapp.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.inventica.rpmapp.R;

import org.openapitools.client.model.ListUserModel;

import java.util.ArrayList;


public class InviteFriendsAdapter extends BaseAdapter {

    public ArrayList<ListUserModel> listUserModels;
    Activity activity;
  //  private ArrayList<ProjectStatusActivityModel2> mDisplayedValues = null;

    public InviteFriendsAdapter(Activity activity, ArrayList<ListUserModel> listUserModels) {
        super();
        this.activity = activity;
        this.listUserModels = listUserModels;
    }

    @Override
    public int getCount() {
        //return projList.size();
        return listUserModels.size();
    }

    @Override
    public ListUserModel getItem(int position) {

        //return projList.get(position);
        return listUserModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView imageViewCheck;
        TextView textName, textEmail,imageView;
        ConstraintLayout constraintLayout;
        CheckBox mStatus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.card_view_invite_friends, null);
            holder = new ViewHolder();

            holder.constraintLayout = convertView.findViewById(R.id.constraintLayout);
            holder.imageView = convertView.findViewById(R.id.imageProfile);
            holder.textEmail = convertView.findViewById(R.id.textEmail);
            holder.textName = convertView.findViewById(R.id.textName);
            holder.imageViewCheck = convertView.findViewById(R.id.imageViewCheck);
            holder.mStatus = (CheckBox) convertView.findViewById(R.id.check_status);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ListUserModel item = listUserModels.get(position);

        if(item.getInviteUserName()!=null) {
            holder.textName.setText(item.getInviteUserName().toString());
        }
        if(item.getInviteUserEmailId()!=null) {
            holder.textEmail.setText(item.getInviteUserEmailId().toString());
        }


        /*if(item.getIsConnected()) {
            holder.mStatus.setChecked(true);
            //holder.mStatus.setSelected(true);
        }else{
            holder.mStatus.setChecked(false);
        }*/
       /* if(item.getIsConnected().toString().equals("0")) {
            holder.mStatus.setChecked(false);
            //holder.mStatus.setSelected(false);
        }*/

        if(item.getIsConnected()) {
            holder.mStatus.setChecked(true);
            //holder.mStatus.setSelected(true);
        }else {
            holder.mStatus.setChecked(false);
        }



        holder.mStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("insidelistview","listview clicked");

                if(isChecked) {
                    Log.d("ischhecked","true");
                    holder.mStatus.setChecked(true);
                    //holder.mStatus.setEnabled(true);
                    listUserModels.get(position).setIsConnected(true);
                }else{
                    Log.d("ischhecked","false");
                    holder.mStatus.setChecked(false);
                    listUserModels.get(position).setIsConnected(false);
                }
                //notifyDataSetChanged();
                //holder.mStatus.setChecked(true);
                //holder.mStatus.setSelected(true);

            }
        });




  /*      Button btn_selectAll;
        btn_selectAll = (Button)
        btn_selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("lview.getCount()iss", String.valueOf(lview.getCount()));
                for (int i = 0; i < lview.getCount(); i++) {
                    View vw = lview.getAdapter().getView(i,null,null);
                    CheckBox checkBox = (CheckBox) vw.findViewById(R.id.check_status);
                    Log.d("checkboxstatusissss", String.valueOf(checkBox.isChecked()));
                    checkBox.setChecked(true);
                    Log.d("checkboxstatusissss2", String.valueOf(checkBox.isChecked()));
                    lview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });*/


        return convertView;
    }


    /*public void filter(String charText, ArrayList<Class_UnpaidStudList> feesList) {
        //charText = charText.toLowerCase(Locale.getDefault());
        //Log.d("charTextissss",charText);
        this.feesUnPaidList.clear();

     *//*   for(FeesUnpaidModel feesUnpaidModel : feesList){
            String  stdName = feesUnpaidModel.getStudent_name().toString();
            //Log.d("StudeNameissssss",stdName);
        }*//*

        if(charText!=null) {
            if(feesList!=null) {
                if (charText.isEmpty() || charText.length() == 0) {
                    this.feesUnPaidList.addAll(feesList);
                } else {
                    for (Class_UnpaidStudList wp : feesList) {
                        //Log.d("GetCollegeNameissss",wp.getCollege_name().toLowerCase((Locale.getDefault())));
                       *//* if (wp.getCollege_name().toLowerCase(Locale.getDefault()).contains(charText) || wp.getLead_id().toLowerCase(Locale.getDefault()).contains(charText) || wp.getPhone_number().toLowerCase(Locale.getDefault()).contains(charText) || wp.getStudent_name().toLowerCase(Locale.getDefault()).contains(charText) || wp.getPhone_number().toLowerCase(Locale.getDefault()).contains(charText)) {
                            this.feesUnPaidList.add(wp);
                        }*//*
                        if  ((wp.getCollegeName()!=null && wp.getCollegeName().toLowerCase().contains(charText.toLowerCase())) || ( wp.getLead_id()!=null && wp.getLead_id().toLowerCase().contains(charText.toLowerCase())) || ( wp.getMobileNo()!=null && wp.getMobileNo().toLowerCase().contains(charText.toLowerCase())) || ( wp.getStudentName()!=null && wp.getStudentName().toLowerCase().contains(charText.toLowerCase()) ) || ( wp.getMobileNo()!=null && wp.getMobileNo().toLowerCase().contains(charText.toLowerCase()))) {
                            this.feesUnPaidList.add(wp);
                        }

                    }
                }
                notifyDataSetChanged();
            }
        }
    }*/
}
