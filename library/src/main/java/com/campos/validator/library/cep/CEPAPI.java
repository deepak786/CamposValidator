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

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by root on 28/3/18.
 */

public interface CEPAPI {
    String BASE_URL = "https://viacep.com.br/";

    // validate CEP
    @GET("ws/{CEP}/json/")
    Call<CEPModel> validateCEP(@Path("CEP") String CEP);
}
