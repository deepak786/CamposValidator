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

package com.campos.validator;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.campos.validator.library.cep.CEPHandler;
import com.campos.validator.library.cep.CEPModel;
import com.campos.validator.library.cep.CEPResponse;
import com.campos.validator.library.utility.Validator;

import retrofit2.Call;

public class MainActivityViewModel extends ViewModel implements CEPResponse {
    private MutableLiveData<String> CPF = new MutableLiveData<>();
    private MutableLiveData<String> cpfError = new MutableLiveData<>();

    private MutableLiveData<String> CNPJ = new MutableLiveData<>();
    private MutableLiveData<String> cnpjError = new MutableLiveData<>();

    private MutableLiveData<String> CEP = new MutableLiveData<>();
    private MutableLiveData<String> cepError = new MutableLiveData<>();
    private MutableLiveData<String> cepDetails = new MutableLiveData<>();

    private Call call;

    public MutableLiveData<String> getCPF() {
        return CPF;
    }

    public MutableLiveData<String> getCpfError() {
        return cpfError;
    }

    public MutableLiveData<String> getCNPJ() {
        return CNPJ;
    }

    public MutableLiveData<String> getCnpjError() {
        return cnpjError;
    }

    public MutableLiveData<String> getCEP() {
        return CEP;
    }

    public MutableLiveData<String> getCepError() {
        return cepError;
    }

    public MutableLiveData<String> getCepDetails() {
        return cepDetails;
    }

    /**
     * validate the Fields
     */
    public void validate() {
        // validate CPF
        if (Validator.isValidCPF(CPF.getValue())) {
            cpfError.postValue(null);
        } else {
            cpfError.postValue("Invalid CPF");
        }

        // validate CNPJ
        if (Validator.isValidCNPJ(CNPJ.getValue())) {
            cnpjError.postValue(null);
        } else {
            cnpjError.postValue("Invalid CNPJ");
        }

        // validate CEP
        if (Validator.isValidFormatCEP(CEP.getValue())) {
            cepError.postValue(null);
            // get the CEP details
            call = CEPHandler.getInstance().validateCEP(CEP.getValue(), this);
        } else {
            cepError.postValue("Invalid CEP");
        }
    }

    @Override
    public void CEPSuccess(CEPModel cepModel) {
        cepDetails.postValue(cepModel.toString());
        System.out.println(">>>>>" + cepModel.toString());
    }

    @Override
    public void CEPInvalid() {
        cepError.postValue("Invalid CEP");
    }

    @Override
    public void CEPError(String error) {
        cepError.postValue(error);
    }

    @Override
    protected void onCleared() {
        if (call != null)
            call.cancel();
        super.onCleared();
    }
}
