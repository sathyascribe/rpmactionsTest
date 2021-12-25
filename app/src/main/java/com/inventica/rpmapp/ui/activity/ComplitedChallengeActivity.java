package com.inventica.rpmapp.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.ChallengeStatusModel;
import org.openapitools.client.model.DistanceModel;
import org.openapitools.client.model.GetMyChallengeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplitedChallengeActivity extends AppCompatActivity {

    int[] images;// = {R.drawable.challenge1, R.drawable.challenge2,R.drawable.challenge3,R.drawable.challenge4};

    ListView listView;
    ListAdapterMyChallengescomplited lAdapter;
    String[] version ;//= {"10k Mile challenge", "20k Mile challenge"};

    String[] versionNumber,remaining_days,startTime,endTime,participants;// = {"10k Mile", "20k Mile"};
    int[] images2 = {R.drawable.challenge3, R.drawable.challenge4,R.drawable.challenge5};

    ListView listView2;
    ListAdapterComplitedChallenges lAdapter2;
    String[] version2 = {"10k Mile challenge", "20k Mile challenge", "20k Mile challenge"};

    String[] versionNumber2 = {"10k Mile", "20k Mile","20k Mile"};

    Button btn_challenge;
    List<DistanceModel> DistanceModelList;
    List<GetMyChallengeModel> challengeModelList;
    List<ChallengeStatusModel> ChallengeStatuslList;
    private Context mContext;

    LinearLayout ll_challenges,gridView_ll;
    GridView gridview_achivment;
    RadioGroup toggle_radiogroup;
    RadioButton challenge_rb,achievement_rb;
    public Integer[] mThumbIds = {
            R.drawable.badge1, R.drawable.badge2,
            R.drawable.badge3, R.drawable.badge4,
            R.drawable.badge5, R.drawable.badge6,
            R.drawable.badge7, R.drawable.badge8,
            R.drawable.badge9, R.drawable.badge10,
            R.drawable.badge11, R.drawable.badge12
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complited_challengeslist);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Challenges");

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        CollapsingToolbarLayout coll_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
//        coll_toolbar.setTitle("Challenges");
       // coll_toolbar.setCollapsedTitleTextAppearance(R.style.coll_toolbar_title);
       // coll_toolbar.setExpandedTitleTextAppearance(R.style.exp_toolbar_title);
        //coll_toolbar.setContentScrimColor(Color.GREEN);
        mContext = this;
        getAllView();
       // fullscreen_ll.setVisibility(View.VISIBLE);

        if(challenge_rb.isChecked())
        {
            ll_challenges.setVisibility(View.VISIBLE);
            gridView_ll.setVisibility(View.GONE);
            challenge_rb.setTextColor(Color.WHITE);
            achievement_rb.setTextColor(Color.parseColor("#e01c42"));
            getMylistOfChallenge();
            lAdapter2 = new ListAdapterComplitedChallenges(ComplitedChallengeActivity.this, version2, versionNumber2, images2);
            listView2.setAdapter(lAdapter2);
        }
        else
        {
            ll_challenges.setVisibility(View.GONE);
            gridView_ll.setVisibility(View.VISIBLE);
            achievement_rb.setTextColor(Color.WHITE);
            challenge_rb.setTextColor(Color.parseColor("#e01c42"));
            ImageAdapter myAdapter=new ImageAdapter(ComplitedChallengeActivity.this,mThumbIds);
            gridview_achivment.setAdapter(myAdapter);


        }

      //  gridview_achivment.setAdapter(new ImageAdapter(getApplicationContext()));
       /* toggle_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.challenge_rb:
                        fullscreen_ll.setVisibility(View.VISIBLE);
                        gridview_achivment.setVisibility(View.GONE);
                        getMylistOfChallenge();
                        lAdapter2 = new ListAdapterComplitedChallenges(ComplitedChallengeActivity.this, version2, versionNumber2, images2);
                        listView2.setAdapter(lAdapter2);
                        break;
                    case R.id.achievement_rb:
                        fullscreen_ll.setVisibility(View.GONE);
                        gridview_achivment.setVisibility(View.VISIBLE);

                        ImageAdapter myAdapter=new ImageAdapter(ComplitedChallengeActivity.this,mThumbIds);
                        gridview_achivment.setAdapter(myAdapter);
                        break;
                }
            }
        });
*/

    }

    private void getAllView() {
        listView=(ListView) findViewById(R.id.challeng_list);
        listView2=(ListView) findViewById(R.id.join_list);
     //   btn_challenge = (Button) findViewById(R.id.btn_challenge);
        ll_challenges = (LinearLayout) findViewById(R.id.ll_challenges);
        gridView_ll = (LinearLayout) findViewById(R.id.gridView_ll);
        gridview_achivment = (GridView) findViewById(R.id.gridview_achivment);
        toggle_radiogroup =(RadioGroup) findViewById(R.id.toggle);
        challenge_rb=(RadioButton) findViewById(R.id.challenge_rb);
        achievement_rb=(RadioButton) findViewById(R.id.achievement_rb);
    }

    public void onRadioButtonToggleClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.challenge_rb:
                if (checked) {

                    ll_challenges.setVisibility(View.VISIBLE);
                    gridView_ll.setVisibility(View.GONE);
                    challenge_rb.setTextColor(Color.WHITE);
                    achievement_rb.setTextColor(Color.parseColor("#e01c42"));
                    getMylistOfChallenge();
                    lAdapter2 = new ListAdapterComplitedChallenges(ComplitedChallengeActivity.this, version2, versionNumber2, images2);
                    listView2.setAdapter(lAdapter2);
                }
                break;
            case R.id.achievement_rb:
                if (checked) {
                    ll_challenges.setVisibility(View.GONE);
                    gridView_ll.setVisibility(View.VISIBLE);
                    achievement_rb.setTextColor(Color.WHITE);
                    challenge_rb.setTextColor(Color.parseColor("#e01c42"));
                    ImageAdapter myAdapter=new ImageAdapter(ComplitedChallengeActivity.this,mThumbIds);
                    gridview_achivment.setAdapter(myAdapter);
                }
                break;
        }
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
                    images = new int[challengeModelList.size()];

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
                        images[j]=R.drawable.challenge2;
                    }
                    Log.e("tag","version="+version);
                    Log.e("tag","versionNumber="+versionNumber.toString());
                    lAdapter = new ListAdapterMyChallengescomplited(ComplitedChallengeActivity.this, version, versionNumber, images,remaining_days,participants,startTime,endTime);
                    listView.setAdapter(lAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<GetMyChallengeModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                //   login_progressDoalog.dismiss();
                Toast.makeText(ComplitedChallengeActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ComplitedChallengeActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ComplitedChallengeActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }

   /* class ImageAdapter extends BaseAdapter {

        private Context mContext;

        // Constructor
        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                imageView.setPadding(8, 8, 8, 8);
            }
            else
            {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // Keep all Images in array
        public Integer[] mThumbIds = {
                R.drawable.badge1, R.drawable.badge2,
                R.drawable.badge3, R.drawable.badge4,
                R.drawable.badge5, R.drawable.badge6,
                R.drawable.badge7, R.drawable.badge8,
                R.drawable.badge9, R.drawable.badge10,
                R.drawable.badge11, R.drawable.badge12
        };
    }
*/
   public class ImageAdapter extends BaseAdapter {
       private Context context;
       private final Integer[] images;
     //  private final String name[];

       private ImageView imageView;
       private TextView textView;

       private LayoutInflater layoutInflater;

       public ImageAdapter(Context context, Integer[] images) {
           this.context = context;
           this.images = images;
          // this.name = name;
       }

       @Override
       public int getCount() {
           return images.length;
       }

       @Override
       public Object getItem(int position) {
           return null;
       }

       @Override
       public long getItemId(int position) {
           return 0;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
           layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView=layoutInflater.inflate(R.layout.achivment_gridview,null);
           imageView=(ImageView)convertView.findViewById(R.id.imageview);

           //   textView=(TextView)convertView.findViewById(R.id.textview);

           imageView.setImageResource(images[position]);

          /* imageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   showCustomDistanceDialog(v);
               }
           });*/
        //   textView.setText(name[position]);
           return convertView;
       }
   }

    void showCustomDistanceDialog(View root) {

        ViewGroup viewGroup = root.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(ComplitedChallengeActivity.this).inflate(R.layout.congratulation_dialog, viewGroup, false);
        Button submit = dialogView.findViewById(R.id.submit_btn);

        dialogView.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AlertDialog.Builder builder = new AlertDialog.Builder(ComplitedChallengeActivity.this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        submit.setOnClickListener(v -> {


            alertDialog.cancel();
        });

    }
    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.noti_setting, menu);

        menu.findItem(R.id.nav_setting).setVisible(false);
        menu.findItem(R.id.nav_search).setVisible(false);
//        menu.findItem(R.id.nav_notification).setVisible(false);
//        menu.findItem(R.id.nav_add).setVisible(false);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_notification) {
            return true;
        } else if (id == R.id.nav_add) {
            startActivity(new Intent(mContext, AddGear.class));
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }*/
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.noti_setting, menu);
       menu.findItem(R.id.nav_setting).setVisible(false);
       //  menu.findItem(R.id.nav_search).setVisible(false);
       menu.findItem(R.id.nav_notification).setVisible(false);
       //menu.findItem(R.id.nav_add).setVisible(false);
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

class ListAdapterComplitedChallenges extends BaseAdapter {

    Context context;
    private final String [] values;
    private final String [] numbers;
    private final int [] images;

    public ListAdapterComplitedChallenges(Context context, String [] values, String [] numbers, int [] images){
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
                /*intent.putExtra("participents", viewHolder.participents_tv.getText().toString());
                intent.putExtra("startDate", viewHolder.startTime_tv.getText().toString());
                intent.putExtra("endDate", viewHolder.endTime_tv.getText().toString());*/
                context.startActivity(intent);
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

class ListAdapterMyChallengescomplited extends BaseAdapter {

    Context context;
    private final String [] values;
    private final String [] numbers;
    private final int [] images;
    private final String[] remainingDays,participents,startTime,endTime;

    public ListAdapterMyChallengescomplited(Context context, String [] values, String [] numbers, int [] images,String [] remainingDays,String [] participents,String [] startTime,String [] endTime){
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
                context.startActivity(intent);
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