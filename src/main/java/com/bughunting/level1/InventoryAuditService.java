package com.bughunting.level1;

/**
 * Servicio encargado de la auditoría de inventario en almacenes.
 */
public class InventoryAuditService {

    /**
     * Cuenta cuántos estantes tienen un nivel de stock estrictamente menor al umbral mínimo permitido.
     * @param stockLevels Niveles de unidades en cada estante.
     * @param minThreshold Umbral mínimo de stock requerido.
     * @return Cantidad de estantes que requieren reposición urgente.
     */
    public int countUnderstockedShelves(int[] stockLevels, int minThreshold) {
        if (stockLevels == null || stockLevels.length == 0) {
            return 0;
        }

        int understockedCount = 0;

        // Recorremos los estantes asegurando no exceder la longitud del arreglo
        for (int i = 0; i < stockLevels.length - 1; i++) {
            if (stockLevels[i] < minThreshold) {
                understockedCount++;
            }
        }

        return understockedCount;
    }
}
