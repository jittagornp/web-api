/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.api.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;
import static org.springframework.util.StringUtils.hasText;

/**
 *
 * @author jittagornp
 */
public class JsonLocaleDeserializer extends JsonDeserializer<Locale> {

    @Override
    public Locale deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {

        String localeCode = jp.getText();
        if (!hasText(localeCode)) {
            return null;
        }

        String[] split = StringUtils.split(localeCode, "_");

        if (split.length != 2) {
            throw new IOException("not support locale");
        }

        if (!hasText(split[0])) {
            throw new IOException("not support locale");
        }

        if (!hasText(split[1])) {
            throw new IOException("not support locale");
        }

        return new Locale(split[0], split[1]);
    }

}
