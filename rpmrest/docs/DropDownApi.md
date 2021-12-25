# DropDownApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiDropDownGetAllGearBrandGet**](DropDownApi.md#apiDropDownGetAllGearBrandGet) | **GET** api/DropDown/GetAllGearBrand | 
[**apiDropDownGetAllMeasureDistanceGet**](DropDownApi.md#apiDropDownGetAllMeasureDistanceGet) | **GET** api/DropDown/GetAllMeasureDistance | 
[**apiDropDownGetAllMeasureHeightGet**](DropDownApi.md#apiDropDownGetAllMeasureHeightGet) | **GET** api/DropDown/GetAllMeasureHeight | 
[**apiDropDownGetAllMeasureWeightGet**](DropDownApi.md#apiDropDownGetAllMeasureWeightGet) | **GET** api/DropDown/GetAllMeasureWeight | 
[**apiDropDownGetAllModelGet**](DropDownApi.md#apiDropDownGetAllModelGet) | **GET** api/DropDown/GetAllModel | 
[**apiDropDownGetAllVideoCategoriesGet**](DropDownApi.md#apiDropDownGetAllVideoCategoriesGet) | **GET** api/DropDown/GetAllVideoCategories | 
[**apiDropDownGetCityGet**](DropDownApi.md#apiDropDownGetCityGet) | **GET** api/DropDown/GetCity | 
[**apiDropDownGetCountryGet**](DropDownApi.md#apiDropDownGetCountryGet) | **GET** api/DropDown/GetCountry | 
[**apiDropDownGetEventFrequencyMasterGet**](DropDownApi.md#apiDropDownGetEventFrequencyMasterGet) | **GET** api/DropDown/GetEventFrequencyMaster | 
[**apiDropDownGetLanguageGet**](DropDownApi.md#apiDropDownGetLanguageGet) | **GET** api/DropDown/GetLanguage | 
[**apiDropDownGetProvinceStateGet**](DropDownApi.md#apiDropDownGetProvinceStateGet) | **GET** api/DropDown/GetProvinceState | 
[**apiDropDownGetThemesGet**](DropDownApi.md#apiDropDownGetThemesGet) | **GET** api/DropDown/GetThemes | 



## apiDropDownGetAllGearBrandGet

> GetGrearBrandModelListApiResponse apiDropDownGetAllGearBrandGet()



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DropDownApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        DropDownApi apiInstance = new DropDownApi(defaultClient);
        try {
            GetGrearBrandModelListApiResponse result = apiInstance.apiDropDownGetAllGearBrandGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DropDownApi#apiDropDownGetAllGearBrandGet");
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

[**GetGrearBrandModelListApiResponse**](GetGrearBrandModelListApiResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiDropDownGetAllMeasureDistanceGet

> List&lt;DistanceModel&gt; apiDropDownGetAllMeasureDistanceGet()



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DropDownApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        DropDownApi apiInstance = new DropDownApi(defaultClient);
        try {
            List<DistanceModel> result = apiInstance.apiDropDownGetAllMeasureDistanceGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DropDownApi#apiDropDownGetAllMeasureDistanceGet");
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

[**List&lt;DistanceModel&gt;**](DistanceModel.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiDropDownGetAllMeasureHeightGet

> List&lt;HeightModel&gt; apiDropDownGetAllMeasureHeightGet()



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DropDownApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        DropDownApi apiInstance = new DropDownApi(defaultClient);
        try {
            List<HeightModel> result = apiInstance.apiDropDownGetAllMeasureHeightGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DropDownApi#apiDropDownGetAllMeasureHeightGet");
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

[**List&lt;HeightModel&gt;**](HeightModel.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiDropDownGetAllMeasureWeightGet

> List&lt;WeightModel&gt; apiDropDownGetAllMeasureWeightGet()



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DropDownApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        DropDownApi apiInstance = new DropDownApi(defaultClient);
        try {
            List<WeightModel> result = apiInstance.apiDropDownGetAllMeasureWeightGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DropDownApi#apiDropDownGetAllMeasureWeightGet");
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

[**List&lt;WeightModel&gt;**](WeightModel.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiDropDownGetAllModelGet

> GetGrearModelListApiResponse apiDropDownGetAllModelGet(brandId)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DropDownApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        DropDownApi apiInstance = new DropDownApi(defaultClient);
        Integer brandId = 56; // Integer | 
        try {
            GetGrearModelListApiResponse result = apiInstance.apiDropDownGetAllModelGet(brandId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DropDownApi#apiDropDownGetAllModelGet");
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
 **brandId** | **Integer**|  | [optional]

### Return type

[**GetGrearModelListApiResponse**](GetGrearModelListApiResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiDropDownGetAllVideoCategoriesGet

> GetVideoCateoryModelListApiResponse apiDropDownGetAllVideoCategoriesGet()



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DropDownApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        DropDownApi apiInstance = new DropDownApi(defaultClient);
        try {
            GetVideoCateoryModelListApiResponse result = apiInstance.apiDropDownGetAllVideoCategoriesGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DropDownApi#apiDropDownGetAllVideoCategoriesGet");
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

[**GetVideoCateoryModelListApiResponse**](GetVideoCateoryModelListApiResponse.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiDropDownGetCityGet

> List&lt;CityModel&gt; apiDropDownGetCityGet(id)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DropDownApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        DropDownApi apiInstance = new DropDownApi(defaultClient);
        Integer id = 56; // Integer | 
        try {
            List<CityModel> result = apiInstance.apiDropDownGetCityGet(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DropDownApi#apiDropDownGetCityGet");
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
 **id** | **Integer**|  | [optional]

### Return type

[**List&lt;CityModel&gt;**](CityModel.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiDropDownGetCountryGet

> List&lt;CountryModel&gt; apiDropDownGetCountryGet()



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DropDownApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        DropDownApi apiInstance = new DropDownApi(defaultClient);
        try {
            List<CountryModel> result = apiInstance.apiDropDownGetCountryGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DropDownApi#apiDropDownGetCountryGet");
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

[**List&lt;CountryModel&gt;**](CountryModel.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiDropDownGetEventFrequencyMasterGet

> List&lt;EventFrequencyMasterModel&gt; apiDropDownGetEventFrequencyMasterGet()



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DropDownApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        DropDownApi apiInstance = new DropDownApi(defaultClient);
        try {
            List<EventFrequencyMasterModel> result = apiInstance.apiDropDownGetEventFrequencyMasterGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DropDownApi#apiDropDownGetEventFrequencyMasterGet");
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

[**List&lt;EventFrequencyMasterModel&gt;**](EventFrequencyMasterModel.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiDropDownGetLanguageGet

> List&lt;LanguageModel&gt; apiDropDownGetLanguageGet()



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DropDownApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        DropDownApi apiInstance = new DropDownApi(defaultClient);
        try {
            List<LanguageModel> result = apiInstance.apiDropDownGetLanguageGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DropDownApi#apiDropDownGetLanguageGet");
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

[**List&lt;LanguageModel&gt;**](LanguageModel.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiDropDownGetProvinceStateGet

> List&lt;ProvinceStateModel&gt; apiDropDownGetProvinceStateGet(id)



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DropDownApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        DropDownApi apiInstance = new DropDownApi(defaultClient);
        Integer id = 56; // Integer | 
        try {
            List<ProvinceStateModel> result = apiInstance.apiDropDownGetProvinceStateGet(id);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DropDownApi#apiDropDownGetProvinceStateGet");
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
 **id** | **Integer**|  | [optional]

### Return type

[**List&lt;ProvinceStateModel&gt;**](ProvinceStateModel.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |


## apiDropDownGetThemesGet

> List&lt;ThemesModel&gt; apiDropDownGetThemesGet()



### Example

```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DropDownApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        DropDownApi apiInstance = new DropDownApi(defaultClient);
        try {
            List<ThemesModel> result = apiInstance.apiDropDownGetThemesGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DropDownApi#apiDropDownGetThemesGet");
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

[**List&lt;ThemesModel&gt;**](ThemesModel.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Success |  -  |

