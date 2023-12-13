package com.dojo.unittest.examples.exercises.exc1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyStringUtilsTest {

    @Test
    void shouldCallDummyTest() {
        assertEquals(1 + 1, 2);
        assertThat(1 + 1).isEqualTo(2);
    }

    /**
     * Construir una prueba unitaria que permita verificar a partir de una lista de valores su valor al contrario.
     * Implementar la misma prueba utilizando JUnit5 y assertJ
     */
    @Test
    void shouldReverseStringValue() {

    }

    /**
     * Construir una prueba unitaria que permita verificar a partir de una lista de valores si el valor es valido o no.
     * Implementar la misma prueba utilizando JUnit5 y assertJ
     */
    @Test
    void shouldThrowException_withInvalidData() {

    }

}
