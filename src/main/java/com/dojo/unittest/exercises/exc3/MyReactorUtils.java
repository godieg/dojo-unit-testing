package com.dojo.unittest.exercises.exc3;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class MyReactorUtils {


    /**
     * 1. Crear un Stream de datos de tipo String con 1 elemento, es necesario que este dato
     * se transforme a mayusculas.
     */
    public Mono<String> mapToUpperCase(String value) {
        return Mono.empty();
    }

    /**
     * 2. Crear un Stream de datos de tipo String con 10 nombres y filtrar aquellos nombres que
     * tengan más de 3 letras, después obtener la longitud del nombre y filtrar aquellos nombres
     * donde su longitud sea impar.
     */
    public Flux<String> filterOddNameLength(List<String> names) {
        return Flux
                .fromIterable(names)
                .filter(name -> name.length() >= 3
                        && name.length() % 2 != 0);
    }

    /**
     * 3. Crear un Stream de datos de tipo Double con 1 elemento. El metodo debe permitir realizar la division
     * entre dos numeros y en caso de error se debe retornar por defecto el valor cero.
     */
    public Mono<Double> divide(Double x, Double y) {
        return Mono.just(x / y);
    }

    public Mono<Double> myDivideFunctionMono(Double x, Double y) {
        try {
            return Mono.just(x / y);
        } catch (Exception e) {
            return Mono.error(new RuntimeException(e));
        }
    }

    public Double myDivideFunctionSupplier(Double x, Double y) {
        return x / y;
    }

}
