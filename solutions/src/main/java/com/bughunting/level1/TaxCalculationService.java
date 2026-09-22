package com.bughunting.level1;

/**
 * SOLUCIÓN - Ejercicio 1.3 (Nivel 1 - 50 pts)
 *
 * BUG ORIGINAL:
 * `taxPercentage / 100` realizaba una división entera entre dos primitivos `int`.
 * Como cualquier porcentaje menor a 100 (ej. 16, 21, 8) produce un cociente menor a 1,
 * Java truncaba el resultado a `0`, haciendo que `subtotal * 0` siempre devolviera `0.0`.
 *
 * CORRECCIÓN:
 * Realizar división en punto flotante usando el literal `100.0` (o haciendo cast a `double`).
 */
public class TaxCalculationService {

    public double calculateTax(double subtotal, int taxPercentage) {
        if (subtotal <= 0 || taxPercentage <= 0) {
            return 0.0;
        }

        // Corrección: Usar 100.0 fuerza la división en punto flotante (double)
        double factor = taxPercentage / 100.0;

        return subtotal * factor;
    }
}
