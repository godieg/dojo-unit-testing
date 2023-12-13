package com.dojo.unittest.exercises.exc1;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MyTemperatureConverter {

    public static int fahrenheitToCelsius(int temperature) {
        return (temperature - 32) * 5 / 9;
    }

    public static int celsiusToFahrenheit(int temperature) {
        return (temperature * 9 / 5) + 32;
    }

}
