package org.openapitools.client.api;

import org.openapitools.client.ApiClient;
import org.openapitools.client.model.UserCountModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for DataApi
 */
public class DataApiTest {

    private DataApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(DataApi.class);
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void apiDataTotalAdminUserCountGetTest() {
        // UserCountModel response = api.apiDataTotalAdminUserCountGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiDataTotalGroupAdminUserCountGetTest() {
        // UserCountModel response = api.apiDataTotalGroupAdminUserCountGet();

        // TODO: test validations
    }
}
