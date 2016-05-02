/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.converter;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;

/**
 *
 * @author jittagornp
 */
public class StringCharacterEscapes extends CharacterEscapes {

    private final int[] asciiEscapes;

    public StringCharacterEscapes() {
        // start with set of characters known to require escaping (double-quote, backslash etc)
        int[] esc = CharacterEscapes.standardAsciiEscapesForJSON();
        esc['/'] = CharacterEscapes.ESCAPE_CUSTOM;
        esc['"'] = CharacterEscapes.ESCAPE_CUSTOM;

        asciiEscapes = esc;
    }

    // this method gets called for character codes 0 - 127
    @Override
    public int[] getEscapeCodesForAscii() {
        return asciiEscapes;
    }

    // and this for others; we don't need anything special here
    @Override
    public SerializableString getEscapeSequence(int ch) {
        // no further escaping (beyond ASCII chars) needed:

        if (ch == '/') {
            return new SlashSerializableString();
        }
        
        if(ch == '"'){
            return new DoubleQuoteSerializableString();
        }

        return null;
    }
}
