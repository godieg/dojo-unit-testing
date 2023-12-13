package com.dojo.unittest.examples.exercises.exc2.domain;

import com.dojo.unittest.exercises.exc2.domain.OrderUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {

    private OrderUseCase orderUseCase;

    @Test
    void shouldCallDummyTest() {
        assertEquals(1 + 1, 2);
        assertThat(1 + 1).isEqualTo(2);
    }

    /**
     * Construir el conjunto de pruebas unitarias suficientes para la clase OrderUseCase
     * Implementar la misma prueba utilizando JUnit5 y assertJ
     *
     * La finalidad de las pruebas sobre OrderUseCase no es probar el comportamiento de los Gateway que necesita.
     */
}
