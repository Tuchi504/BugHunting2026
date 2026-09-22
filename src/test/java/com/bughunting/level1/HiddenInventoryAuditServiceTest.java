package com.bughunting.level1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verificación de resolución de ejercicio.
 */
public class HiddenInventoryAuditServiceTest {

    private final InventoryAuditService auditService = new InventoryAuditService();

    @Test
    @DisplayName("Detecta estante con stock bajo al inicio de la lista")
    void testCountUnderstockedShelvesNormalCase() {
        int[] stockLevels = {4, 25, 40};
        int minThreshold = 10;

        int result = auditService.countUnderstockedShelves(stockLevels, minThreshold);

        // El primer estante (índice 0) tiene 4 unidades, menor que 10
        assertEquals(1, result, "Debe detectar 1 estante con stock insuficiente.");
    }

    @Test
    @DisplayName("Estante con stock bajo ubicado en la última posición del arreglo.")
    void testUnderstockedAtLastIndex() {
        int[] stockLevels = {20, 30, 40, 3}; // Último elemento (3) está por debajo de 10
        int minThreshold = 10;

        int result = auditService.countUnderstockedShelves(stockLevels, minThreshold);

        assertEquals(1, result, "Debe contabilizar el estante en la última posición del arreglo.");
    }

    @Test
    @DisplayName("Arreglo de un solo elemento con stock bajo")
    void testSingleElementArray() {
        int[] stockLevels = {2};
        int minThreshold = 5;

        int result = auditService.countUnderstockedShelves(stockLevels, minThreshold);

        assertEquals(1, result, "Un arreglo de 1 solo elemento bajo el umbral debe retornar 1.");
    }

    @Test
    @DisplayName("Arreglo vacío o nulo debe retornar 0 sin errores")
    void testEmptyAndNullArray() {
        assertEquals(0, auditService.countUnderstockedShelves(new int[]{}, 10));
        assertEquals(0, auditService.countUnderstockedShelves(null, 10));
    }
}