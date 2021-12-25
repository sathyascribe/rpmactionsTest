# PaymentApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiPaymentCustomPaymentPost**](PaymentApi.md#apiPaymentCustomPaymentPost) | **POST** api/Payment/CustomPayment | 
[**apiPaymentGetListMoblieSubscriptionPlansGet**](PaymentApi.md#apiPaymentGetListMoblieSubscriptionPlansGet) | **GET** api/Payment/GetListMoblieSubscriptionPlans | 
[**apiPaymentGetListSubsciptiontokenGet**](PaymentApi.md#apiPaymentGetListSubsciptiontokenGet) | **GET** api/Payment/GetListSubsciptiontoken | 
[**apiPaymentMakePaymentForGroupAdminPost**](PaymentApi.md#apiPaymentMakePaymentForGroupAdminPost) | **POST** api/Payment/MakePaymentForGroupAdmin | 
[**apiPaymentMakePaymentForUsersPost**](PaymentApi.md#apiPaymentMakePaymentForUsersPost) | **POST** api/Payment/MakePaymentForUsers | 



## apiPaymentCustomPaymentPost

> ModelApiResponse apiPaymentCustomPaymentPost(customPaymentRequestModel)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PaymentApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        PaymentApi apiInstance = new PaymentApi(defaultClient);
        CustomPaymentRequestModel customPaymentRequestModel = new CustomPaymentRequestModel(); // CustomPaymentRequestModel | 
        try {
            ModelApiResponse result = apiInstance.apiPaymentCustomPaymentPost(customPaymentRequestModel);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling PaymentApi#apiPaymentCustomPaymentPost");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **customPaymentRequestModel** | [**CustomPaymentRequestModel**](CustomPaymentRequestModel.md)|  | [optional]

### Return type

[**ModelApiResponse**](ModelApiResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiPaymentGetListMoblieSubscriptionPlansGet

> List&lt;SubscriptionPlanModel&gt; apiPaymentGetListMoblieSubscriptionPlansGet()



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PaymentApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        PaymentApi apiInstance = new PaymentApi(defaultClient);
        try {
            List<SubscriptionPlanModel> result = apiInstance.apiPaymentGetListMoblieSubscriptionPlansGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling PaymentApi#apiPaymentGetListMoblieSubscriptionPlansGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**List&lt;SubscriptionPlanModel&gt;**](SubscriptionPlanModel.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiPaymentGetListSubsciptiontokenGet

> List&lt;SubscriptionTokenModel&gt; apiPaymentGetListSubsciptiontokenGet()



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PaymentApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");
        
        // Configure API key authorization: bearer
        ApiKeyAuth bearer = (ApiKeyAuth) defaultClient.getAuthentication("bearer");
        bearer.setApiKey("YOUR API KEY");
        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
        //bearer.setApiKeyPrefix("Token");

        PaymentApi apiInstance = new PaymentApi(defaultClient);
        try {
            List<SubscriptionTokenModel> result = apiInstance.apiPaymentGetListSubsciptiontokenGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling PaymentApi#apiPaymentGetListSubsciptiontokenGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**List&lt;SubscriptionTokenModel&gt;**](SubscriptionTokenModel.md)

### Authorization

[bearer](../README.md#bearer)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |
| **401** | Unauthorized |  -  |
| **403** | Forbidden |  -  |


## apiPaymentMakePaymentForGroupAdminPost

> ModelApiResponse apiPaymentMakePaymentForGroupAdminPost(makePaymentModel)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PaymentApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        PaymentApi apiInstance = new PaymentApi(defaultClient);
        MakePaymentModel makePaymentModel = new MakePaymentModel(); // MakePaymentModel | 
        try {
            ModelApiResponse result = apiInstance.apiPaymentMakePaymentForGroupAdminPost(makePaymentModel);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling PaymentApi#apiPaymentMakePaymentForGroupAdminPost");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **makePaymentModel** | [**MakePaymentModel**](MakePaymentModel.md)|  | [optional]

### Return type

[**ModelApiResponse**](ModelApiResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiPaymentMakePaymentForUsersPost

> ModelApiResponse apiPaymentMakePaymentForUsersPost(paymentGatewayModel)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PaymentApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        PaymentApi apiInstance = new PaymentApi(defaultClient);
        PaymentGatewayModel paymentGatewayModel = new PaymentGatewayModel(); // PaymentGatewayModel | 
        try {
            ModelApiResponse result = apiInstance.apiPaymentMakePaymentForUsersPost(paymentGatewayModel);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling PaymentApi#apiPaymentMakePaymentForUsersPost");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **paymentGatewayModel** | [**PaymentGatewayModel**](PaymentGatewayModel.md)|  | [optional]

### Return type

[**ModelApiResponse**](ModelApiResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |

