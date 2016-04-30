/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.util;

import com.pamarin.api.converter.DateTimeFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jittagornp
 */
public class DateUtils {

    private DateUtils() {

    }

    public static Date parse(String date, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(date);
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date parse(String date) throws ParseException {
        return parse(date, DateTimeFormat.DEFAULT);
    }

    public static String format(Date date) {
        return format(date, DateTimeFormat.DEFAULT);
    }
}
