/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.converter;

import com.fasterxml.jackson.core.SerializableString;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 *
 * @author jittagornp
 */
public class SerializableStringImpl implements SerializableString {

    private final String escape;

    private final int length;

    private final char[] charArray;

    private final byte[] byteArray;

    private final char character;

    public SerializableStringImpl(String escape, int length, char[] charArray, byte[] byteArray, char character) {
        this.escape = escape;
        this.length = length;
        this.charArray = charArray;
        this.byteArray = byteArray;
        this.character = character;
    }

    @Override
    public String getValue() {
        return escape;
    }

    @Override
    public int charLength() {
        return length;
    }

    @Override
    public char[] asQuotedChars() {
        return charArray;
    }

    @Override
    public byte[] asUnquotedUTF8() {
        return byteArray;
    }

    @Override
    public byte[] asQuotedUTF8() {
        return byteArray;
    }

    @Override
    public int appendQuotedUTF8(byte[] bytes, int i) {
        return character;
    }

    @Override
    public int appendQuoted(char[] chars, int i) {
        return character;
    }

    @Override
    public int appendUnquotedUTF8(byte[] bytes, int i) {
        return character;
    }

    @Override
    public int appendUnquoted(char[] chars, int i) {
        return character;
    }

    @Override
    public int writeQuotedUTF8(OutputStream out) throws IOException {
        return character;
    }

    @Override
    public int writeUnquotedUTF8(OutputStream out) throws IOException {
        return character;
    }

    @Override
    public int putQuotedUTF8(ByteBuffer bb) throws IOException {
        return character;
    }

    @Override
    public int putUnquotedUTF8(ByteBuffer bb) throws IOException {
        return character;
    }

}
