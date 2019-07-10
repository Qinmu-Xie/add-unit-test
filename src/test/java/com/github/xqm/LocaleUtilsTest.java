package com.github.xqm;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;


public class LocaleUtilsTest {
    private LocaleUtils localUtils;
    private String testString;
    private static String EMPTY = "";

    @Before
    public void setUp() throws Exception {
        localUtils = new LocaleUtils();
    }

    @Test
    public void should_return_null_when_string_is_null() {
        //given
        testString = null;
        //when
        Locale res = localUtils.toLocale(testString);
        //then
        assertThat(res).isNull();
    }

    @Test
    public void should_return_empty_when_string_is_empty() {
        //given
        testString = "";
        //when
        Locale res = localUtils.toLocale(testString);
        //then
        assertThat(res).isEqualTo(new Locale(EMPTY, EMPTY));

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_illegal_argument_exception_when_string_contains_special_char() {
        //given
        testString = "a#b";
        //when
        localUtils.toLocale(testString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_illegal_argument_exception_when_string_contains_less_than_2_chars() {
        //given
        testString = "a";
        //when
        localUtils.toLocale(testString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_illegal_argument_exception_when_crossbar_string_less_than_3_chars() {
        //given
        testString = "_b";
        //when
        localUtils.toLocale(testString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_get_illegal_argument_exception_when_crossbar_string_not_start_with_2_uppercase_char() {
        //given
        testString = "_bbb";
        //when
        localUtils.toLocale(testString);
    }

    @Test
    public void should_return_sub_string_when_crossbar_string_length_of_3() {
        //given
        testString = "_AB";
        //when
        Locale res = localUtils.toLocale(testString);
        //then
        assertThat(res).isEqualTo(new Locale(EMPTY, "AB"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_sub_string_when_crossbar_string_less_than_length_5() {
        //given
        testString = "_ABA";
        //when
        localUtils.toLocale(testString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_sub_string_when_crossbar_string_contains_crossbar_at_char_3() {
        //given
        testString = "_ABsdf";
        //when
        localUtils.toLocale(testString);
    }

    @Test
    public void should_return_local_of_correct_crossbar_string() {
        //given
        testString = "_AB_CD";
        //when
        Locale res = localUtils.toLocale(testString);
        //then
        assertThat(res).isEqualTo(new Locale(EMPTY, "AB", "CD"));

    }

    @Test
    public void should_return_local_of_ISO639_string_length_2() {
        //given
        testString = "bb";
        //when
        Locale res = localUtils.toLocale(testString);
        //then
        assertThat(res).isEqualTo(new Locale(testString));

    }

}