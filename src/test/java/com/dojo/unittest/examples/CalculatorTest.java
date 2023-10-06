package com.dojo.unittest.examples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    }


}
