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

package com.campos.validator.library.utility;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;

public class Validator {
    private static List<String> INVALID_CPFs = Arrays.asList(
            "00000000000",
            "11111111111",
            "22222222222",
            "33333333333",
            "44444444444",
            "55555555555",
            "66666666666",
            "77777777777",
            "88888888888",
            "99999999999",
            "12345678909",
            "01234567890"
    );

    private static List<String> INVALID_CNPJs = Arrays.asList(
            "00000000000000",
            "11111111111111",
            "22222222222222",
            "33333333333333",
            "44444444444444",
            "55555555555555",
            "66666666666666",
            "77777777777777",
            "88888888888888",
            "99999999999999"
    );

    /**
     * check validation of CPF
     */
    public static boolean isValidCPF(String cpf) {
        if (!TextUtils.isEmpty(cpf) && cpf.matches(Formats.CPF_REGEX)) {
            try {
                String cleanCpf = cpf.replaceAll("\\D", "");
                if (!INVALID_CPFs.contains(cleanCpf)) {
                    // first check
                    if (validateCPFOrCNPJ(cleanCpf, 10, true, true))
                        // second check
                        if (validateCPFOrCNPJ(cleanCpf, 11, false, true))
                            return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * check validation of CNPJ
     */
    public static boolean isValidCNPJ(String cnpj) {
        if (!TextUtils.isEmpty(cnpj) && cnpj.matches(Formats.CNPJ_REGEX)) {
            try {
                String cleanCNPJ = cnpj.replaceAll("\\D", "");
                if (!INVALID_CNPJs.contains(cleanCNPJ)) {
                    // first check
                    if (validateCPFOrCNPJ(cleanCNPJ, 5, true, false))
                        // second check
                        if (validateCPFOrCNPJ(cleanCNPJ, 6, false, false))
                            return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static int getNumber(String value, int index) {
        return Integer.parseInt(value.substring(index, index + 1));
    }

    /**
     * validate CPF/CNPJ based on algorithm
     *
     * @param value            value to validate
     * @param multipliedNumber multiplied number
     * @param firstStep        checking the first digit
     * @param isCPF            either CPF or CNPJ
     * @return validated ot not
     */
    private static boolean validateCPFOrCNPJ(String value, int multipliedNumber, boolean firstStep, boolean isCPF) {
        int number = 0;
        int baseNumber = 11;
        int totalSteps;

        if (firstStep)
            totalSteps = value.length() - 2;
        else
            totalSteps = value.length() - 1;

        for (int i = 0; i < totalSteps; i++) {
            if (!isCPF && multipliedNumber < 2) multipliedNumber = 9;
            number += multipliedNumber * getNumber(value, i);
            multipliedNumber--;
        }
        int remainder = number % baseNumber;

        int checkDigit = 0;
        if (remainder >= 2) checkDigit = baseNumber - remainder;

        int numberToBeChecked = getNumber(value, totalSteps);
        return checkDigit == numberToBeChecked;
    }

    /**
     * check validation of CEP (Postal Code)
     */
    public static boolean isValidFormatCEP(String cep) {
        return !TextUtils.isEmpty(cep) && cep.matches(Formats.CEP_REGEX);
    }
}
