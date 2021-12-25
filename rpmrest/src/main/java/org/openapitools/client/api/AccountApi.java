package org.openapitools.client.api;

import org.openapitools.client.CollectionFormats.*;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import org.openapitools.client.model.Getemail;
import org.openapitools.client.model.LoginModel;
import org.openapitools.client.model.LoginResponseServiceResponse;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.RefreshTokenModel;
import org.openapitools.client.model.RegisterModel;
import org.openapitools.client.model.ResetPasswordModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AccountApi {
  /**
   * 
   * 
   * @param getemail  (optional)
   * @return Call&lt;ModelApiResponse&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/Account/ForgotPassword")
  Call<ModelApiResponse> apiAccountForgotPasswordPost(
    @retrofit2.http.Body Getemail getemail
  );

  /**
   * 
   * 
   * @param loginModel  (optional)
   * @return Call&lt;LoginResponseServiceResponse&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("api/Account/Login")
  Call<LoginResponseServiceResponse> apiAccountLoginPut(
    @retrofit2.http.Body LoginModel loginModel
  );

  /**
   * 
   * 
   * @param refreshTokenModel  (optional)
   * @return Call&lt;LoginResponseServiceResponse&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("api/Account/RefreshToken")
  Call<LoginResponseServiceResponse> apiAccountRefreshTokenPut(
    @retrofit2.http.Body RefreshTokenModel refreshTokenModel
  );

  /**
   * 
   * 
   * @param registerModel  (optional)
   * @return Call&lt;ModelApiResponse&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/Account/Register")
  Call<ModelApiResponse> apiAccountRegisterPost(
    @retrofit2.http.Body RegisterModel registerModel
  );

  /**
   * 
   * 
   * @param resetPasswordModel  (optional)
   * @return Call&lt;ModelApiResponse&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/Account/ResetPassword")
  Call<ModelApiResponse> apiAccountResetPasswordPost(
    @retrofit2.http.Body ResetPasswordModel resetPasswordModel
  );

}
