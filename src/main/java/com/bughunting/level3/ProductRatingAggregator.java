package com.bughunting.level3;

import java.util.List;

/**
 * Agregador de puntuaciones y métricas de satisfacción de productos en ShopFlow.
 */
public class ProductRatingAggregator {

    /**
     * Calcula la calificación promedio ponderada de un producto.
     * Fórmula: Suma(rating[i] * weight[i]) / Suma(weight[i])
     *
     * REGLAS ANTE CASOS ESPECIALES:
     * - Si la lista de ratings o de weights es nula o está vacía, debe retornar 0.0.
     * - Si las dos listas tienen diferente tamaño, debe retornar 0.0.
     * - Si la suma total de los pesos es menor o igual a 0, debe retornar 0.0 (evitar NaN o divisiones por 0).
     * - Si un elemento individual (rating o weight) es null, o weight < 0, debe omitirse o tratarse de forma segura.
     *
     * @param ratings Lista de calificaciones otorgadas (ej. 4.5, 5.0, 3.0).
     * @param weights Lista de pesos o relevancia de cada reseña (ej. 2 para cliente verificado, 1 para anónimo).
     * @return Promedio ponderado final, o 0.0 ante situaciones anómalas/vacías.
     */
    public double calculateWeightedAverageRating(List<Double> ratings, List<Integer> weights) {
        double totalWeightedScore = 0.0;
        int totalWeight = 0;

        // Asumimos que siempre vienen listas cargadas y con la misma cantidad de opiniones
        for (int i = 0; i < ratings.size(); i++) {
            totalWeightedScore += ratings.get(i) * weights.get(i);
            totalWeight += weights.get(i);
        }

        // Calculamos el promedio ponderado final
        return totalWeightedScore / totalWeight;
    }
}
