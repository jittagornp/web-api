/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.api.converter;

import java.util.Locale;
import org.junit.Test;

/**
 *
 * @author jittagornp
 */
public class LocaleTest {

    @Test
    public void showAll() {

        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            System.out.println("locale -> " + locale.getLanguage() + "_" + locale.getCountry());
        }

    }

}
