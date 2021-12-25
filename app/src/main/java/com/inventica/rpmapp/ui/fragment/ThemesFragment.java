package com.inventica.rpmapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.inventica.rpmapp.R;

import org.openapitools.client.model.QueryMasterModel;

public class ThemesFragment extends Fragment {

    private ThemesFragment galleryViewModel;

    Spinner sp_query;
    Button btn_submit;
    Integer sp_queryId;
    EditText query_msg_et;
    QueryMasterModel Obj_Class_countryDetails;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_theme, container, false);

        GridView gridview = (GridView) root.findViewById(R.id.gridview);
        Button btn_submit = (Button) root.findViewById(R.id.btn_submit);
        gridview.setAdapter(new ImageAdapter(getActivity()));

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FragmentManager fm = getFragmentManager();
                    fm.popBackStack();*/
                getActivity().onBackPressed();
            }
        });
        return root;
    }

}

class ImageAdapter extends BaseAdapter {

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
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
            R.drawable.theme1, R.drawable.theme3,
            R.drawable.theme2, R.drawable.theme4
    };
}
