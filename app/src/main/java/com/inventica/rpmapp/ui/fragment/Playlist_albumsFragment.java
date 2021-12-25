package com.inventica.rpmapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.inventica.rpmapp.R;

public class Playlist_albumsFragment extends Fragment {

    private Playlist_albumsFragment galleryViewModel;
    String[] mobileArray = {"Notification","Manage Permissions","Privacy Policy","Terms of Use",
            "About","Send us Feedback"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      /*  galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_playlistalbms, container, false);
    /*    ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) root.findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);*/
        return root;
    }
}