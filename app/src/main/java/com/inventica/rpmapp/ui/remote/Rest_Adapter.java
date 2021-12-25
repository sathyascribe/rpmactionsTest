package com.inventica.rpmapp.ui.remote;


import com.inventica.rpmapp.BuildConfig;
import com.inventica.rpmapp.ui.ConnectionHelper;
import com.inventica.rpmapp.ui.application.RPMApplication;
import com.inventica.rpmapp.ui.rest.AuthenticationInterceptor;

import org.openapitools.client.ApiClient;
import org.openapitools.client.api.AccountApi;
import org.openapitools.client.api.DataApi;
import org.openapitools.client.api.DropDownApi;
import org.openapitools.client.api.MobileAccessoriesApi;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


public class Rest_Adapter {


    public static Retrofit retrofit = null;



   /* public static Retrofit getClient(String url){
        if(retrofit == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }*/


    public static ApiClient getApiClient(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


         String token = ConnectionHelper.getToken(RPMApplication.getAppContext());

        // val refreshToken = ConnectionHelper.getRefreshToken(SainaApplication.getAppContext())


        Interceptor interceptor = new AuthenticationInterceptor(token,token);

        if (BuildConfig.DEBUG) {

//         logging =  new HttpLoggingInterceptor();
//        logging.level = HttpLoggingInterceptor.Level.BODY


            httpClient.connectTimeout(380, TimeUnit.SECONDS)
                    .readTimeout(380, TimeUnit.SECONDS)
                    .followRedirects(true)
                    .followSslRedirects(true)
                    .addInterceptor(interceptor);
                //    .addInterceptor(interceptor)

        } else
            httpClient.connectTimeout(380, TimeUnit.SECONDS)
                    .readTimeout(380, TimeUnit.SECONDS)
                    .followRedirects(true)
                    .followSslRedirects(true)
                    .addInterceptor(interceptor);

        return new ApiClient(httpClient.build());
    }

    public static AccountApi getAccountApi() {
        return getApiClient().createService(AccountApi.class);
    }
    public static DropDownApi getDropDownApi() {
        return getApiClient().createService(DropDownApi.class);
    }
    public static MobileAccessoriesApi getMobileAccessoriesApi() {
        return getApiClient().createService(MobileAccessoriesApi.class);
    }
    public static DataApi getDataApi() {
        return getApiClient().createService(DataApi.class);
    }
}

