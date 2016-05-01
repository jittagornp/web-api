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
public class DateTimePatternTest {

    @Test
    public void shouldBeOk() {

        String input = DateTimePattern.STANDARD_PATTERN;
        String expected = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

        assertThat(input).isEqualTo(expected);
    }

}
