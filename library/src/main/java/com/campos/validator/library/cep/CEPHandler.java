/*
 * Copyright (c) 2018, Deepak Goyal under Apache License.
 *   All rights reserved.
 *   Redistribution and use in source and binary forms, with or without
 *   modification, are permitted provided that the following conditions are met:
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 */

package com.campos.validator.library.cep;

import android.support.annotation.NonNull;

import com.campos.validator.library.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 28/3/18.
 */

public class CEPHandler {
    private static CEPHandler instance;
    private CEPAPI handler; // Interface where all API methods are getting called

    private CEPHandler() {
    }

    /**
     * get the instance of {@link CEPHandler\}
     *
     * @return instance
     */
    public static CEPHandler getInstance() {
        if (instance == null) {
            instance = new CEPHandler();
        }
        return instance;
    }

    /**
     * get {@link Retrofit}
     *
     * @return Retrofit
     */
    private Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG)
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        else
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(CEPAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * get the API handler {@link CEPAPI}
     *
     * @return handler
     */
    private CEPAPI getHandler() {
        if (handler == null)
            handler = getRetrofit().create(CEPAPI.class);
        return handler;
    }

    /**
     * validate CEP
     */
    public Call<CEPModel> validateCEP(String CEP, final CEPResponse callback) {
        Call<CEPModel> call = getHandler().validateCEP(CEP);
        call.enqueue(new Callback<CEPModel>() {
            @Override
            public void onResponse(@NonNull Call<CEPModel> call, @NonNull Response<CEPModel> response) {
                if (response.isSuccessful()) {
                    CEPModel cepModel = response.body();
                    if (cepModel != null && !cepModel.isErro()) {
                        if (callback != null) callback.CEPSuccess(cepModel);
                        return;
                    }
                }
                if (callback != null) callback.CEPInvalid();
            }

            @Override
            public void onFailure(@NonNull Call<CEPModel> call, @NonNull Throwable t) {
                if (!call.isCanceled())
                    if (callback != null) callback.CEPError(t.getLocalizedMessage());
            }
        });
        return call;
    }
}
