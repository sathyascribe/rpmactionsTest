package com.inventica.rpmapp.ui.utils;


import com.inventica.rpmapp.ui.remote.Rest_Adapter;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

    public static DefaultResponse parseError(Response<?> response) {
        Converter<ResponseBody, DefaultResponse> converter =
                Rest_Adapter.retrofit
                        .responseBodyConverter(DefaultResponse.class, new Annotation[0]);

        DefaultResponse error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new DefaultResponse();
        }

        return error;
    }
}
