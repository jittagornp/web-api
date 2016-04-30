/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.converter;

/**
 *
 * @author jittagornp
 */
public class DoubleQuoteSerializableString extends SerializableStringImpl {

    public DoubleQuoteSerializableString() {
        super(
                "\\\"",
                2,
                new char[]{'\\', '"'},
                new byte[]{'\\', '"'},
                '"'
        );
    }

}
