package com.inventica.rpmapp.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.inventica.rpmapp.R;

import com.inventica.rpmapp.ui.activity.gear.AddGear;
import com.inventica.rpmapp.ui.activity.gear.ActiveGearAdaper;
import com.inventica.rpmapp.ui.activity.gear.RetiredGearAdaptor;
import com.inventica.rpmapp.ui.modles.RpmModule;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.GetUserGearModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GearFragment extends Fragment {

    ArrayList<RpmModule> dataModels;

    private FloatingActionButton floatingActionButton;
    private ListView listView;
  //  private static Forum.CustomAdapter adapter;
    private ListAdapter lAdapter;
    private int[] images = {R.drawable.gear_icon, R.drawable.gear_icon};
    private RecyclerView recyclerViewMyGears ;
    private RecyclerView.Adapter adapterMyGears;
    private RecyclerView.LayoutManager layoutManagerMyGears;
    private RecyclerView recyclerViewMyGearsRetired ;
    private RecyclerView.Adapter adapterMyGearsRetired;
    private RecyclerView.LayoutManager layoutManagerMyGearsRetired;

    String[] version = {"Galaxy 5 Shoes", "Galaxy 5 Shoes"};

    String[] versionNumber = {"test@gmail.com", "test@gmail.com", "Ttest@gmail.com", "test@gmail.com", "test@gmail.com"};

    ListAdapter_retire Adapter1;
    int[] images1 = {R.drawable.gear_icon};

    String[] version1 = {"Galaxy 5 Shoes"};
    ArrayList<GetUserGearModel> arrayList = new ArrayList<>();
    ArrayList<GetUserGearModel> arrayListRetired = new ArrayList<>();

    private TextView textViewNoActiveGear, textViewNoRetiredGear;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_gear, container, false);

        getGearList();

        textViewNoActiveGear = root.findViewById(R.id.text_noActiveGear);
        textViewNoRetiredGear = root.findViewById(R.id.text_noRetiredGear);

        recyclerViewMyGears = (RecyclerView) root.findViewById(R.id.recyclerView_myGear);
        adapterMyGears = new ActiveGearAdaper(getActivity(),arrayList);
        layoutManagerMyGears  = new LinearLayoutManager(getActivity());
        recyclerViewMyGears.setAdapter(adapterMyGears);
        recyclerViewMyGears.setLayoutManager(layoutManagerMyGears);
        recyclerViewMyGears.setHasFixedSize(true);
        recyclerViewMyGears.setNestedScrollingEnabled(false);

        recyclerViewMyGearsRetired = (RecyclerView) root.findViewById(R.id.recyclerView_myGear_retired);
        adapterMyGearsRetired = new RetiredGearAdaptor(getActivity(),arrayListRetired);
        layoutManagerMyGearsRetired  = new LinearLayoutManager(getActivity());
        recyclerViewMyGearsRetired.setAdapter(adapterMyGearsRetired);
        recyclerViewMyGearsRetired.setLayoutManager(layoutManagerMyGearsRetired);
        recyclerViewMyGearsRetired.setHasFixedSize(true);
        recyclerViewMyGearsRetired.setNestedScrollingEnabled(false);

        floatingActionButton = root.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> startActivity(new Intent(getContext(), AddGear.class)));


        return root;
    }

    private void getGearList() {

      Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetMyCurrentGearlistGet().enqueue(new Callback<List<GetUserGearModel>>() {
          @Override
          public void onResponse(Call<List<GetUserGearModel>> call, Response<List<GetUserGearModel>> response) {

              if(response.isSuccessful() && response.code()==200)
              {
                  arrayList.clear();
                  arrayListRetired.clear();
                  for (GetUserGearModel item : response.body()) {

                       if(item.getIsRetired())
                       {
                           GetUserGearModel getUserGearModel = new GetUserGearModel();
                           getUserGearModel.setIsRetired(false);
                           getUserGearModel.setIsWalking(true);
                           getUserGearModel.setDistanceMeasurementId((long)1);
                           getUserGearModel.setGearId((long)2);
                           getUserGearModel.setId((long)1);
                           getUserGearModel.setMaxDistance((long)100);
                           getUserGearModel.setNickName("Adidas");
                           getUserGearModel.setStartDate(Calendar.getInstance().getTime());
                           getUserGearModel.setIsSelected(false);
                           arrayListRetired.add(getUserGearModel);
                       }
                       else {
                           GetUserGearModel getUserGearModel = new GetUserGearModel();
                           getUserGearModel.setIsRetired(false);
                           getUserGearModel.setIsWalking(true);
                           getUserGearModel.setDistanceMeasurementId((long)1);
                           getUserGearModel.setGearId((long)2);
                           getUserGearModel.setId((long)1);
                           getUserGearModel.setMaxDistance((long)100);
                           getUserGearModel.setNickName("Adidas");
                           getUserGearModel.setStartDate(Calendar.getInstance().getTime());
                           getUserGearModel.setIsSelected(false);
                           getUserGearModel.setExpiryDate(Calendar.getInstance().getTime());
                           arrayListRetired.add(getUserGearModel);
                           item.setIsSelected(false);
                           arrayList.add(item);
                       }
                  }
                  setVisibility();
                  adapterMyGears.notifyDataSetChanged();
                  adapterMyGearsRetired.notifyDataSetChanged();
              }
             else if(response.isSuccessful() && response.code()==500)
              {
                  Toast.makeText(getActivity(), response.message(),Toast.LENGTH_LONG).show();
              }
              else{
                  Toast.makeText(getActivity(), response.message(),Toast.LENGTH_LONG).show();
              }
          }

          @Override
          public void onFailure(Call<List<GetUserGearModel>> call, Throwable t) {

              t.printStackTrace();
              Toast.makeText(getActivity(), "Something went wrong",Toast.LENGTH_LONG).show();

          }
      });

    }

    private void setVisibility() {
        if(arrayList.size()>0) {
            recyclerViewMyGears.setVisibility(View.VISIBLE);
            textViewNoActiveGear.setVisibility(View.GONE);
        }
        else{
            recyclerViewMyGears.setVisibility(View.GONE);
            textViewNoActiveGear.setVisibility(View.VISIBLE);
        }
        if(arrayListRetired.size()>0)
        {
            recyclerViewMyGearsRetired.setVisibility(View.VISIBLE);
            textViewNoRetiredGear.setVisibility(View.GONE);
        }
        else{
            recyclerViewMyGearsRetired.setVisibility(View.GONE);
            textViewNoRetiredGear.setVisibility(View.VISIBLE);
        }
    }

    class ListAdapter extends BaseAdapter {

        Context context;
        private final String [] values;
        private final String [] numbers;
        private final int [] images;

        public ListAdapter(Context context, String [] values, String [] numbers, int [] images){
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
                convertView = inflater.inflate(R.layout.child_gearlist_item, parent, false);
                viewHolder.txtName = (TextView) convertView.findViewById(R.id.Name_tv);
                viewHolder.retire_tv = (TextView) convertView.findViewById(R.id.retire_tv);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.image_view_shoe);

                result = convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

            viewHolder.txtName.setText(values[position]);
           // viewHolder.txtVersion.setText(numbers[position]);
            viewHolder.icon.setImageResource(images[position]);

            View finalConvertView = convertView;
            viewHolder.retire_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomNameDialog(finalConvertView);
                }
            });
            return convertView;
        }

        private class ViewHolder {

            TextView txtName;
            TextView retire_tv;
            ImageView icon;

        }

    }
    void showCustomNameDialog(View root) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = root.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_retire,viewGroup, false);


      //  EditText challengeName= dialogView.findViewById(R.id.challengeName);
        Button submit=dialogView.findViewById(R.id.buttonRetire);
        Button cancel=dialogView.findViewById(R.id.cancel_btn);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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

    class ListAdapter_retire extends BaseAdapter {

        Context context;
        private final String [] values;
        private final String [] numbers;
        private final int [] images;

        public ListAdapter_retire(Context context, String [] values, String [] numbers, int [] images){
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
                convertView = inflater.inflate(R.layout.child_gearlist_item, parent, false);
                viewHolder.txtName = (TextView) convertView.findViewById(R.id.Name_tv);
                viewHolder.progressBar = (ProgressBar) convertView.findViewById(R.id.progress_bar);
                 viewHolder.retire_tv = (TextView) convertView.findViewById(R.id.retire_tv);
                viewHolder.icon = (ImageView) convertView.findViewById(R.id.image_view_shoe);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }

            viewHolder.txtName.setText(values[position]);
            // viewHolder.txtVersion.setText(numbers[position]);
            viewHolder.icon.setImageResource(images[position]);
            viewHolder.progressBar.setVisibility(View.GONE);
            viewHolder.retire_tv.setVisibility(View.GONE);
            return convertView;
        }

        private class ViewHolder {

            TextView txtName;
            TextView retire_tv;
            ProgressBar progressBar;
            ImageView icon;

        }

    }
}