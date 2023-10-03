package com.dojo.unittest.lvl1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void testAdd() {

        // Arrange
        Calculator calculator = new Calculator(1, 2);

        // Act
        double result = calculator.add();

        //Assert
        Assertions.assertEquals(3, result);
    }

}
