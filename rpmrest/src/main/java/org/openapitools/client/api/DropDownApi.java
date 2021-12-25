package org.openapitools.client.api;

import org.openapitools.client.CollectionFormats.*;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import org.openapitools.client.model.CityModel;
import org.openapitools.client.model.CountryModel;
import org.openapitools.client.model.DistanceModel;
import org.openapitools.client.model.EventFrequencyMasterModel;
import org.openapitools.client.model.GetGrearBrandModelListApiResponse;
import org.openapitools.client.model.GetGrearModelListApiResponse;
import org.openapitools.client.model.GetVideoCateoryModelListApiResponse;
import org.openapitools.client.model.HeightModel;
import org.openapitools.client.model.LanguageModel;
import org.openapitools.client.model.ProvinceStateModel;
import org.openapitools.client.model.ThemesModel;
import org.openapitools.client.model.WeightModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DropDownApi {
  /**
   * 
   * 
   * @return Call&lt;GetGrearBrandModelListApiResponse&gt;
   */
  @GET("api/DropDown/GetAllGearBrand")
  Call<GetGrearBrandModelListApiResponse> apiDropDownGetAllGearBrandGet();
    

  /**
   * 
   * 
   * @return Call&lt;List&lt;DistanceModel&gt;&gt;
   */
  @GET("api/DropDown/GetAllMeasureDistance")
  Call<List<DistanceModel>> apiDropDownGetAllMeasureDistanceGet();
    

  /**
   * 
   * 
   * @return Call&lt;List&lt;HeightModel&gt;&gt;
   */
  @GET("api/DropDown/GetAllMeasureHeight")
  Call<List<HeightModel>> apiDropDownGetAllMeasureHeightGet();
    

  /**
   * 
   * 
   * @return Call&lt;List&lt;WeightModel&gt;&gt;
   */
  @GET("api/DropDown/GetAllMeasureWeight")
  Call<List<WeightModel>> apiDropDownGetAllMeasureWeightGet();
    

  /**
   * 
   * 
   * @param brandId  (optional)
   * @return Call&lt;GetGrearModelListApiResponse&gt;
   */
  @GET("api/DropDown/GetAllModel")
  Call<GetGrearModelListApiResponse> apiDropDownGetAllModelGet(
    @retrofit2.http.Query("brandId") Integer brandId
  );

  /**
   * 
   * 
   * @return Call&lt;GetVideoCateoryModelListApiResponse&gt;
   */
  @GET("api/DropDown/GetAllVideoCategories")
  Call<GetVideoCateoryModelListApiResponse> apiDropDownGetAllVideoCategoriesGet();
    

  /**
   * 
   * 
   * @param id  (optional)
   * @return Call&lt;List&lt;CityModel&gt;&gt;
   */
  @GET("api/DropDown/GetCity")
  Call<List<CityModel>> apiDropDownGetCityGet(
    @retrofit2.http.Query("id") Integer id
  );

  /**
   * 
   * 
   * @return Call&lt;List&lt;CountryModel&gt;&gt;
   */
  @GET("api/DropDown/GetCountry")
  Call<List<CountryModel>> apiDropDownGetCountryGet();
    

  /**
   * 
   * 
   * @return Call&lt;List&lt;EventFrequencyMasterModel&gt;&gt;
   */
  @GET("api/DropDown/GetEventFrequencyMaster")
  Call<List<EventFrequencyMasterModel>> apiDropDownGetEventFrequencyMasterGet();
    

  /**
   * 
   * 
   * @return Call&lt;List&lt;LanguageModel&gt;&gt;
   */
  @GET("api/DropDown/GetLanguage")
  Call<List<LanguageModel>> apiDropDownGetLanguageGet();
    

  /**
   * 
   * 
   * @param id  (optional)
   * @return Call&lt;List&lt;ProvinceStateModel&gt;&gt;
   */
  @GET("api/DropDown/GetProvinceState")
  Call<List<ProvinceStateModel>> apiDropDownGetProvinceStateGet(
    @retrofit2.http.Query("id") Integer id
  );

  /**
   * 
   * 
   * @return Call&lt;List&lt;ThemesModel&gt;&gt;
   */
  @GET("api/DropDown/GetThemes")
  Call<List<ThemesModel>> apiDropDownGetThemesGet();
    

}
