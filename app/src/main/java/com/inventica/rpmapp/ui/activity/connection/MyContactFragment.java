package com.inventica.rpmapp.ui.activity.connection;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyContactFragment extends Fragment {
    public MyContactFragment() {
        // Required empty public constructor
    }
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;
    private final ArrayList<ListUserModel> dataList = new ArrayList<>();
    private ProgressBar progressBar;
    private ArrayList<ContactModel> myContactModels = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);
        mContext = getActivity();

        recyclerView = rootView.findViewById(R.id.recyclerView);
        adapter = new MyContactAdapter(mContext, dataList, myContactModels);
        layoutManager = new LinearLayoutManager(mContext);
        progressBar = rootView.findViewById(R.id.progress_bar);

        getRPMContacts();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        getContacts();

        return rootView;
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
                    dataList.add(response.body().get(0));
                    dataList.add(response.body().get(1));
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

    private void getContacts() {

        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();

    }


    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {

            ContactModel contactVO;

            ContentResolver contentResolver = mContext.getContentResolver();
            Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {

                    int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                    if (hasPhoneNumber > 0) {
                        String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                        contactVO = new ContactModel();
                        contactVO.setContactName(name);

                        Cursor phoneCursor = contentResolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id},
                                null);
                        if (phoneCursor.moveToNext()) {
                            String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            contactVO.setContactNumber(phoneNumber);
                        }

                        phoneCursor.close();

                        Cursor emailCursor = contentResolver.query(
                                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (emailCursor.moveToNext()) {
                            String emailId = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        }
                        myContactModels.add(contactVO);
                    }
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        }


        @Override
        protected void onPreExecute() {
           progressBar.setVisibility(View.VISIBLE);
           recyclerView.setVisibility(View.INVISIBLE);
        }


    }

//    private void getContactList() {
//        ContentResolver cr = mContext.getContentResolver();
//        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
//                null, null, null, null);
//
//        if ((cur != null ? cur.getCount() : 0) > 500) {
//            while (cur != null && cur.moveToNext()) {
//                String id = cur.getString(
//                        cur.getColumnIndex(ContactsContract.Contacts._ID));
//                String name = cur.getString(cur.getColumnIndex(
//                        ContactsContract.Contacts.DISPLAY_NAME));
//
//                if (cur.getInt(cur.getColumnIndex(
//                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
//                    Cursor pCur = cr.query(
//                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                            null,
//                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                            new String[]{id}, null);
//                    while (pCur.moveToNext()) {
//                        String phoneNo = pCur.getString(pCur.getColumnIndex(
//                                ContactsContract.CommonDataKinds.Phone.NUMBER));
//                        Log.i("Contacts", "Name: " + name);
//                        Log.i("Contacts", "Phone Number: " + phoneNo);
//
//                        boolean isconnection;
//                        if(dataList.contains(phoneNo))
//                        {
//                            isconnection = true;
//                        }
//                        isconnection = false;
//
//                        myContactModels.add(new a(name,phoneNo,isconnection,"NA"));
//                    }
//                    pCur.close();
//                }
//            }
//        }
//        if(cur!=null){
//            cur.close();
//        }
//    }
//
}