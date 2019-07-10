package com.github.xqm;

import org.junit.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;


public class LocaleUtilsTest {

    @Test
    public void should_return_null_when_str_is_null() {
        //given
        String testStr = null;
        //when
        Locale res = new LocaleUtils().toLocale(testStr);
        //then
        assertThat(res).isNull();
    }

}