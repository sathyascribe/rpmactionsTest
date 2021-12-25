package com.inventica.rpmapp.ui.fragment;

import android.content.Context;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.SignInActivity;
import com.inventica.rpmapp.ui.activity.Yourself_MeasurementActivity;
import com.inventica.rpmapp.ui.adapter.CustomAdapter;
import com.inventica.rpmapp.ui.modles.RpmModule;
import com.inventica.rpmapp.ui.modles.itemModel;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.ChallengeStatusModel;
import org.openapitools.client.model.DistanceModel;
import org.openapitools.client.model.GetMyChallengeModel;
import org.openapitools.client.model.HeightModel;
import org.openapitools.client.model.WeightModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChallengesFragment extends Fragment {

    private ChallengesFragment galleryViewModel;
    String[] mobileArray = {"Notification","Manage Permissions","Privacy Policy","Terms of Use",
            "About","Send us Feedback"};
    boolean[] listImages={true, true, true, true, true,true};

    String name[] = {"chocolate", "Aubergine", "Banana", "Burger", "Cake", "Ice Cream", "Tomato", "Watermelon"};
    ArrayList<itemModel> arrayList;
    CustomAdapter adapter1;
    int[] images = {R.drawable.challenge1, R.drawable.challenge2,R.drawable.challenge3,R.drawable.challenge4};

    ListView listView;
    ListAdapterMyChallenges_frg lAdapter;
    String[] version ;//= {"10k Mile challenge", "20k Mile challenge"};

    String[] versionNumber,remaining_days,startTime,endTime,participants;// = {"10k Mile", "20k Mile"};
    int[] images2 = {R.drawable.challenge3, R.drawable.challenge4,R.drawable.challenge5};

    ListView listView2;
    ListAdapterChallenges_frg lAdapter2;
    String[] version2 = {"10k Mile challenge", "20k Mile challenge", "20k Mile challenge"};

    String[] versionNumber2 = {"10k Mile", "20k Mile","20k Mile"};

    Button btn_challenge;
    List<DistanceModel> DistanceModelList;
    List<GetMyChallengeModel> challengeModelList;
    List<ChallengeStatusModel> ChallengeStatuslList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_challenges_temp, container, false);

       /* final ImageView imageView = (ImageView) root.findViewById(R.id.background_img);
        AppBarLayout appBarLayout = (AppBarLayout) root.findViewById(R.id.toolbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Matrix matrix = new Matrix(imageView.getImageMatrix());

                //get image's width and height
                final int dwidth = imageView.getDrawable().getIntrinsicWidth();
                final int dheight = imageView.getDrawable().getIntrinsicHeight();

                //get view's width and height
                final int vwidth = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                int vheight = imageView.getHeight() - imageView.getPaddingTop() - imageView.getPaddingBottom();

                float scale;
                float dx = 0, dy = 0;
                float parallaxMultiplier = ((CollapsingToolbarLayout.LayoutParams) imageView.getLayoutParams()).getParallaxMultiplier();

                //maintain the image's aspect ratio depending on offset
                if (dwidth * vheight > vwidth * dheight) {
                    vheight += (verticalOffset); //calculate view height depending on offset
                    scale = (float) vheight / (float) dheight; //calculate scale
                    dx = (vwidth - dwidth * scale) * 0.5f; //calculate x value of the center point of scaled drawable
                    dy = -verticalOffset * (1 - parallaxMultiplier); //calculate y value by compensating parallaxMultiplier
                } else {
                    scale = (float) vwidth / (float) dwidth;
                    dy = (vheight - dheight * scale) * 0.5f;
                }

                int currentWidth = Math.round(scale * dwidth); //calculate current intrinsic width of the drawable

                if (vwidth <= currentWidth) { //compare view width and drawable width to decide, should we scale more or not
                    matrix.setScale(scale, scale);
                    matrix.postTranslate(Math.round(dx), Math.round(dy));
                    imageView.setImageMatrix(matrix);
                }
            }
        });*/
        listView=(ListView) root.findViewById(R.id.challeng_list);
        listView2=(ListView) root.findViewById(R.id.join_list);
        btn_challenge = (Button) root.findViewById(R.id.btn_challenge);

        btn_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Fragment  fragment2 = new Create_ChallengesFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();               }
        });
/*        dataModels= new ArrayList<>();

        dataModels.add(new RpmModule("Sandra Adams", "sent you connection"));
        dataModels.add(new RpmModule("Imma jones", "Has invite you to participate in challenge"));
        dataModels.add(new RpmModule("Mark Smith", "The membership rules"));

        adapter= new CustomAdapterNotification(dataModels,getContext());
        */

        getMylistOfChallenge();

