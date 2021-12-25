package com.inventica.rpmapp.ui.activity;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;
import com.inventica.rpmapp.ui.utils.Utils;

import org.openapitools.client.model.ChallengeStatusModel;
import org.openapitools.client.model.DistanceModel;
import org.openapitools.client.model.GetMyChallengeModel;
import org.openapitools.client.model.GetUserGearModel;
import org.openapitools.client.model.JoinChallenegeModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.ContextCompat.startActivity;

public class ChallengeActivity extends AppCompatActivity {

    int[] images;//= {R.drawable.challenge1, R.drawable.challenge2,R.drawable.challenge3,R.drawable.challenge4,R.drawable.challenge4};

    ListView listView;
    ListAdapterMyChallenges lAdapter;
    String[] version ;//= {"10k Mile challenge", "20k Mile challenge"};

    String[] versionNumber,remaining_days,startTime,endTime,participants,challengeId;// = {"10k Mile", "20k Mile"};
    int[] images2 = {R.drawable.challenge3, R.drawable.challenge4,R.drawable.challenge5};

    ListView listView2;
    ListAdapterChallenges lAdapter2;
    String[] version2 = {"10k Mile challenge", "20k Mile challenge", "20k Mile challenge"};

    String[] versionNumber2 = {"10k Mile", "20k Mile","20k Mile"};

    Button btn_challenge;
    List<DistanceModel> DistanceModelList;
    List<GetMyChallengeModel> challengeModelList;
    List<ChallengeStatusModel> ChallengeStatuslList;
    List<JoinChallenegeModel> joinChallenegeModelList;
    private Context mContext;

