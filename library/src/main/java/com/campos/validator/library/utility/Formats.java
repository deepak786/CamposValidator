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

public class Formats {
    // CPF
    public static final String CPF_REGEX = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}";
    public static final String FORMAT_CPF = "###.###.###-##"; // 11

    // CNPJ
    public static final String CNPJ_REGEX = "[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}\\-?[0-9]{2}";
    public static final String FORMAT_CNPJ = "##.###.###/####-##"; // 14

    // CEP
    public static final String CEP_REGEX = "[0-9]{5}-?[0-9]{3}";
    public static final String FORMAT_CEP = "#####-###";
}
