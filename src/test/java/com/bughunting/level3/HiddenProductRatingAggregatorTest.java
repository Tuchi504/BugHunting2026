package com.bughunting.level3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verificación de resolución del ejercicio.
 */
public class HiddenProductRatingAggregatorTest {

    private final ProductRatingAggregator aggregator = new ProductRatingAggregator();

    @Test
    @DisplayName("Calcula promedio ponderado de calificaciones estándar")
    void testWeightedAverageNormal() {
        // Calificaciones: 4.0 (peso 2), 5.0 (peso 1), 3.0 (peso 1)
        // Suma ponderada: (4.0*2) + (5.0*1) + (3.0*1) = 8.0 + 5.0 + 3.0 = 16.0
        // Suma de pesos: 2 + 1 + 1 = 4
        // Promedio: 16.0 / 4 = 4.0
        List<Double> ratings = List.of(4.0, 5.0, 3.0);
        List<Integer> weights = List.of(2, 1, 1);

        double average = aggregator.calculateWeightedAverageRating(ratings, weights);

        assertEquals(4.0, average, 0.001, "El promedio ponderado debe ser exactamente 4.0.");
    }

    @Test
    @DisplayName("Listas vacías deben retornar 0.0 en lugar de NaN o ArithmeticException")
    void testEmptyListsReturnZero() {
        double result = assertDoesNotThrow(
                () -> aggregator.calculateWeightedAverageRating(List.of(), List.of()),
                "Listas vacías no deben arrojar excepción ni NaN.");

        assertEquals(0.0, result, 0.0001, "Listas vacías deben resultar en un promedio de 0.0.");
    }

    @Test
    @DisplayName("Argumentos nulos deben retornar 0.0 sin lanzar NullPointerException")
    void testNullInputsReturnZero() {
        assertDoesNotThrow(() -> {
            assertEquals(0.0, aggregator.calculateWeightedAverageRating(null, List.of(1)), 0.0001);
            assertEquals(0.0, aggregator.calculateWeightedAverageRating(List.of(4.0), null), 0.0001);
            assertEquals(0.0, aggregator.calculateWeightedAverageRating(null, null), 0.0001);
        }, "Argumentos nulos deben manejarse sin lanzar NullPointerException.");
    }

    @Test
    @DisplayName("Suma de pesos igual a cero debe retornar 0.0 para evitar división por cero (NaN)")
    void testZeroTotalWeightReturnsZero() {
        List<Double> ratings = List.of(5.0, 4.0);
        List<Integer> weights = List.of(0, 0); // Suma de pesos es 0

        double result = aggregator.calculateWeightedAverageRating(ratings, weights);

        assertEquals(0.0, result, 0.0001, "Cuando la suma de pesos es 0, debe retornar 0.0 y no NaN.");
    }

    @Test
    @DisplayName("Listas con tamaños dispares deben retornar 0.0 de forma segura")
    void testMismatchedListSizes() {
        List<Double> ratings = List.of(4.0, 5.0, 3.0);
        List<Integer> weights = List.of(1, 2); // Faltan pesos

        double result = assertDoesNotThrow(
                () -> aggregator.calculateWeightedAverageRating(ratings, weights),
                "Tamaños dispares no deben lanzar IndexOutOfBoundsException.");

        assertEquals(0.0, result, 0.0001, "Listas con dimensiones inconsistentes deben retornar 0.0.");
    }

    @Test
    @DisplayName("Elemento nulo dentro de la lista debe omitirse limpiamente")
    void testNullElementInsideList() {
        List<Double> ratings = new ArrayList<>();
        ratings.add(4.0);
        ratings.add(null);
        ratings.add(5.0);

        List<Integer> weights = new ArrayList<>();
        weights.add(1);
        weights.add(1);
        weights.add(1);

        double result = assertDoesNotThrow(
                () -> aggregator.calculateWeightedAverageRating(ratings, weights),
                "Un elemento null en la lista no debe quebrar la ejecución.");

        // Promedio esperado con los elementos válidos (4.0*1 + 5.0*1) / 2 = 4.5
        assertEquals(4.5, result, 0.0001, "Debe calcular el promedio ignorando o manejando el valor nulo.");
    }
}