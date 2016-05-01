/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jittagornp
 */
public class JsonDateTimeDeserializer extends JsonDeserializer<Date> {

    private final SimpleDateFormat format = new SimpleDateFormat(DateTimePattern.STANDARD_PATTERN);

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext dc) throws IOException, JsonProcessingException {
        String date = jsonParser.getText();
        try {
            return format.parse(date);
        } catch (ParseException | NullPointerException ex) {
            throw new IOException("Can't parse date.", ex);
        }
    }

}
