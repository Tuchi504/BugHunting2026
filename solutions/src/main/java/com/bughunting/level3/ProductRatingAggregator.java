package com.bughunting.level3;

import java.util.List;

/**
 * SOLUCIÓN - Ejercicio 3.2 (Nivel 3 - 150 pts)
 *
 * BUGS ORIGINALES:
 * 1. Entradas nulas (`ratings == null` o `weights == null`) lanzaban `NullPointerException`.
 * 2. Entradas vacías (`ratings.isEmpty()`) resultaban en `0.0 / 0`, produciendo `Double.NaN`.
 * 3. Suma total de pesos igual a 0 provocaba división por cero (`NaN`).
 * 4. Listas de diferentes longitudes provocaban `IndexOutOfBoundsException`.
 * 5. Elementos `null` dentro de la lista lanzaban `NullPointerException` al desempacarse (unboxing).
 *
 * CORRECCIÓN:
 * Implementar validaciones defensivas completas en la cabecera del método y dentro del bucle,
 * verificando nulls y divisiones por cero antes de retornar.
 */
public class ProductRatingAggregator {

    public double calculateWeightedAverageRating(List<Double> ratings, List<Integer> weights) {
        // Corrección: Validar listas nulas o vacías
        if (ratings == null || weights == null || ratings.isEmpty() || weights.isEmpty()) {
            return 0.0;
        }

        // Corrección: Validar consistencia de dimensiones
        if (ratings.size() != weights.size()) {
            return 0.0;
        }

        double totalWeightedScore = 0.0;
        int totalWeight = 0;

        for (int i = 0; i < ratings.size(); i++) {
            Double rating = ratings.get(i);
            Integer weight = weights.get(i);

            // Corrección: Omitir elementos nulos o pesos negativos
            if (rating != null && weight != null && weight > 0) {
                totalWeightedScore += rating * weight;
                totalWeight += weight;
            }
        }

        // Corrección: Evitar división por cero si la suma total de pesos es 0
        if (totalWeight <= 0) {
            return 0.0;
        }

        return totalWeightedScore / totalWeight;
    }
}
