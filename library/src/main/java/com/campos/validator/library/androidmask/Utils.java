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

package com.campos.validator.library.androidmask;


import java.util.Set;

public abstract class Utils {

    public static String unmask(String s, Set<String> replaceSymbols) {

        for (String symbol : replaceSymbols)
            s = s.replaceAll("[" + symbol + "]", "");

        return s;
    }

    public static String mask(String format, String text) {
        StringBuilder maskedText = new StringBuilder();
        int i = 0;
        for (char m : format.toCharArray()) {
            if (m != '#') {
                maskedText.append(m);
                continue;
            }
            try {
                maskedText.append(text.charAt(i));
            } catch (Exception e) {
                break;
            }
            i++;
        }
        return maskedText.toString();
    }
}