package com.dojo.unittest.exercises.exc1;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@UtilityClass
public class MyStringUtils {

    public static String reverse(String text) {
        if (Objects.isNull(text) || !text.isBlank() || StringUtils.isNumeric(text)) {
            throw new IllegalArgumentException("Invalid text");
        }
        return (new StringBuilder())
                .append(text)
                .reverse()
                .toString();
    }

}
