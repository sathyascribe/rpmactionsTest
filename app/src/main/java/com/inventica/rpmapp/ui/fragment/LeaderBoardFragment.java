package com.inventica.rpmapp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.ComplitedChallengeActivity;
import com.inventica.rpmapp.ui.activity.JoinChallenge_Activity;
import com.inventica.rpmapp.ui.modles.RpmModule;

import java.util.ArrayList;

public class LeaderBoardFragment extends Fragment {
    public LeaderBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    ArrayList<RpmModule> dataModels;
    ListView listView;
    private static CustomAdapter adapter;
    ListAdapter lAdapter;
    int[] images = {R.drawable.leader1, R.drawable.leader2, R.drawable.leader3,R.drawable.leader2, R.drawable.leader1};

    String[] version = {"Sandra Adams", "Imma jones", "Mark Smith", "Sandra Adams", "Imma jones"};
    String[] slno = {"1", "2", "3", "4", "5"};
    String[] distance = {"54.48 KM", "25.36 KM", "32.48 KM", "24.48 KM", "34.29 KM"};

   // String[] versionNumber = {"test@gmail.com", "test@gmail.com", "Ttest@gmail.com", "test@gmail.com", "test@gmail.com"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_leaderboard, container, false);
//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        listView=(ListView) rootView.findViewById(R.id.list);

    /*    dataModels= new ArrayList<>();

        dataModels.add(new RpmModule("Sandra Adams", "test@gmail.com"));
        dataModels.add(new RpmModule("Imma jones", "test@gmail.com"));
        dataModels.add(new RpmModule("Mark Smith", "test@gmail.com"));

        adapter= new CustomAdapter(dataModels,getContext());
        listView.setAdapter(adapter);*/

        lAdapter = new ListAdapter(getActivity(), version, slno, distance, images);
        listView.setAdapter(lAdapter);
        return rootView;
    }
    public static class ViewHolder {


        public ImageView rpmimage_iv;

        TextView name_tv;
        TextView emailId_tv;

    }


    class ListAdapter extends BaseAdapter {

        Context context;
        private final String [] values;
        private final String [] numbers;
        private final String [] distance;
        private final int [] images;

        public ListAdapter(Context context, String [] values, String [] numbers, String [] distance, int [] images){
            //super(context, R.layout.single_list_app_item, utilsArrayList);
            this.context = context;
            this.values = values;
            this.numbers = numbers;
            this.distance = distance;
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
                convertView = inflater.inflate(R.layout.leaderboard_adapter, parent, false);
                viewHolder.sl_no_tv = (TextView) convertView.findViewById(R.id.sl_no_tv);
                viewHolder.leadername_tv = (TextView) convertView.findViewById(R.id.leadername_tv);
                viewHolder.distance_tv = (TextView) convertView.findViewById(R.id.distance_tv);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.leaderboard_iv);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

            viewHolder.sl_no_tv.setText(numbers[position]);
            viewHolder.leadername_tv.setText(values[position]);
            viewHolder.distance_tv.setText(distance[position]);

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
                    Intent intent = new Intent(context, ComplitedChallengeActivity.class);
                   /* intent.putExtra("ChallengeName",viewHolder.txtName.getText().toString());
                    intent.putExtra("Distance", "100 Mi");
                    intent.putExtra("remainingDays", viewHolder.days_tv.getText().toString());*/
                /*intent.putExtra("participents", viewHolder.participents_tv.getText().toString());
                intent.putExtra("startDate", viewHolder.startTime_tv.getText().toString());
                intent.putExtra("endDate", viewHolder.endTime_tv.getText().toString());*/
                    context.startActivity(intent);
                }
            });
            return convertView;
        }

        private class ViewHolder {

            TextView distance_tv;
            TextView leadername_tv,sl_no_tv;
            ImageView icon;

        }

    }

    public static class CustomAdapter extends ArrayAdapter<RpmModule> implements View.OnClickListener{

       private ArrayList<RpmModule> dataSet;
       Context mContext;

       // View lookup cache
       private class ViewHolder {
           TextView txtName;
           TextView txtType;
           ImageView info;
       }

       public CustomAdapter(ArrayList<RpmModule> data, Context context) {
           super(context, R.layout.child_farmerlist_item, data);
           this.dataSet = data;
           this.mContext=context;

       }

       @Override
       public void onClick(View v) {

           int position=(Integer) v.getTag();
           Object object= getItem(position);
           RpmModule dataModel=(RpmModule)object;

         /*  switch (v.getId())
           {
               case R.id.item_info:
                   Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                           .setAction("No action", null).show();
                   break;
           }*/
       }

       private int lastPosition = -1;

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
           // Get the data item for this position
           RpmModule dataModel = getItem(position);
           // Check if an existing view is being reused, otherwise inflate the view
           ViewHolder viewHolder; // view lookup cache stored in tag

           final View result;

           if (convertView == null) {

               viewHolder = new ViewHolder();
               LayoutInflater inflater = LayoutInflater.from(getContext());
               convertView = inflater.inflate(R.layout.child_farmerlist_item, parent, false);
               viewHolder.txtName = (TextView) convertView.findViewById(R.id.Name_tv);
               viewHolder.txtType = (TextView) convertView.findViewById(R.id.emailId_tv);
               viewHolder.info = (ImageView) convertView.findViewById(R.id.rpmimage_iv);

               result=convertView;

               convertView.setTag(viewHolder);
           } else {
               viewHolder = (ViewHolder) convertView.getTag();
               result=convertView;
           }

         /*  Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
           result.startAnimation(animation);*/
           lastPosition = position;

           viewHolder.txtName.setText(dataModel.getName());
           viewHolder.txtType.setText(dataModel.getEmailId());
           viewHolder.info.setTag(position);
           // Return the completed view to render on screen
           return convertView;
       }
   }
}