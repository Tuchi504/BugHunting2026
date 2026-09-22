package com.bughunting.level2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verificación de resolución de ejercicio.
 */
public class HiddenCustomerLoyaltyServiceTest {

    private final CustomerLoyaltyService loyaltyService = new CustomerLoyaltyService();

    @Test
    @DisplayName("Cliente registrado con más de 1000 puntos obtiene 15% de descuento")
    void testLoyaltyDiscountForHighTierCustomer() {
        // CUST_101 está precargado con 1200 puntos
        int discount = loyaltyService.getLoyaltyDiscountPercentage("CUST_101");

        assertEquals(15, discount, "Cliente con más de 1000 puntos debe recibir 15% de descuento.");
    }

    @Test
    @DisplayName("Cliente nuevo no registrado debe recibir 0% sin lanzar NullPointerException")
    void testUnregisteredCustomerReturnsZeroWithoutNpe() {
        int discount = assertDoesNotThrow(
                () -> loyaltyService.getLoyaltyDiscountPercentage("CUST_UNKNOWN"),
                "Un cliente no registrado no debe lanzar NullPointerException por unboxing.");

        assertEquals(0, discount, "Cliente no registrado debe tener 0% de descuento por defecto.");
    }

    @Test
    @DisplayName("ID de cliente nulo debe retornar 0% de forma segura")
    void testNullCustomerIdReturnsZeroWithoutNpe() {
        int discount = assertDoesNotThrow(
                () -> loyaltyService.getLoyaltyDiscountPercentage(null),
                "Un ID de cliente null debe ser manejado de forma segura.");

        assertEquals(0, discount, "ID null debe resultar en 0% de descuento.");
    }

    @Test
    @DisplayName("Cliente con exactamente 500 puntos debe entrar en el rango de 10%")
    void testExact500PointsTier() {
        loyaltyService.registerCustomer("CUST_TIER2", 500);

        int discount = loyaltyService.getLoyaltyDiscountPercentage("CUST_TIER2");

        assertEquals(10, discount, "Cliente con exactamente 500 puntos debe recibir 10%.");
    }

    @Test
    @DisplayName("Cliente con menos de 500 puntos debe recibir 5%")
    void testLowTierCustomer() {
        int discount = loyaltyService.getLoyaltyDiscountPercentage("CUST_102"); // tiene 450 puntos

        assertEquals(5, discount, "Cliente con 450 puntos debe recibir 5%.");
    }
}
