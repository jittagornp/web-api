/*
 *  Copyright 2016 Pamarin.com
 */
package com.pamarin.api.converter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pamarin.api.util.DateUtils;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jittagornp
 */
public class ObjectMapperTest {

    private static ObjectMapper objectMapper;

    private static class User {

        private String firstName;
        private String lastName;
        private Date birthDate;
        private String link;
        private Locale locale;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Locale getLocale() {
            return locale;
        }

        public void setLocale(Locale locale) {
            this.locale = locale;
        }

    }

    @BeforeClass
    public static void beforeClass() {
        objectMapper = ObjectMapperFactory.createObjectMapper();
    }

    @Test
    public void shouldBeFalseOnProppertyFAIL_ON_UNKNOWN_PROPERTIES() {

        boolean enabled = objectMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        assertThat(enabled).isFalse();

    }

    @Test(expected = NullPointerException.class)
    public void shouldBeThrowNullPointerException_whenJsonIsNull() throws IOException {

        String json = null;
        User user = objectMapper.readValue(json, User.class);

    }

    @Test(expected = JsonMappingException.class)
    public void shouldBeThrowJsonMappingException_whenJsonIsEmptyString() throws IOException {

        String json = "";
        User user = objectMapper.readValue(json, User.class);

    }

    @Test(expected = JsonMappingException.class)
    public void shouldBeThrowJsonMappingException_whenJsonIsEmptyArray() throws IOException {

        String json = "[]";
        User user = objectMapper.readValue(json, User.class);

    }

    @Test(expected = JsonParseException.class)
    public void shouldBeThrowJsonParseException_whenJsonIsString() throws IOException {

        String json = "string";
        User user = objectMapper.readValue(json, User.class);

    }

    @Test(expected = JsonMappingException.class)
    public void shouldBeThrowJsonMappingException_whenJsonIsNumber() throws IOException {

        String json = "0";
        User user = objectMapper.readValue(json, User.class);

    }

    @Test(expected = JsonMappingException.class)
    public void shouldBeThrowJsonMappingException_whenJsonIsBoolean() throws IOException {

        String json = "true";
        User user = objectMapper.readValue(json, User.class);

    }

    @Test
    public void shouldBeOk_whenJsonIsEmptyObject() throws IOException {

        String json = "{}";
        User user = objectMapper.readValue(json, User.class);

        assertThat(user.getFirstName()).isNull();
        assertThat(user.getLastName()).isNull();
        assertThat(user.getBirthDate()).isNull();
        assertThat(user.getLink()).isNull();
        assertThat(user.getLocale()).isNull();
    }

    @Test
    public void shouldBeOk_whenJsonIsFirstNameAndLastName() throws IOException {

        String json = "{ \"firstName\" : \"jittagorn\", \"lastName\" : \"pitak\" }";
        User user = objectMapper.readValue(json, User.class);

        assertThat(user.getFirstName()).isEqualTo("jittagorn");
        assertThat(user.getLastName()).isEqualTo("pitak");
        assertThat(user.getBirthDate()).isNull();
        assertThat(user.getLink()).isNull();
        assertThat(user.getLocale()).isNull();
    }

    @Test
    public void shouldBeOk_whenUnknowJsonProperties() throws IOException {

        String json = "{ \"username\" : \"jittagornp\" }";
        User user = objectMapper.readValue(json, User.class);

        assertThat(user.getFirstName()).isNull();
        assertThat(user.getLastName()).isNull();
        assertThat(user.getBirthDate()).isNull();
        assertThat(user.getLink()).isNull();
        assertThat(user.getLocale()).isNull();
    }

    @Test
    public void shouldBeOk_whenJsonIsBirthDate() throws IOException, ParseException {

        String bithDate = "2016-01-01T18:30:15.999Z";

        String json = "{ \"birthDate\" : \"" + bithDate + "\" }";
        User user = objectMapper.readValue(json, User.class);

        assertThat(user.getFirstName()).isNull();
        assertThat(user.getLastName()).isNull();
        assertThat(user.getBirthDate()).isEqualTo(DateUtils.parse(bithDate));
        assertThat(user.getLink()).isNull();
        assertThat(user.getLocale()).isNull();
    }

    @Test
    public void shouldBeOk_whenJsonIsLocale__th_TH() throws IOException, ParseException {

        String localeCode = "th_TH";

        String json = "{ \"locale\" : \"" + localeCode + "\" }";
        User user = objectMapper.readValue(json, User.class);

        assertThat(user.getFirstName()).isNull();
        assertThat(user.getLastName()).isNull();
        assertThat(user.getBirthDate()).isNull();
        assertThat(user.getLink()).isNull();
        assertThat(user.getLocale()).isEqualTo(new Locale("th", "TH"));
    }

    @Test
    public void shouldBeOk_whenJsonIsLocale__en_US() throws IOException, ParseException {

        String localeCode = "en_US";

        String json = "{ \"locale\" : \"" + localeCode + "\" }";
        User user = objectMapper.readValue(json, User.class);

        assertThat(user.getFirstName()).isNull();
        assertThat(user.getLastName()).isNull();
        assertThat(user.getBirthDate()).isNull();
        assertThat(user.getLink()).isNull();
        assertThat(user.getLocale()).isEqualTo(Locale.US);
    }

    @Test
    public void shouldBeNull_whenObjectIsNull() throws JsonProcessingException {

        Object obj = null;
        String json = objectMapper.writeValueAsString(obj);

        assertThat(json).isEqualTo("null");

    }

    @Test
    public void shouldBeEmptyString_whenObjectIsEmptyString() throws JsonProcessingException {

        Object obj = "";
        String json = objectMapper.writeValueAsString(obj);

        assertThat(json).isEqualTo("\"\"");

    }

    @Test
    public void shouldBeOk_whenUserIsFirstNameAndBirthDate() throws ParseException, JsonProcessingException {

        String bithDate = "2016-01-01T18:30:15.999Z";

        User user = new User();
        user.setFirstName("jittagorn");
        user.setBirthDate(DateUtils.parse(bithDate));

        String json = objectMapper.writeValueAsString(user);
        String expected = "{\"firstName\":\"jittagorn\",\"birthDate\":\"" + bithDate + "\"}";

        assertThat(json).isEqualTo(expected);
    }

    @Test
    public void shouldBeOk_whenLinkIsMyFacebookProfile() throws JsonProcessingException {

        User user = new User();
        user.setLink("https://www.facebook.com/jittagornp");

        String json = objectMapper.writeValueAsString(user);
        String expteced = "{\"link\":\"https:\\/\\/www.facebook.com\\/jittagornp\"}";

        assertThat(json).isEqualTo(expteced);
    }

    @Test
    public void shouldBeOk_whenLocaleIs__th_TH() throws JsonProcessingException {

        User user = new User();
        user.setLocale(new Locale("th", "TH"));

        String json = objectMapper.writeValueAsString(user);
        String expteced = "{\"locale\":\"th_TH\"}";

        assertThat(json).isEqualTo(expteced);
    }

    @Test
    public void shouldBeOk_whenLocaleIs__en_US() throws JsonProcessingException {

        User user = new User();
        user.setLocale(Locale.US);

        String json = objectMapper.writeValueAsString(user);
        String expteced = "{\"locale\":\"en_US\"}";

        assertThat(json).isEqualTo(expteced);
    }
}
