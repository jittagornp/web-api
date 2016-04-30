/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.pamarin.api.util.DateUtils;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 *
 * @author jittagornp
 */
public class JsonDateTimeSerializerTest {
    
    @Test
    public void shouldBeNeverCall() throws IOException, ParseException{
        
        JsonSerializer<Date> serializer = new JsonDateTimeSerializer();   
        JsonGenerator generator = mock(JsonGenerator.class); 
        
        Date date = null; 
        String expected = null;
                
        serializer.serialize(date, generator, null);
        
        verify(generator, never()).writeString(expected);
    }
    
    @Test
    public void shouldBeCallWriteString() throws IOException, ParseException{
        
        JsonSerializer<Date> serializer = new JsonDateTimeSerializer();   
        JsonGenerator generator = mock(JsonGenerator.class); 
        
        Date date = DateUtils.parse("01/01/2016", "dd/MM/yyyy"); 
        String expected = DateUtils.format(date);
                
        serializer.serialize(date, generator, null);
        
        verify(generator).writeString(expected);
    }
    
}
