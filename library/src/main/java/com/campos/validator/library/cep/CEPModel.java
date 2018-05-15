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

import java.io.Serializable;

/**
 * Created by root on 28/3/18.
 */

public class CEPModel implements Serializable {
    private boolean erro = false; // true if CEP is not found
    private String cep; // CEP
    private String logradouro; // Street
    private String complemento; // Street Number
    private String bairro; // district
    private String localidade; // city
    private String uf; // state initials

    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getStreet() {
        return getLogradouro();
    }

    public String getStreetNumber() {
        return getComplemento();
    }

    public String getDistrict() {
        return getBairro();
    }

    public String getCity() {
        return getLocalidade();
    }

    public String getState() {
        return getUf();
    }

    @Override
    public String toString() {
        return ("CEP: " + getCep()
                + "\nlogradouro: " + getStreet()
                + "\nstreet: " + getStreet()
                + "\ncomplemento: " + getStreetNumber()
                + "\nstreetNumber: " + getStreetNumber()
                + "\nbairro: " + getDistrict()
                + "\ndistrict: " + getDistrict()
                + "\nlocalidade: " + getCity()
                + "\ncity: " + getCity()
                + "\nuf: " + getState()
                + "\nstate: " + getState());
    }
}
