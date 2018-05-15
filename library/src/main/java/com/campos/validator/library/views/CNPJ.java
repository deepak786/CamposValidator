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

package com.campos.validator.library.views;

import android.content.Context;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;

import com.campos.validator.library.androidmask.MaskEditTextChangedListener;
import com.campos.validator.library.utility.Formats;

public class CNPJ extends android.support.v7.widget.AppCompatEditText {

    public CNPJ(Context context) {
        super(context);
        addWatcher();
    }

    public CNPJ(Context context, AttributeSet attrs) {
        super(context, attrs);
        addWatcher();
    }

    public CNPJ(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addWatcher();
    }

    /**
     * add watcher
     */
    private void addWatcher() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
        setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        this.addTextChangedListener(new MaskEditTextChangedListener(Formats.FORMAT_CNPJ, this));
    }
}