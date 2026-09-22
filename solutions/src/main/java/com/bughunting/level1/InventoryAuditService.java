package com.bughunting.level1;

/**
 * SOLUCIÓN - Ejercicio 1.1 (Nivel 1 - 50 pts)
 *
 * BUG ORIGINAL:
 * El bucle `for` utilizaba la condición de parada `i < stockLevels.length - 1`, lo cual omitía
 * evaluar el último elemento del arreglo (típico error 'off-by-one').
 * Además, en arreglos de 1 solo elemento, el bucle ni siquiera se ejecutaba.
 *
 * CORRECCIÓN:
 * Cambiar la condición a `i < stockLevels.length` para recorrer todos los índices válidos [0 .. N-1].
 */
public class InventoryAuditService {

    public int countUnderstockedShelves(int[] stockLevels, int minThreshold) {
        if (stockLevels == null || stockLevels.length == 0) {
            return 0;
        }

        int understockedCount = 0;

        // Corrección: Recorremos hasta la última posición válida (i < stockLevels.length)
        for (int i = 0; i < stockLevels.length; i++) {
            if (stockLevels[i] < minThreshold) {
                understockedCount++;
            }
        }

        return understockedCount;
    }
}