    LinearLayout fullscreen_ll;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_challenges_temp);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

      //  Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setTitle("Challenges");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout coll_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        coll_toolbar.setTitle("Challenges");
        coll_toolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        coll_toolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        mContext = this;
        getAllView();
        dialog = new ProgressDialog(mContext);
        fullscreen_ll.setVisibility(View.VISIBLE);
        if (Utils.isNetworkConnectedMainThred(getApplicationContext())) {
        getMylistOfChallenge();
            getJoinlistOfChallenge();
        }else {
            Toast.makeText(getApplicationContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }

    }

    private void getAllView() {
        listView=(ListView) findViewById(R.id.challeng_list);
        listView2=(ListView) findViewById(R.id.join_list);
        btn_challenge = (Button) findViewById(R.id.btn_challenge);
        fullscreen_ll = (LinearLayout) findViewById(R.id.fullscreen_ll);

        btn_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               /* Fragment fragment2 = new Create_ChallengesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
              //  fullscreen_ll.setVisibility(View.GONE);
            /*    androidx.fragment.app.FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
// Replace the content of the container
                fts.replace(R.id.nav_host_fragment, new Create_ChallengesFragment());
// Append this transaction to the backstack
                fts.addToBackStack("optional tag");
// Commit the changes
                fts.commit();*/

                Intent intent = new Intent(ChallengeActivity.this, Create_ChallengeActivity.class);
                startActivity(intent);
            }
        });

    }

    public void getMylistOfChallenge(){

        GetAllMeasureDistance();
        GetAllChallengeStatus();
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetMylistOfChallengeGet().enqueue(new Callback<List<GetMyChallengeModel>>(){
            @Override
            public void onResponse(Call<List<GetMyChallengeModel>> call, Response<List<GetMyChallengeModel>> response) {
                Log.e("GetMyChallengeModel:", response.message());
                if(response.isSuccessful())
                {


                    List<GetMyChallengeModel> getchallengelist_obj = response.body();

                    GetMyChallengeModel[] challengelist_arrayObj= new GetMyChallengeModel[getchallengelist_obj.size()];

                    challengeModelList = new ArrayList<>();
                    for(int i=0;i<getchallengelist_obj.size();i++){
                        GetMyChallengeModel inner_challengelst= new GetMyChallengeModel();
                        inner_challengelst.setChallengeName(getchallengelist_obj.get(i).getChallengeName());
                        inner_challengelst.setChallengeId(getchallengelist_obj.get(i).getChallengeId());
                        inner_challengelst.setChallengeDate(getchallengelist_obj.get(i).getChallengeDate());
                        inner_challengelst.setChallengeEndTime(getchallengelist_obj.get(i).getChallengeEndTime());
                        inner_challengelst.setChallengeStartTime(getchallengelist_obj.get(i).getChallengeStartTime());
                        inner_challengelst.setChallengeStatus(getchallengelist_obj.get(i).getChallengeStatus());
                        inner_challengelst.setDistance(getchallengelist_obj.get(i).getDistance());
                        inner_challengelst.setDistanceMeasurementId(getchallengelist_obj.get(i).getDistanceMeasurementId());
                        inner_challengelst.setEventType(getchallengelist_obj.get(i).getEventType());
                        inner_challengelst.setRemainingDate(getchallengelist_obj.get(i).getRemainingDate());
                        inner_challengelst.setThemeName(getchallengelist_obj.get(i).getThemeName());
                        inner_challengelst.setThemesId(getchallengelist_obj.get(i).getThemesId());
                        inner_challengelst.setTotalParticipantsCount(getchallengelist_obj.get(i).getTotalParticipantsCount());
                        challengelist_arrayObj[i]=inner_challengelst;
                        challengeModelList.add(inner_challengelst);
                    }
                    // if(challengeModelList.get(0).)
                    version=new String[challengeModelList.size()];
                    versionNumber=new String[challengeModelList.size()];
                    remaining_days=new String[challengeModelList.size()];
                    participants=new String[challengeModelList.size()];
                    startTime=new String[challengeModelList.size()];
                    endTime=new String[challengeModelList.size()];
                    images = new int[challengeModelList.size()];
                    challengeId = new String[challengeModelList.size()];
                    for(int j=0;j<challengeModelList.size();j++){

                        Log.e("tag","challengeModelList"+challengeModelList.get(j).getChallengeName());


                        for(int k=0;k<DistanceModelList.size();k++){
                            Log.e("tag","getChallengeId="+challengeModelList.get(j).getDistanceMeasurementId());
                            Log.e("tag","getId="+DistanceModelList.get(k).getId());

                            if(challengeModelList.get(j).getDistanceMeasurementId()==DistanceModelList.get(k).getId()){
                                Log.e("tag","getDistanceMeasurementName"+DistanceModelList.get(k).getDistanceMeasurementName());
                                Log.e("tag","getDistance"+challengeModelList.get(j).getDistance());

                                versionNumber[j]=challengeModelList.get(j).getDistance()+"  "+DistanceModelList.get(k).getDistanceMeasurementName();
                            }
                        }
                        version[j]=challengeModelList.get(j).getChallengeName();
                        remaining_days[j]= String.valueOf(challengeModelList.get(j).getRemainingDate());
                        participants[j]= String.valueOf(challengeModelList.get(j).getTotalParticipantsCount());
                        startTime[j]= String.valueOf(challengeModelList.get(j).getChallengeStartTime());
                        endTime[j]= String.valueOf(challengeModelList.get(j).getChallengeEndTime());
                        challengeId[j]=String.valueOf(challengeModelList.get(j).getChallengeId());
                        images[j]=R.drawable.challenge1;
                    }
                    Log.e("tag","version="+version);
                    Log.e("tag","versionNumber="+versionNumber.toString());
                    lAdapter = new ListAdapterMyChallenges(ChallengeActivity.this, version, versionNumber, images,remaining_days,participants,startTime,endTime,challengeId);
                    listView.setAdapter(lAdapter);
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<GetMyChallengeModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(ChallengeActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void GetAllMeasureDistance(){

        Rest_Adapter.getDropDownApi().apiDropDownGetAllMeasureDistanceGet().enqueue(new Callback<List<DistanceModel>>(){
            @Override
            public void onResponse(Call<List<DistanceModel>> call, Response<List<DistanceModel>> response) {
                if(response.isSuccessful())
                {


                    List<DistanceModel> getDistancelist_obj = response.body();
                    // List<Country> userlist_obj=response.body().;
                    Log.e("getDistancelist_obj", String.valueOf(getDistancelist_obj.get(0).getDistanceMeasurementName()));

                    DistanceModel[] DistanceModellist_arrayObj= new DistanceModel[getDistancelist_obj.size()];

                    DistanceModelList = new ArrayList<>();
                    for(int i=0;i<getDistancelist_obj.size();i++){
                        DistanceModel inner_countrylst= new DistanceModel();
                        inner_countrylst.setDistanceMeasurementName(getDistancelist_obj.get(i).getDistanceMeasurementName());
                        inner_countrylst.setId(getDistancelist_obj.get(i).getId());
                        DistanceModellist_arrayObj[i]=inner_countrylst;
                        DistanceModelList.add(inner_countrylst);
                        Log.e("getDistancelist_obj", String.valueOf(DistanceModelList.get(i).getDistanceMeasurementName()));

                    }


                    //login_progressDoalog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<DistanceModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                //   login_progressDoalog.dismiss();
                Toast.makeText(ChallengeActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void GetAllChallengeStatus(){

        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetAllChallengeStatusGet().enqueue(new Callback<List<ChallengeStatusModel>>(){
            @Override
            public void onResponse(Call<List<ChallengeStatusModel>> call, Response<List<ChallengeStatusModel>> response) {
                Log.e("ChallengeStatusModel:", response.message());
                if(response.isSuccessful())
                {


                    List<ChallengeStatusModel> getCountrylist_obj = response.body();

                    ChallengeStatusModel[] countrylist_arrayObj= new ChallengeStatusModel[getCountrylist_obj.size()];

                    ChallengeStatuslList = new ArrayList<>();
                    for(int i=0;i<getCountrylist_obj.size();i++){
                        ChallengeStatusModel inner_countrylst= new ChallengeStatusModel();
                        inner_countrylst.setChallengeStatus(getCountrylist_obj.get(i).getChallengeStatus());
                        inner_countrylst.setId(getCountrylist_obj.get(i).getId());
                        countrylist_arrayObj[i]=inner_countrylst;
                        ChallengeStatuslList.add(inner_countrylst);
                        Log.e("ChallengeStatuslList", String.valueOf(ChallengeStatuslList.get(i).getChallengeStatus()));

                    }

                }
            }

            @Override
            public void onFailure(Call<List<ChallengeStatusModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                //   login_progressDoalog.dismiss();
                Toast.makeText(ChallengeActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getJoinlistOfChallenge(){

        GetAllMeasureDistance();
        GetAllChallengeStatus();
        dialog.setMessage("Loading..");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetMyListOfJoinChallengeGet().enqueue(new Callback<List<JoinChallenegeModel>>(){
            @Override
            public void onResponse(Call<List<JoinChallenegeModel>> call, Response<List<JoinChallenegeModel>> response) {
                Log.e("GetMyChallengeModel:", response.message());
                if(response.isSuccessful())
                {


                    List<JoinChallenegeModel> getchallengelist_obj = response.body();

                    JoinChallenegeModel[] challengelist_arrayObj= new JoinChallenegeModel[getchallengelist_obj.size()];

                    joinChallenegeModelList = new ArrayList<>();
                    for(int i=0;i<getchallengelist_obj.size();i++){
                        JoinChallenegeModel inner_challengelst= new JoinChallenegeModel();
                        inner_challengelst.setChallengeName(getchallengelist_obj.get(i).getChallengeName());
                        inner_challengelst.setChallengeId(getchallengelist_obj.get(i).getChallengeId());
                        inner_challengelst.setChallengeDate(getchallengelist_obj.get(i).getChallengeDate());
                        inner_challengelst.setChallengeEndTime(getchallengelist_obj.get(i).getChallengeEndTime());
                        inner_challengelst.setChallengeStartTime(getchallengelist_obj.get(i).getChallengeStartTime());
                     //   inner_challengelst.setChallengeStatus(getchallengelist_obj.get(i).getChallengeStatus());
                        inner_challengelst.setDistance(getchallengelist_obj.get(i).getDistance());
                        inner_challengelst.setDistanceMeasurementId(getchallengelist_obj.get(i).getDistanceMeasurementId());
                     //   inner_challengelst.setEventType(getchallengelist_obj.get(i).getEventType());
                        inner_challengelst.setRemainingDate(getchallengelist_obj.get(i).getRemainingDate());
                     //   inner_challengelst.setThemeName(getchallengelist_obj.get(i).getThemeName());
                     //   inner_challengelst.setThemesId(getchallengelist_obj.get(i).getThemesId());
                        inner_challengelst.setTotalParticipantsCount(getchallengelist_obj.get(i).getTotalParticipantsCount());
                        challengelist_arrayObj[i]=inner_challengelst;
                        joinChallenegeModelList.add(inner_challengelst);
                    }
                    // if(challengeModelList.get(0).)
                    version=new String[joinChallenegeModelList.size()];
                    versionNumber=new String[joinChallenegeModelList.size()];
                    remaining_days=new String[joinChallenegeModelList.size()];
                    participants=new String[joinChallenegeModelList.size()];
                    startTime=new String[joinChallenegeModelList.size()];
                    endTime=new String[joinChallenegeModelList.size()];
                    images = new int[joinChallenegeModelList.size()];
                    challengeId = new String[joinChallenegeModelList.size()];
                    for(int j=0;j<joinChallenegeModelList.size();j++){

                        Log.e("tag","joinChallenegeModelList"+joinChallenegeModelList.get(j).getChallengeName());


                        for(int k=0;k<DistanceModelList.size();k++){
                            Log.e("tag","getChallengeId="+joinChallenegeModelList.get(j).getDistanceMeasurementId());
                            Log.e("tag","getId="+DistanceModelList.get(k).getId());

                            if(joinChallenegeModelList.get(j).getDistanceMeasurementId()==DistanceModelList.get(k).getId()){
                                Log.e("tag","getDistanceMeasurementName"+DistanceModelList.get(k).getDistanceMeasurementName());
                                Log.e("tag","getDistance"+joinChallenegeModelList.get(j).getDistance());

                                versionNumber[j]=joinChallenegeModelList.get(j).getDistance()+"  "+DistanceModelList.get(k).getDistanceMeasurementName();
                            }
                        }
                        version[j]=joinChallenegeModelList.get(j).getChallengeName();
                        remaining_days[j]= String.valueOf(joinChallenegeModelList.get(j).getRemainingDate());
                        participants[j]= String.valueOf(joinChallenegeModelList.get(j).getTotalParticipantsCount());
                        startTime[j]= String.valueOf(joinChallenegeModelList.get(j).getChallengeStartTime());
                        endTime[j]= String.valueOf(joinChallenegeModelList.get(j).getChallengeEndTime());
                        challengeId[j]=String.valueOf(joinChallenegeModelList.get(j).getChallengeId());
                        images[j]=R.drawable.challenge1;
                    }
                    Log.e("tag","version="+version);
                    Log.e("tag","versionNumber="+versionNumber.toString());
                   /* lAdapter = new ListAdapterMyChallenges(ChallengeActivity.this, version, versionNumber, images,remaining_days,participants,startTime,endTime,challengeId);
                    listView.setAdapter(lAdapter);*/
                    lAdapter2 = new ListAdapterChallenges(ChallengeActivity.this, version, versionNumber, images,remaining_days,participants,startTime,endTime,challengeId);
                    listView2.setAdapter(lAdapter2);
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<JoinChallenegeModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                dialog.dismiss();
                Toast.makeText(ChallengeActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.noti_setting, menu);
       menu.findItem(R.id.nav_add).setVisible(false);
       menu.findItem(R.id.nav_search).setVisible(false);
       return true;
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.nav_notification) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

class ListAdapterChallenges extends BaseAdapter {

    Context context;
    private final String [] values;
    private final String [] numbers;
    private final int [] images;

/*    public ListAdapterChallenges(Context context, String [] values, String [] numbers, int [] images){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.values = values;
        this.numbers = numbers;
        this.images = images;
    }*/
    private final String[] remainingDays,participents,startTime,endTime,challengeId;

    public ListAdapterChallenges(Context context, String [] values, String [] numbers, int [] images,String [] remainingDays,String [] participents,String [] startTime,String [] endTime,String[] challengeId){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.values = values;
        this.numbers = numbers;
        this.images = images;
        this.remainingDays = remainingDays;
        this.participents = participents;
        this.startTime = startTime;
        this.endTime = endTime;
        this.challengeId = challengeId;
    }
    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.child_challengelist_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.Name_tv);
            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.distanceId_tv);
           /* viewHolder.icon = (ImageView) convertView.findViewById(R.id.rpmimage_iv);
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.days_tv = (TextView)convertView.findViewById(R.id.days_remaining_tv);
*/
            viewHolder.icon = (TextView) convertView.findViewById(R.id.rpmimage_iv1);
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.days_tv = (TextView)convertView.findViewById(R.id.days_remaining_tv);
            viewHolder.participents_tv = (TextView)convertView.findViewById(R.id.participents_tv);
            viewHolder.startTime_tv = (TextView)convertView.findViewById(R.id.startTime_tv);
            viewHolder.endTime_tv = (TextView)convertView.findViewById(R.id.endTime_tv);
            viewHolder.challengeId_tv = (TextView)convertView.findViewById(R.id.challengeId_tv);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        /*viewHolder.txtName.setText(values[position]);
        viewHolder.txtVersion.setText(numbers[position]);

        viewHolder.icon.setImageResource(images[position]);*/

        viewHolder.txtName.setText(values[position]);
        viewHolder.txtVersion.setText(numbers[position]);
        // viewHolder.icon.setImageResource(images[position]);
        viewHolder.icon.setText("10k");
        Drawable buttonDrawable = viewHolder.icon.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        //the color is a direct color int and not a color resource
        DrawableCompat.setTint(buttonDrawable, Color.RED);
        viewHolder.icon.setBackground(buttonDrawable);
        //viewHolder.icon.setBackgroundTintMode(Color.parseColor("#FF0000"));
        viewHolder.days_tv.setText(remainingDays[position]+" Remaining Days");
        viewHolder.participents_tv.setText(participents[position]);
        viewHolder.startTime_tv.setText(startTime[position]);
        viewHolder.endTime_tv.setText(endTime[position]);
        viewHolder.challengeId_tv.setText(challengeId[position]);

        viewHolder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Fragment fragment2 = new JoinChallengeFragment();
                Bundle args = new Bundle();
                args.putString("ChallengeName",viewHolder.txtName.getText().toString());
                args.putString("Distance", "100 Mi");
                args.putString("remainingDays", viewHolder.days_tv.getText().toString());
                // args.putString("participents", viewHolder.participents_tv.getText().toString());
                // args.putString("startDate", viewHolder.startTime_tv.getText().toString());
                //args.putString("endDate", viewHolder.endTime_tv.getText().toString());
                fragment2.setArguments(args);
                FragmentTransaction mFragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.nav_host_fragment, fragment2).commit();// mFragmentTransaction.addToBackStack(null).commit();
*/
                Intent intent = new Intent(context, JoinChallenge_Activity.class);
                intent.putExtra("ChallengeName",viewHolder.txtName.getText().toString());
                intent.putExtra("Distance", "100 Mi");
                intent.putExtra("remainingDays", viewHolder.days_tv.getText().toString());
                intent.putExtra("challengeId", viewHolder.challengeId_tv.getText().toString());
                /*intent.putExtra("participents", viewHolder.participents_tv.getText().toString());
                intent.putExtra("startDate", viewHolder.startTime_tv.getText().toString());
                intent.putExtra("endDate", viewHolder.endTime_tv.getText().toString());*/
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private static class ViewHolder {

        /*TextView txtName;
        TextView txtVersion,days_tv;
        ImageView icon;*/
        TextView txtName;
        TextView txtVersion,days_tv;
        TextView participents_tv,startTime_tv,endTime_tv,challengeId_tv;
        TextView icon;
    }

}
class ListAdapterMyChallenges extends BaseAdapter {

    Context context;
    private final String [] values;
    private final String [] numbers;
    private final int [] images;
    private final String[] remainingDays,participents,startTime,endTime,challengeId;

    public ListAdapterMyChallenges(Context context, String [] values, String [] numbers, int [] images,String [] remainingDays,String [] participents,String [] startTime,String [] endTime,String[] challengeId){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.values = values;
        this.numbers = numbers;
        this.images = images;
        this.remainingDays = remainingDays;
        this.participents = participents;
        this.startTime = startTime;
        this.endTime = endTime;
        this.challengeId = challengeId;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.child_challengelist_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.Name_tv);
            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.distanceId_tv);
          //  viewHolder.icon = (ImageView) convertView.findViewById(R.id.rpmimage_iv);
            viewHolder.icon = (TextView) convertView.findViewById(R.id.rpmimage_iv1);
            viewHolder.icon.setVisibility(View.VISIBLE);
            viewHolder.days_tv = (TextView)convertView.findViewById(R.id.days_remaining_tv);
            viewHolder.participents_tv = (TextView)convertView.findViewById(R.id.participents_tv);
            viewHolder.startTime_tv = (TextView)convertView.findViewById(R.id.startTime_tv);
            viewHolder.endTime_tv = (TextView)convertView.findViewById(R.id.endTime_tv);
            viewHolder.challengeId_tv = (TextView)convertView.findViewById(R.id.challengeId_tv);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(values[position]);
        viewHolder.txtVersion.setText(numbers[position]);
       // viewHolder.icon.setImageResource(images[position]);
        viewHolder.icon.setText("10k");
        Drawable buttonDrawable = viewHolder.icon.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        //the color is a direct color int and not a color resource
        DrawableCompat.setTint(buttonDrawable, Color.RED);
        viewHolder.icon.setBackground(buttonDrawable);
        //viewHolder.icon.setBackgroundTintMode(Color.parseColor("#FF0000"));
        viewHolder.days_tv.setText(remainingDays[position]+" Remaining Days");
        viewHolder.participents_tv.setText(participents[position]);
        viewHolder.startTime_tv.setText(startTime[position]);
        viewHolder.endTime_tv.setText(endTime[position]);
        viewHolder.challengeId_tv.setText(challengeId[position]);

        viewHolder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Fragment fragment2 = new ChallengeDetailsFragment();
//                /*FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.nav_host_fragment, fragment2);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();*/
//                Bundle args = new Bundle();
//                args.putString("ChallengeName",viewHolder.txtName.getText().toString());
//                args.putString("Distance", viewHolder.txtVersion.getText().toString());
//                args.putString("remainingDays", viewHolder.days_tv.getText().toString());
//                args.putString("participents", viewHolder.participents_tv.getText().toString());
//                args.putString("startDate", viewHolder.startTime_tv.getText().toString());
//                args.putString("endDate", viewHolder.endTime_tv.getText().toString());
//                fragment2.setArguments(args);
//                FragmentTransaction mFragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
//                mFragmentTransaction.replace(R.id.nav_host_fragment, fragment2).commit();// mFragmentTransaction.addToBackStack(null).commit();


                Intent intent = new Intent(context, ChallengeDetails_Activity.class);
                intent.putExtra("ChallengeName",viewHolder.txtName.getText().toString());
                intent.putExtra("Distance", viewHolder.txtVersion.getText().toString());
                intent.putExtra("remainingDays", viewHolder.days_tv.getText().toString());
                intent.putExtra("participents", viewHolder.participents_tv.getText().toString());
                intent.putExtra("startDate", viewHolder.startTime_tv.getText().toString());
                intent.putExtra("endDate", viewHolder.endTime_tv.getText().toString());
                intent.putExtra("challengeId", viewHolder.challengeId_tv.getText().toString());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    private static class ViewHolder {

        TextView txtName;
        TextView txtVersion,days_tv;
        TextView participents_tv,startTime_tv,endTime_tv,challengeId_tv;
        TextView icon;

    }

}