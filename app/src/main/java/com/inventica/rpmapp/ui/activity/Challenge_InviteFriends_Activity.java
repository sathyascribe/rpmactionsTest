package com.inventica.rpmapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.Music.MusicMainActivity;
import com.inventica.rpmapp.ui.activity.Music.PlayList_AddSongsActivity;
import com.inventica.rpmapp.ui.adapter.InviteFriendsAdapter;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.NonScrollListView;

import org.openapitools.client.model.AddPlaylistModel;
import org.openapitools.client.model.ListUserModel;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.ParticipantsdetailsModel;
import org.openapitools.client.model.StringApiResponse;
import org.openapitools.client.model.UpdateSongsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.sql.DriverManager.println;

public class Challenge_InviteFriends_Activity extends AppCompatActivity {

    private ListView listview_invite;
    private InviteFriendsAdapter adapter;
    ProgressDialog dialog;
    private Context mContext;
    ArrayList<ListUserModel> userModelArrayList;
    Button invite_frnd_btn;
    String challengeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends_);
        mContext = this;

        listview_invite = (NonScrollListView) findViewById(R.id.listview_invite);
        invite_frnd_btn = (Button) findViewById(R.id.invite_frnd_btn);

        dialog = new ProgressDialog(mContext);

        challengeId =  getIntent().getExtras().getString("challengeId");
        userModelArrayList = new ArrayList<ListUserModel>();

        getMylistOfChallenge();


        invite_frnd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Long userId = 0,userChallengeId,InvitestatusId;
                int size = userModelArrayList.size();
                Log.e("size", "size=" + String.valueOf(size));
                ArrayList<Integer> userlist=new ArrayList<>();
                ParticipantsdetailsModel participantsdetailsModel=new ParticipantsdetailsModel();
                for (int i = 0; i < size; i++) {
                    ListUserModel listUserModel = userModelArrayList.get(i);
                    if (listUserModel.getIsConnected()) {
                        Log.e("tag", "check box true");
                      //  userId=listUserModel.getInviteUserId();
                        participantsdetailsModel.setChallengeId(Long.valueOf(challengeId));
                        participantsdetailsModel.setInviteStatusId(Long.valueOf(1));
                        participantsdetailsModel.setInviteUserContactNumber(listUserModel.getInviteUserContactNumber());
                        participantsdetailsModel.setInviteUserEmailId(listUserModel.getInviteUserEmailId());
                        participantsdetailsModel.setInviteUserName(listUserModel.getInviteUserName());
                        participantsdetailsModel.setUserId(listUserModel.getInviteUserId());

                        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesAddListInviteFriendsForChallengePost(participantsdetailsModel).enqueue(new Callback<StringApiResponse>() {
                            @Override
                            public void onResponse(Call<StringApiResponse> call, Response<StringApiResponse> response) {
                                Log.e("tag", "response==" + response.body().getMessage());

                                dialog.dismiss();
                                //  if(response.code()==200){
                                if (response.body().getStatusCode()==200) {

                                    Intent intent = new Intent(Challenge_InviteFriends_Activity.this, MusicMainActivity.class);

                                    startActivity(intent);
                                    //startActivity(new Intent(getActivity(), Complited_challengeFragment.class));
                                    Toast.makeText(Challenge_InviteFriends_Activity.this, "Invite sent is successfull.", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(Challenge_InviteFriends_Activity.this, response.message(), Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<StringApiResponse> call, Throwable t) {
                                println("register error ${t.message}");
                                dialog.dismiss();
                            }
                        });
                    }
//                    Log.e("tag","songlist id=="+songlist.get(i).toString());
                }
                Log.e("tag","userlist.size=="+userlist.size());

                Log.e("tag","userModelArrayList=="+userModelArrayList.size());



            }
        });


    }
    public void getMylistOfChallenge(){

        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetListChallengeUserListGet(Long.valueOf(challengeId)).enqueue(new Callback<List<ListUserModel>>(){
            @Override
            public void onResponse(Call<List<ListUserModel>> call, Response<List<ListUserModel>> response) {
                Log.e("GetMyChallengeModel:", response.message());
                if(response.isSuccessful())
                {


                    List<ListUserModel> getchallengelist_obj = response.body();

                    ListUserModel[] challengelist_arrayObj= new ListUserModel[getchallengelist_obj.size()];

                  //  challengeModelList = new ArrayList<>();
                    for(int i=0;i<getchallengelist_obj.size();i++){
                        ListUserModel inner_challengelst= new ListUserModel();
                        inner_challengelst.setInviteUserContactNumber(getchallengelist_obj.get(i).getInviteUserContactNumber());
                        inner_challengelst.setInviteUserEmailId(getchallengelist_obj.get(i).getInviteUserEmailId());
                        inner_challengelst.setInviteUserName(getchallengelist_obj.get(i).getInviteUserName());
                        inner_challengelst.setInviteUserId(getchallengelist_obj.get(i).getInviteUserId());
                        inner_challengelst.setInviteUserProfilePhoto(getchallengelist_obj.get(i).getInviteUserProfilePhoto());
                        inner_challengelst.setGroupName(getchallengelist_obj.get(i).getGroupName());
                        inner_challengelst.setGroupId(getchallengelist_obj.get(i).getGroupId());
                        inner_challengelst.setIsConnected(getchallengelist_obj.get(i).getIsConnected());

                        challengelist_arrayObj[i]=inner_challengelst;
                        userModelArrayList.add(inner_challengelst);
                    }
                    adapter = new InviteFriendsAdapter(Challenge_InviteFriends_Activity.this,userModelArrayList);
                    listview_invite.setAdapter(adapter);

                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<ListUserModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(Challenge_InviteFriends_Activity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
}