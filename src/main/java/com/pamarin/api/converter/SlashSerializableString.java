/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.converter;

/**
 *
 * @author jittagornp
 */
public class SlashSerializableString extends SerializableStringImpl {

    public SlashSerializableString() {
        super(
                "\\/",
                2,
                new char[]{'\\', '/'},
                new byte[]{'\\', '/'},
                '/'
        );
    }
}
