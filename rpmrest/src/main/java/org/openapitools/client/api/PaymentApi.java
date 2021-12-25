package org.openapitools.client.api;

import org.openapitools.client.CollectionFormats.*;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import org.openapitools.client.model.CustomPaymentRequestModel;
import org.openapitools.client.model.MakePaymentModel;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.PaymentGatewayModel;
import org.openapitools.client.model.SubscriptionPlanModel;
import org.openapitools.client.model.SubscriptionTokenModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PaymentApi {
  /**
   * 
   * 
   * @param customPaymentRequestModel  (optional)
   * @return Call&lt;ModelApiResponse&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/Payment/CustomPayment")
  Call<ModelApiResponse> apiPaymentCustomPaymentPost(
    @retrofit2.http.Body CustomPaymentRequestModel customPaymentRequestModel
  );

  /**
   * 
   * 
   * @return Call&lt;List&lt;SubscriptionPlanModel&gt;&gt;
   */
  @GET("api/Payment/GetListMoblieSubscriptionPlans")
  Call<List<SubscriptionPlanModel>> apiPaymentGetListMoblieSubscriptionPlansGet();
    

  /**
   * 
   * 
   * @return Call&lt;List&lt;SubscriptionTokenModel&gt;&gt;
   */
  @GET("api/Payment/GetListSubsciptiontoken")
  Call<List<SubscriptionTokenModel>> apiPaymentGetListSubsciptiontokenGet();
    

  /**
   * 
   * 
   * @param makePaymentModel  (optional)
   * @return Call&lt;ModelApiResponse&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/Payment/MakePaymentForGroupAdmin")
  Call<ModelApiResponse> apiPaymentMakePaymentForGroupAdminPost(
    @retrofit2.http.Body MakePaymentModel makePaymentModel
  );

  /**
   * 
   * 
   * @param paymentGatewayModel  (optional)
   * @return Call&lt;ModelApiResponse&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/Payment/MakePaymentForUsers")
  Call<ModelApiResponse> apiPaymentMakePaymentForUsersPost(
    @retrofit2.http.Body PaymentGatewayModel paymentGatewayModel
  );

}
