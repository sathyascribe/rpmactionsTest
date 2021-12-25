package com.inventica.rpmapp.ui.viewmodels;

public class DropDown_Controller {

/*
    public static void getCountryList(){

        Class_RetrofitClient.getDropDownApi().apiDropDownGetCountryGet().enqueue(new Callback<List<CountryModel>>(){
            @Override
            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                if(response.isSuccessful())
                {


                    List<CountryModel> getCountrylist_obj = response.body();
                    // List<Country> userlist_obj=response.body().;

                    Log.e("getCountrylist_obj", String.valueOf(getCountrylist_obj.get(0).getCountry()));
                    CountryModel[] countrylist_arrayObj= new CountryModel[getCountrylist_obj.size()];

                    for(int i=0;i<getCountrylist_obj.size();i++){
                        CountryModel inner_countrylst= new CountryModel();
                        inner_countrylst.setCountry(getCountrylist_obj.get(i).getCountry());
                        inner_countrylst.setId(getCountrylist_obj.get(i).getId());
                        countrylist_arrayObj[i]=inner_countrylst;
                    }
                    ArrayAdapter dataAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinnercenterstyle, countrylist_arrayObj);
                    dataAdapter.setDropDownViewResource(R.layout.spinnercenterstyle);
                    countrylist_SP.setAdapter(dataAdapter);

                    //login_progressDoalog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());

                Log.e("tag", "Error:" + t.getMessage());

                //   login_progressDoalog.dismiss();
                Toast.makeText(SignUpActivity.this, "It looks like the Internet Bandwidth is very LOW,\n please connect in good network area and Re-Try", Toast.LENGTH_SHORT).show();
            }
        });
    }
*/

}
