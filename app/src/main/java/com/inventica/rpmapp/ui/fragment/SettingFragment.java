package com.inventica.rpmapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.adapter.CustomAdapter;
import com.inventica.rpmapp.ui.adapter.ImageAdapter;
import com.inventica.rpmapp.ui.modles.itemModel;

import java.util.ArrayList;

public class SettingFragment extends Fragment {

    private SettingFragment galleryViewModel;
    String[] mobileArray = {"Notification","Manage Permissions","Privacy Policy","Terms of Use",
            "About","Send us Feedback"};
    boolean[] listImages={true, true, true, true, true,true};

    int image[] = {R.drawable.icon_activity,
            R.drawable.icon_activity,
            R.drawable.icon_calendar,
            R.drawable.icon_activity,
            R.drawable.icon_calendar,
            R.drawable.ic_menu_camera,
           };
    String name[] = {"chocolate", "Aubergine", "Banana", "Burger", "Cake", "Ice Cream", "Tomato", "Watermelon"};
    ArrayList<itemModel> arrayList;
    CustomAdapter adapter1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        ListView listView = (ListView) root.findViewById(R.id.mobile_list);
     // ArrayAdapter  adapter = new ArrayAdapter<String>(getContext(), R.layout.setting_listview, mobileArray);
      //  adapter = new ImageAdapter(getActivity(), mobileArray);
     //   setListAdapter(new ImageAdapter(this, R.layout.main, R.id.text1, R.id.image1, listItems, listImages ));

        arrayList = new ArrayList<>();

        for (int i = 0; i < mobileArray.length; i++) {
            itemModel itemmodel = new itemModel();
            itemmodel.setImgRes(image[i]);
            itemmodel.setName(mobileArray[i]);
            //add in array list
            arrayList.add(itemmodel);
        }
        adapter1 = new CustomAdapter(getActivity(), arrayList);
        listView.setAdapter(adapter1);
        return root;
    }
}