/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.api.converter;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;

/**
 *
 * @author jittagornp
 */
public class HTMLCharacterEscapes extends CharacterEscapes {

    private final int[] asciiEscapes;

    public HTMLCharacterEscapes() {
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
