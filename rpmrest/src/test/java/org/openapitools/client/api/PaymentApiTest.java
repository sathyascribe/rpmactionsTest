package org.openapitools.client.api;

import org.openapitools.client.ApiClient;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.PaymentGatewayModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for PaymentApi
 */
public class PaymentApiTest {

    private PaymentApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(PaymentApi.class);
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void apiPaymentPostPostTest() {
        PaymentGatewayModel paymentGatewayModel = null;
        // ModelApiResponse response = api.apiPaymentPostPost(paymentGatewayModel);

        // TODO: test validations
    }
}
