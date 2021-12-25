package com.inventica.rpmapp.ui.activity.gear;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.MySharedPreferences;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.jetbrains.annotations.NotNull;
import org.openapitools.client.model.GetUserGearModel;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.UserRetaireGearModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActiveGearAdaper extends RecyclerView.Adapter<ActiveGearAdaper.ViewHolder> {

    private ArrayList<GetUserGearModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private static RadioButton lastChecked = null;
    private static int lastCheckedPos = 0;

    public ActiveGearAdaper(Context context, ArrayList<GetUserGearModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_view_gear, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

         final GetUserGearModel model = mData.get(position);
         String distanceUnits= getDistanceUnit(model.getDistanceMeasurementId());
         holder.retired.setOnClickListener( v -> showDialog(model.getGearId()));
         // holder.progressBar.setMax(Math.toIntExact(model.getMaxDistance()));
         // getDistanceUnit(model.getDistanceMeasurementId());
         holder.progressBar.setMax(getMaxDistance(position));
         holder.progressBar.setProgress(getDistance(position));
         holder.modelName.setText(model.getBrandName());
         holder.brandName.setText(model.getModelName());
         SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
         String date = dateFormat.format(model.getStartDate());
         holder.date.setText(date);
         holder.type.setText(model.getIsWalking()? "Walking" : "Running");
         holder.radioButton.setChecked(mData.get(position).getIsSelected());
         holder.radioButton.setTag(new Integer(position));
         String distanceString = getDistance(position)+"/"+ getMaxDistance(position)+distanceUnits;
         holder.distance.setText(distanceString);

        if(position == 0 && mData.get(0).getIsSelected() && holder.radioButton.isChecked())
        {
            lastChecked = holder.radioButton;
            lastCheckedPos = 0;
        }
        holder.radioButton.setOnClickListener(v -> {
            RadioButton cb = (RadioButton) v;
            int clickedPos = ((Integer)cb.getTag()).intValue();

            if(cb.isChecked())
            {
                if(lastChecked != null)
                {
                    lastChecked.setChecked(false);
                    mData.get(lastCheckedPos).setIsSelected(false);
                }

                lastChecked = cb;
                lastCheckedPos = clickedPos;
            }
            else
                lastChecked = null;

            mData.get(clickedPos).setIsSelected(cb.isChecked());
        });

         int id = Math.toIntExact(mData.get(lastCheckedPos).getId());

        MySharedPreferences.setPreferenceInt(mInflater.getContext(),"SelectedGearId",id);
        Log.d("SelectedGearId", "onBindViewHolder: "+ id);

    }

//    private void setProgressMax(ProgressBar pb, int max) {
//        pb.setMax(max * 100);
//    }
//
//    private void setProgressAnimate(ProgressBar pb, int progressTo)
//    {
//        ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", pb.getProgress(), progressTo * 100);
//        animation.setDuration(500);
//        animator.setAutoCancel(true);
//        animation.setInterpolator(new DecelerateInterpolator());
//        animation.start();
//    }

   private int getMaxDistance(int position)
    {
        switch (position)
        {
            case 0: return 110;
            case 1: return 120;
            case 2: return  80;
            default: return  100;
        }
    }
    private int getDistance(int position)
    {
        switch (position)
        {
            case 0: return 30;
            case 1: return 60;
            case 2: return 50;
            default: return  10;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getDistanceUnit(Long distanceMeasurementId) {

        String distanceUnit ;
        switch (Math.toIntExact(distanceMeasurementId))
        {
            case 1 : distanceUnit = "Km";
            break;
            case 2 : distanceUnit = "Mi";
            break;
            default: distanceUnit = "M";
        }
        return distanceUnit;

    }

  public void  showDialog(long gearId){

        Dialog dialog = new Dialog(mInflater.getContext());
        dialog.setContentView(R.layout.dialog_retire);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView textTitle = dialog.findViewById(R.id.dialogTitle);
        TextView textDescription = dialog.findViewById(R.id.dialogDescription);
        String title = "Retire Shoe";
        textTitle.setText(title);
        String description = mInflater.getContext().getString(R.string.retire);
        textDescription.setText(description);
        Button buttonCancel = dialog.findViewById(R.id.cancel_btn);
        Button buttonRetire = dialog.findViewById(R.id.buttonRetire);
        buttonCancel.setOnClickListener(v -> dialog.dismiss());
        buttonRetire.setOnClickListener(v -> retireGear(gearId));
        dialog.show();

    }

    private void retireGear(long id) {

        UserRetaireGearModel userRetaireGearModel = new UserRetaireGearModel();
        userRetaireGearModel.userGearId(id);
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesUpdateRetireGearPost(userRetaireGearModel).enqueue(new Callback<ModelApiResponse>() {
            @Override
            public void onResponse(@NotNull Call<ModelApiResponse> call, @NotNull Response<ModelApiResponse> response) {

                if(response.isSuccessful() && response.code() == 200)
                {
                    Toast.makeText(mInflater.getContext(),"Shoe retired",Toast.LENGTH_LONG).show();
                    Log.d("Retired", "onResponse: "+ "Shoe retired");

                    ((GearActivity)mInflater.getContext()).getGearList();

                }
                else if(response.isSuccessful() && response.code() ==500){

                    Toast.makeText(mInflater.getContext(),response.message(),Toast.LENGTH_LONG).show();
                    Log.d("Retired", "onResponse: "+response.message() + 500);
                }
                else
                {
                    Toast.makeText(mInflater.getContext(),response.message(),Toast.LENGTH_LONG).show();
                    Log.d("Retired", "onResponse: "+response.message() );
                }
            }

            @Override
            public void onFailure(@NotNull Call<ModelApiResponse> call, @NotNull Throwable t) {
                Toast.makeText(mInflater.getContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                Log.d("Retired", "onResponse: Something went wrong" );
            }

        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView brandName,modelName,retired,distance,type,date;
        RadioButton radioButton;
        ProgressBar progressBar;
        CircleImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.text_brand);
            modelName = itemView.findViewById(R.id.text_model);
            retired = itemView.findViewById(R.id.text_retired);
            distance = itemView.findViewById(R.id.text_distance);
            type = itemView.findViewById(R.id.text_type);
            date = itemView.findViewById(R.id.text_date);
            radioButton = itemView.findViewById(R.id.radioButton);
            progressBar = itemView.findViewById(R.id.progress_bar);
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
