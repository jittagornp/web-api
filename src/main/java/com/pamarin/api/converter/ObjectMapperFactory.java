/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author jittagornp
 */
public class ObjectMapperFactory {

    private ObjectMapperFactory() {

    }

    public static ObjectMapper createObjectMapper() {

        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.getFactory().setCharacterEscapes(new StringCharacterEscapes());

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Date.class, new JsonDateTimeSerializer());
        simpleModule.addDeserializer(Date.class, new JsonDateTimeDeserializer());
        simpleModule.addSerializer(Locale.class, new JsonLocaleSerializer());
        simpleModule.addDeserializer(Locale.class, new JsonLocaleDeserializer());

        mapper.registerModule(simpleModule);

        return mapper;
    }

}
