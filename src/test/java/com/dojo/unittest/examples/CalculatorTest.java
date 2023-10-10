package com.dojo.unittest.examples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

public class CalculatorTest {

    /*

    • preconditions of the test - the state of the SUT and the environment before the test,
    • triggering actions - what makes the SUT act in the expected way,
    • expected results of the test - what should be returned by the SUT, or what state the SUT should be in
        after the test is finished, or what actions should be performed by the SUT during the test

    should_NotAccept_Password_WithoutDigits()
    should_NotAccept_ShortPasswords()
    should_BeEmpty_AfterCreation()
    should_Allow_ToCreditAccount()
    should_Allow_ToDebitAccount()

     */
    @Test
    void shouldAddTwoNumbers() {

        // Arrange -> Given
        Calculator calculator = new Calculator();

        // Act -> When
        double result = calculator.add(1, 2);

        //Assert -> Then
        Assertions.assertEquals(3, result);

        //AssertJ -> Then
        assertThat(result)
                .as("Add 1 + 2  should be 3")
                .isEqualTo(3)
                .isGreaterThan(2)
                .isFinite();
    }

    @Test
    void shouldThrowExceptionDivideByZero() {
        // Arrange -> Given
        Calculator calculator = new Calculator();

        // Assert -> Then
        Assertions.assertThrows(ArithmeticException.class, () -> {
            // Act -> When
            calculator.divide(1, 0);
        });

        assertThatThrownBy(() -> calculator.divide(1, 0))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("Cannot divide by zero");
    }

    @Test
    void shouldGetInfinityWithDivideByZero() {
        // Arrange -> Given
        Calculator calculator = new Calculator();

        // Assert -> Then
        double result = calculator.divideInfinity(1, 0);

        // Act -> When
        Assertions.assertTrue(Double.isInfinite(result));

        assertThat(result)
                .as("Divide 1 / 0  should be Infinity")
                .isEqualTo(Double.POSITIVE_INFINITY)
                .isPositive()
                .isInfinite();
    }


    // Parameterized Tests
    @ParameterizedTest
    @ValueSource(doubles = {1, 2, 3, 4, 5})
    void shouldAddaOneToParamValue(double x){
        // Arrange -> Given
        Calculator calculator = new Calculator();

        // Act -> When
        double result = calculator.add(x, 1);

        //Assert -> Then
        Assertions.assertEquals(x + 1, result);

        assertThat(result)
                .as("Add %s + 1  should be %s", x, x + 1)
                .isEqualTo(x + 1)
                .isGreaterThan(x);
    }


    @ParameterizedTest
    @MethodSource("provideDoubleValues")
    void shouldAddTwoNumbersParamValues(double x, double y, double expected) {
        // Arrange -> Given
        Calculator calculator = new Calculator();

        // Act -> When
        double result = calculator.add(x, y);

        //Assert -> Then
        Assertions.assertEquals(expected, result);

        assertThat(result)
                .as("Add %s + %s  should be %s", x, y, expected)
                .isEqualTo(expected);
    }

    private static Stream<Arguments> provideDoubleValues() {
        return Stream.of(
                Arguments.of(1, 2, 3),
                Arguments.of(55, 99, 154),
                Arguments.of(33, 0, 33),
                Arguments.of(-1, 4, 3),
                Arguments.of(-1, -1, -2),
                Arguments.of(-5, 5, 0)
        );
    }

}
