package com.inventica.rpmapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.inventica.rpmapp.R;

public class PlaylistFragment extends Fragment {

    private PlaylistFragment galleryViewModel;
    String[] mobileArray = {"Notification","Manage Permissions","Privacy Policy","Terms of Use",
            "About","Send us Feedback"};

    TextView playlist_tv;

    LinearLayout ll_allsongs,ll_workout,ll_playlist;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_playlist, container, false);
        playlist_tv=root.findViewById(R.id.playlist_tv);
        ll_playlist=(LinearLayout)root.findViewById(R.id.ll_playlist);
        ll_allsongs=(LinearLayout) root.findViewById(R.id.ll_allsongs);
        ll_workout=(LinearLayout) root.findViewById(R.id.ll_workout);

        ll_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Fragment fragment1 = new Playlist_albumsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        ll_allsongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Fragment fragment2 = new AllSongsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();               }
        });
        ll_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Fragment fragment3 = new AllSongsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment3);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();               }
        });
    /*    ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) root.findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);*/
        return root;
    }
}