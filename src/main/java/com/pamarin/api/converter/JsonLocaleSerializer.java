/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.api.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Locale;
import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

/**
 *
 * @author jittagornp
 */
public class JsonLocaleSerializer extends JsonSerializer<Locale> {

    @Override
    public void serialize(Locale value, JsonGenerator gen, SerializerProvider sp) throws IOException, JsonProcessingException {
        
        if (isNull(value)) {
            return;
        }

        if (!hasText(value.getLanguage())) {
            throw new IOException("not support locale, require language");
        }

        if (!hasText(value.getCountry())) {
            throw new IOException("not support locale, require country");
        }

        String localeCode = value.getLanguage() + "_" + value.getCountry();
        gen.writeString(localeCode);
    }

}
