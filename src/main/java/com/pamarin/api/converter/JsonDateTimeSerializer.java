/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.Objects.isNull;

/**
 *
 * @author jittagornp
 */
public class JsonDateTimeSerializer extends JsonSerializer<Date> {

    private final Format format = new SimpleDateFormat(DateTimePattern.STANDARD_PATTERN);

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        if (!isNull(value)) {
            gen.writeString(format.format(value));
        }
    }

}
