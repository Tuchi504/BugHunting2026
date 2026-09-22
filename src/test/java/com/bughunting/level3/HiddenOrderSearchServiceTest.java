package com.bughunting.level3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verificación de resolución del ejercicio.
 */
public class HiddenOrderSearchServiceTest {

    private final OrderSearchService searchService = new OrderSearchService();

    @Test
    @DisplayName("Encuentra duplicados en una lista pequeña de tracking codes")
    void testFindDuplicatesSmallList() {
        List<String> orderCodes = List.of("TRK-100", "TRK-200", "TRK-100", "TRK-300", "TRK-200");

        List<String> duplicates = searchService.findDuplicateOrderCodes(orderCodes);

        assertEquals(2, duplicates.size(), "Debe encontrar exactamente 2 códigos duplicados.");
        assertTrue(duplicates.contains("TRK-100"));
        assertTrue(duplicates.contains("TRK-200"));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @DisplayName("Búsqueda de duplicados en 25,000 órdenes debe completarse en menos de 1 segundo (O(N))")
    void testPerformanceWithLargeOrderBatch() {
        int totalOrders = 25000;
        List<String> largeBatch = new ArrayList<>(totalOrders);

        // Generamos 25,000 códigos únicos
        for (int i = 0; i < totalOrders; i++) {
            largeBatch.add("ORD-TRACK-" + i);
        }

        // Insertamos duplicados intencionales
        largeBatch.add("ORD-TRACK-10");
        largeBatch.add("ORD-TRACK-500");
        largeBatch.add("ORD-TRACK-9999");

        List<String> duplicates = searchService.findDuplicateOrderCodes(largeBatch);

        assertEquals(3, duplicates.size(), "Debe identificar exactamente los 3 códigos duplicados.");
        assertTrue(duplicates.contains("ORD-TRACK-10"));
        assertTrue(duplicates.contains("ORD-TRACK-500"));
        assertTrue(duplicates.contains("ORD-TRACK-9999"));
    }

    @Test
    @DisplayName("Lista nula o vacía debe retornar una lista vacía sin fallar")
    void testEmptyAndNullLists() {
        assertTrue(searchService.findDuplicateOrderCodes(null).isEmpty(), "Entrada null debe retornar lista vacía.");
        assertTrue(searchService.findDuplicateOrderCodes(List.of()).isEmpty(), "Entrada vacía debe retornar lista vacía.");
    }
}