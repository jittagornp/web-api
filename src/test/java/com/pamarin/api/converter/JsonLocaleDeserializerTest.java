/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author jittagornp
 */
public class JsonLocaleDeserializerTest {

    @Test
    public void shouldBeNull_whenLocaleCodeIsNull() throws IOException {

        JsonDeserializer<Locale> deserializer = new JsonLocaleDeserializer();
        JsonParser parser = mock(JsonParser.class);

        String localeCode = null;
        when(parser.getText()).thenReturn(localeCode);

        Locale locale = deserializer.deserialize(parser, null);
        assertThat(locale).isNull();

    }

    @Test
    public void shouldBeNull_whenLocaleCodeIsEmptyString() throws IOException {

        JsonDeserializer<Locale> deserializer = new JsonLocaleDeserializer();
        JsonParser parser = mock(JsonParser.class);

        String localeCode = "";
        when(parser.getText()).thenReturn(localeCode);

        Locale locale = deserializer.deserialize(parser, null);
        assertThat(locale).isNull();

    }

    @Test(expected = IOException.class)
    public void shouldBeThrowIOException_whenLocaleIs_() throws IOException {

        JsonDeserializer<Locale> deserializer = new JsonLocaleDeserializer();
        JsonParser parser = mock(JsonParser.class);

        String localeCode = "_";
        when(parser.getText()).thenReturn(localeCode);

        deserializer.deserialize(parser, null);

    }

    @Test(expected = IOException.class)
    public void shouldBeThrowIOException_whenLocaleIs__th_() throws IOException {

        JsonDeserializer<Locale> deserializer = new JsonLocaleDeserializer();
        JsonParser parser = mock(JsonParser.class);

        String localeCode = "th_";
        when(parser.getText()).thenReturn(localeCode);

        deserializer.deserialize(parser, null);

    }

    @Test(expected = IOException.class)
    public void shouldBeThrowIOException_whenLocaleIs___TH() throws IOException {

        JsonDeserializer<Locale> deserializer = new JsonLocaleDeserializer();
        JsonParser parser = mock(JsonParser.class);

        String localeCode = "_TH";
        when(parser.getText()).thenReturn(localeCode);

        deserializer.deserialize(parser, null);

    }

    @Test
    public void shouldBeOk_whenLocaleIs__th_TH() throws IOException {

        JsonDeserializer<Locale> deserializer = new JsonLocaleDeserializer();
        JsonParser parser = mock(JsonParser.class);

        String localeCode = "th_TH";
        when(parser.getText()).thenReturn(localeCode);

        Locale locale = deserializer.deserialize(parser, null);
        assertThat(locale).isEqualTo(new Locale("th", "TH"));
    }

    @Test
    public void shouldBeOk_whenLocaleIs__en_US() throws IOException {

        JsonDeserializer<Locale> deserializer = new JsonLocaleDeserializer();
        JsonParser parser = mock(JsonParser.class);

        String localeCode = "en_US";
        when(parser.getText()).thenReturn(localeCode);

        Locale locale = deserializer.deserialize(parser, null);
        assertThat(locale).isEqualTo(Locale.US);

    }
}
