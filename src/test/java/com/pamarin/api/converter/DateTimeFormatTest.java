/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.converter;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

/**
 *
 * @author jittagornp
 */
public class DateTimeFormatTest {

    @Test
    public void shouldBeOk() {

        String input = DateTimeFormat.DEFAULT;
        String expected = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

        assertThat(input).isEqualTo(expected);
    }

}
