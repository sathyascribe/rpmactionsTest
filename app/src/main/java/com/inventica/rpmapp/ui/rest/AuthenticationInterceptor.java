package com.inventica.rpmapp.ui.rest;


import androidx.annotation.NonNull;


import com.inventica.rpmapp.ui.ConnectionHelper;
import com.inventica.rpmapp.ui.application.RPMApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class AuthenticationInterceptor implements Interceptor {

    private String authToken;
    private String authRefreshToken;
    private static boolean isSessionTimeOut = false;
    private static boolean isLogoutCalled = false;

    public AuthenticationInterceptor(String token, String authRefreshToken) {
        this.authToken = ConnectionHelper.getToken(RPMApplication.getAppContext());
        this.authRefreshToken = authRefreshToken;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", "Bearer " + authToken);

        Request request = builder.build();
        Response response = chain.proceed(request);

        // if token is expired
        if (response.code() == 401) {


          /*  if (!isSessionTimeOut) {
                isSessionTimeOut = true;


                String currentToken = ConnectionHelper.getToken(RPMApplication.getAppContext());
                String mainToken = SharedUtils.INSTANCE.getString(Constants.MAIN_TOKEN, RPMApplication.getAppContext());

            *//*    Log.e("tag", "_____________________________________________");
                Log.e("currentToken", "" + currentToken);
                Log.e("mainToken", "" + mainToken);
                Log.e("tag", "_____________________________________________");
*//*

                if (currentToken != null && currentToken.equalsIgnoreCase(mainToken)) {
                    // if(true){

                    Call<LoginResponse> call = getLoginAccountApi().apiAccountsRefreshTokenPost(ConnectionHelper.getRefreshToken(SainaApplication.getAppContext()));
                    retrofit2.Response<LoginResponse> execute = call.execute();
                    LoginResponse model = execute.body();

                    if (model == null) {
                        ConnectionHelper.setToken(SainaApplication.getAppContext(), null, null);

                        logoutFromTheApplication();
                      //  Log.e("Refresh Token", "@@@@@@@@@ API Not Refreshed");
                        throw new AuthenticationFailedException();

                    } else {

                        ConnectionHelper.setToken(SainaApplication.getAppContext(), model.getAccessToken(), model.getRefreshToken());
                        SharedUtils.INSTANCE.put(Constants.MAIN_TOKEN, model.getAccessToken(), SainaApplication.getAppContext());
                        this.authToken = model.getAccessToken();
                        this.authRefreshToken = model.getRefreshToken();
                        // create a new request and modify it accordingly using the new token
                        Request newRequest = request.newBuilder().header("Authorization", "Bearer " + authToken).build();
                        // retry the request
                        response.close();
                   //     Log.e("Refresh Token", "@@@@@@@@@ Refreshed");
                        isSessionTimeOut = false;
                        return chain.proceed(newRequest);
                    }

                } else {

                    logoutFromTheApplication();
                 //   Log.e("Refresh Token", "------------------ > Switched USER so token cannot be Refreshed");
                }
            } else {

                if (!isLogoutCalled) {
                    isLogoutCalled  = true;
                    logoutFromTheApplication();
                }
            }*/
        }
        else {
            isLogoutCalled = false;
        }
        return response;
    }

/*    private void logoutFromTheApplication() {
        ConnectionHelper.setToken(SainaApplication.getAppContext(), null, null);
        String email = SharedUtils.INSTANCE.getString("email", SainaApplication.getAppContext());
        SharedUtils.INSTANCE.clearData(SainaApplication.getAppContext());
        SharedUtils.INSTANCE.put("email", email, SainaApplication.getAppContext());
        Intent intent = new Intent(SainaApplication.getAppContext(), LandingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        SainaApplication.getAppContext().startActivity(intent);
    }*/
}