package org.openapitools.client.api;

import org.openapitools.client.ApiClient;
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
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for DropDownApi
 */
public class DropDownApiTest {

    private DropDownApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(DropDownApi.class);
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void apiDropDownGetAllGearBrandGetTest() {
        // GetGrearBrandModelListApiResponse response = api.apiDropDownGetAllGearBrandGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiDropDownGetAllMeasureDistanceGetTest() {
        // List<DistanceModel> response = api.apiDropDownGetAllMeasureDistanceGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiDropDownGetAllMeasureHeightGetTest() {
        // List<HeightModel> response = api.apiDropDownGetAllMeasureHeightGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiDropDownGetAllMeasureWeightGetTest() {
        // List<WeightModel> response = api.apiDropDownGetAllMeasureWeightGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiDropDownGetAllModelGetTest() {
        Integer brandId = null;
        // GetGrearModelListApiResponse response = api.apiDropDownGetAllModelGet(brandId);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiDropDownGetAllVideoCategoriesGetTest() {
        // GetVideoCateoryModelListApiResponse response = api.apiDropDownGetAllVideoCategoriesGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiDropDownGetCityGetTest() {
        Integer id = null;
        // List<CityModel> response = api.apiDropDownGetCityGet(id);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiDropDownGetCountryGetTest() {
        // List<CountryModel> response = api.apiDropDownGetCountryGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiDropDownGetEventFrequencyMasterGetTest() {
        // List<EventFrequencyMasterModel> response = api.apiDropDownGetEventFrequencyMasterGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiDropDownGetLanguageGetTest() {
        // List<LanguageModel> response = api.apiDropDownGetLanguageGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiDropDownGetProvinceStateGetTest() {
        Integer id = null;
        // List<ProvinceStateModel> response = api.apiDropDownGetProvinceStateGet(id);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiDropDownGetThemesGetTest() {
        // List<ThemesModel> response = api.apiDropDownGetThemesGet();

        // TODO: test validations
    }
}
