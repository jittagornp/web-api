/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.converter;

import java.io.IOException;
import java.nio.charset.Charset;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author jittagornp
 */
public class JsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private ObjectMapper objectMapper;

    public JsonHttpMessageConverter() {
        /*
         * MediaType("*", "*") protect url with extension
         * can access all extensions as json format
         */

        super(new MediaType("application", "json", DEFAULT_CHARSET));
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        try (InputStream inputStream = inputMessage.getBody()) {
            return getObjectMapper().readValue(
                    inputStream,
                    clazz
            );
        } catch (IOException ex) {
            throw new HttpMessageNotReadableException("can't parse json : " + ex.getMessage(), ex);
        }
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        try (OutputStream inputStream = outputMessage.getBody()) {
            getObjectMapper().writeValue(
                    inputStream,
                    object
            );
        } catch (IOException ex) {
            throw new HttpMessageNotWritableException("can't write object to json : " + ex.getMessage(), ex);
        }
    }
}
