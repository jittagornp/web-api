/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.api.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.util.Locale;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 *
 * @author jittagornp
 */
public class JsonLocaleSerializerTest {

    @Test
    public void shouldBeNeverCall() throws IOException {

        JsonSerializer<Locale> serializer = new JsonLocaleSerializer();
        JsonGenerator generator = mock(JsonGenerator.class);

        Locale locale = null;
        String expected = null;

        serializer.serialize(locale, generator, null);

        verify(generator, never()).writeString(expected);
    }

    @Test(expected = IOException.class)
    public void shouldBeThrowIOException_whenLocaleIsEmptyString() throws IOException {

        JsonSerializer<Locale> serializer = new JsonLocaleSerializer();
        JsonGenerator generator = mock(JsonGenerator.class);

        Locale locale = new Locale("");
        String expected = null;

        serializer.serialize(locale, generator, null);

        verify(generator).writeString(expected);

    }

    @Test(expected = IOException.class)
    public void shouldBeThrowIOException_whenLocaleIsEmptyStringAndEmptyString() throws IOException {

        JsonSerializer<Locale> serializer = new JsonLocaleSerializer();
        JsonGenerator generator = mock(JsonGenerator.class);

        Locale locale = new Locale("", "");
        String expected = null;

        serializer.serialize(locale, generator, null);

        verify(generator).writeString(expected);

    }

    @Test
    public void shouldBeOk_whenLocaleIs__th_TH() throws IOException {

        JsonSerializer<Locale> serializer = new JsonLocaleSerializer();
        JsonGenerator generator = mock(JsonGenerator.class);

        Locale locale = new Locale("th", "TH");
        String expected = "th_TH";

        serializer.serialize(locale, generator, null);

        verify(generator).writeString(expected);

    }
}
