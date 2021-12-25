package com.inventica.rpmapp.ui.activity.connection;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inventica.rpmapp.R;
import com.inventica.rpmapp.ui.activity.connection.RPMUserListAdaptor;
import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import org.openapitools.client.model.ListUserModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RPMUserListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;
    private final ArrayList<ListUserModel> dataList = new ArrayList<>();
    private ProgressBar progressBar;


    public RPMUserListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void getRPMContacts() {

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        Rest_Adapter.getMobileAccessoriesApi().apiMobileAccessoriesGetListUserListGet().enqueue(new Callback<List<ListUserModel>>() {
            @Override
            public void onResponse(Call<List<ListUserModel>> call, Response<List<ListUserModel>> response) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                if (response.isSuccessful() && response.code() == 200) {
                    dataList.clear();
                    assert response.body() != null;
                    dataList.addAll(response.body());
                    adapter.notifyDataSetChanged();

                } else if (response.isSuccessful() && response.code() == 500) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                } else {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    Toast.makeText(mContext, response.message(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<ListUserModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                t.printStackTrace();
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);
        mContext = getActivity();

        recyclerView = rootView.findViewById(R.id.recyclerView);
        adapter = new RPMUserListAdaptor(mContext, dataList);
        layoutManager = new LinearLayoutManager(mContext);
        progressBar = rootView.findViewById(R.id.progress_bar);

        getRPMContacts();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

}