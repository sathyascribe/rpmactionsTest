# AccountApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiAccountForgotPasswordPost**](AccountApi.md#apiAccountForgotPasswordPost) | **POST** api/Account/ForgotPassword | 
[**apiAccountLoginPut**](AccountApi.md#apiAccountLoginPut) | **PUT** api/Account/Login | 
[**apiAccountRefreshTokenPut**](AccountApi.md#apiAccountRefreshTokenPut) | **PUT** api/Account/RefreshToken | 
[**apiAccountRegisterPost**](AccountApi.md#apiAccountRegisterPost) | **POST** api/Account/Register | 
[**apiAccountResetPasswordPost**](AccountApi.md#apiAccountResetPasswordPost) | **POST** api/Account/ResetPassword | 



## apiAccountForgotPasswordPost

> ModelApiResponse apiAccountForgotPasswordPost(getemail)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        AccountApi apiInstance = new AccountApi(defaultClient);
        Getemail getemail = new Getemail(); // Getemail | 
        try {
            ModelApiResponse result = apiInstance.apiAccountForgotPasswordPost(getemail);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountApi#apiAccountForgotPasswordPost");
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
 **getemail** | [**Getemail**](Getemail.md)|  | [optional]

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


## apiAccountLoginPut

> LoginResponseServiceResponse apiAccountLoginPut(loginModel)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        AccountApi apiInstance = new AccountApi(defaultClient);
        LoginModel loginModel = new LoginModel(); // LoginModel | 
        try {
            LoginResponseServiceResponse result = apiInstance.apiAccountLoginPut(loginModel);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountApi#apiAccountLoginPut");
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
 **loginModel** | [**LoginModel**](LoginModel.md)|  | [optional]

### Return type

[**LoginResponseServiceResponse**](LoginResponseServiceResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiAccountRefreshTokenPut

> LoginResponseServiceResponse apiAccountRefreshTokenPut(refreshTokenModel)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        AccountApi apiInstance = new AccountApi(defaultClient);
        RefreshTokenModel refreshTokenModel = new RefreshTokenModel(); // RefreshTokenModel | 
        try {
            LoginResponseServiceResponse result = apiInstance.apiAccountRefreshTokenPut(refreshTokenModel);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountApi#apiAccountRefreshTokenPut");
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
 **refreshTokenModel** | [**RefreshTokenModel**](RefreshTokenModel.md)|  | [optional]

### Return type

[**LoginResponseServiceResponse**](LoginResponseServiceResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiAccountRegisterPost

> ModelApiResponse apiAccountRegisterPost(registerModel)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        AccountApi apiInstance = new AccountApi(defaultClient);
        RegisterModel registerModel = new RegisterModel(); // RegisterModel | 
        try {
            ModelApiResponse result = apiInstance.apiAccountRegisterPost(registerModel);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountApi#apiAccountRegisterPost");
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
 **registerModel** | [**RegisterModel**](RegisterModel.md)|  | [optional]

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


## apiAccountResetPasswordPost

> ModelApiResponse apiAccountResetPasswordPost(resetPasswordModel)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.AccountApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        AccountApi apiInstance = new AccountApi(defaultClient);
        ResetPasswordModel resetPasswordModel = new ResetPasswordModel(); // ResetPasswordModel | 
        try {
            ModelApiResponse result = apiInstance.apiAccountResetPasswordPost(resetPasswordModel);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling AccountApi#apiAccountResetPasswordPost");
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
 **resetPasswordModel** | [**ResetPasswordModel**](ResetPasswordModel.md)|  | [optional]

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