//        Log.e("tag","challengeModelList"+challengeModelList.get(0).getChallengeName());

        lAdapter2 = new ListAdapterChallenges_frg(getActivity(), version2, versionNumber2, images2);
        listView2.setAdapter(lAdapter2);
        return root;
    }
    public void getMylistOfChallenge(){

        GetAllMeasureDistance();
        GetAllChallengeStatus();

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
                    for(int j=0;j<challengeModelList.size();j++){

                        Log.e("tag","challengeModelList"+challengeModelList.get(j).getChallengeName());


                        for(int k=0;k<DistanceModelList.size();k++){
                            Log.e("tag","getChallengeId="+challengeModelList.get(j).getDistanceMeasurementId());
                            Log.e("tag","getId="+DistanceModelList.get(k).getId());

                            if(challengeModelList.get(j).getDistanceMeasurementId()==DistanceModelList.get(k).getId()){
                                Log.e("tag","getDistanceMeasurementName"+DistanceModelList.get(k).getDistanceMeasurementName());

                                versionNumber[j]=challengeModelList.get(j).getDistance()+"  "+DistanceModelList.get(k).getDistanceMeasurementName();
                            }
                        }
                        version[j]=challengeModelList.get(j).getChallengeName();
                        remaining_days[j]= String.valueOf(challengeModelList.get(j).getRemainingDate());
                        participants[j]= String.valueOf(challengeModelList.get(j).getTotalParticipantsCount());
                        startTime[j]= String.valueOf(challengeModelList.get(j).getChallengeStartTime());
                        endTime[j]= String.valueOf(challengeModelList.get(j).getChallengeEndTime());

                    }
                    Log.e("tag","version="+version);
                    Log.e("tag","versionNumber="+versionNumber.toString());
                    lAdapter = new ListAdapterMyChallenges_frg(getActivity(), version, versionNumber, images,remaining_days,participants,startTime,endTime);
                    listView.setAdapter(lAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<GetMyChallengeModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                //   login_progressDoalog.dismiss();
                Toast.makeText(getActivity(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }

}


class ListAdapterChallenges_frg extends BaseAdapter {

    Context context;
    private final String [] values;
    private final String [] numbers;
    private final int [] images;

    public ListAdapterChallenges_frg(Context context, String [] values, String [] numbers, int [] images){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.values = values;
        this.numbers = numbers;
        this.images = images;
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
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.rpmimage_iv);
            viewHolder.days_tv = (TextView)convertView.findViewById(R.id.days_remaining_tv);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(values[position]);
        viewHolder.txtVersion.setText(numbers[position]);

        viewHolder.icon.setImageResource(images[position]);

        viewHolder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new JoinChallengeFragment();
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


            }
        });
        return convertView;
    }

    private static class ViewHolder {

        TextView txtName;
        TextView txtVersion,days_tv;
        ImageView icon;

    }

}
class ListAdapterMyChallenges_frg extends BaseAdapter {

    Context context;
    private final String [] values;
    private final String [] numbers;
    private final int [] images;
    private final String[] remainingDays,participents,startTime,endTime;

    public ListAdapterMyChallenges_frg(Context context, String [] values, String [] numbers, int [] images,String [] remainingDays,String [] participents,String [] startTime,String [] endTime){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.values = values;
        this.numbers = numbers;
        this.images = images;
        this.remainingDays = remainingDays;
        this.participents = participents;
        this.startTime = startTime;
        this.endTime = endTime;
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
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.rpmimage_iv);
            viewHolder.days_tv = (TextView)convertView.findViewById(R.id.days_remaining_tv);
            viewHolder.participents_tv = (TextView)convertView.findViewById(R.id.participents_tv);
            viewHolder.startTime_tv = (TextView)convertView.findViewById(R.id.startTime_tv);
            viewHolder.endTime_tv = (TextView)convertView.findViewById(R.id.endTime_tv);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtName.setText(values[position]);
        viewHolder.txtVersion.setText(numbers[position]);
        viewHolder.icon.setImageResource(images[position]);
        viewHolder.days_tv.setText(remainingDays[position]+" Remaining Days");
        viewHolder.participents_tv.setText(participents[position]);
        viewHolder.startTime_tv.setText(startTime[position]);
        viewHolder.endTime_tv.setText(endTime[position]);

        viewHolder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment2 = new ChallengeDetailsFragment();
                /*FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
                Bundle args = new Bundle();
                args.putString("ChallengeName",viewHolder.txtName.getText().toString());
                args.putString("Distance", viewHolder.txtVersion.getText().toString());
                args.putString("remainingDays", viewHolder.days_tv.getText().toString());
                args.putString("participents", viewHolder.participents_tv.getText().toString());
                args.putString("startDate", viewHolder.startTime_tv.getText().toString());
                args.putString("endDate", viewHolder.endTime_tv.getText().toString());
                fragment2.setArguments(args);
                FragmentTransaction mFragmentTransaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.nav_host_fragment, fragment2).commit();// mFragmentTransaction.addToBackStack(null).commit();


            }
        });
        return convertView;
    }

    private static class ViewHolder {

        TextView txtName;
        TextView txtVersion,days_tv;
        TextView participents_tv,startTime_tv,endTime_tv;
        ImageView icon;

    }

}
