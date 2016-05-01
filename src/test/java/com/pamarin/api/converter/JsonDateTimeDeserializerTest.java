/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.pamarin.api.util.DateUtils;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author jittagornp
 */
public class JsonDateTimeDeserializerTest {

    @Test(expected = IOException.class)
    public void shouldBeThrowIOException_whenDateIsNull() throws IOException {

        JsonDeserializer<Date> deserializer = new JsonDateTimeDeserializer();
        JsonParser parser = mock(JsonParser.class);

        String date = null;
        when(parser.getText()).thenReturn(date);

        deserializer.deserialize(parser, null);

    }

    @Test(expected = IOException.class)
    public void shouldBeThrowIOException_whenDateIsXXXX() throws IOException {

        JsonDeserializer<Date> deserializer = new JsonDateTimeDeserializer();
        JsonParser parser = mock(JsonParser.class);

        String date = "XXXX";
        when(parser.getText()).thenReturn(date);

        deserializer.deserialize(parser, null);

    }

    @Test
    public void shouldBeOk() throws IOException, ParseException {

        JsonDeserializer<Date> deserializer = new JsonDateTimeDeserializer();
        JsonParser parser = mock(JsonParser.class);

        String date = "2016-01-01T00:00:00.000Z";
        when(parser.getText()).thenReturn(date);

        Date output = deserializer.deserialize(parser, null);
        assertThat(output).isEqualTo(DateUtils.parse(date));

    }
}
