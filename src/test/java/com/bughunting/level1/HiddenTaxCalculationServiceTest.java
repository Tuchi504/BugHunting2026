package com.bughunting.level1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verificación de resolución de ejercicio.
 */
public class HiddenTaxCalculationServiceTest {

    private final TaxCalculationService taxService = new TaxCalculationService();

    @Test
    @DisplayName("Compra con tasa cero genera 0.0 de impuestos")
    void testZeroTaxPercentage() {
        double subtotal = 100.0;
        int taxPercentage = 0;

        double tax = taxService.calculateTax(subtotal, taxPercentage);

        assertEquals(0.0, tax, 0.001, "Una tasa de 0% debe generar 0.0 de impuesto.");
    }

    @Test
    @DisplayName("Tasa estándar del 16% sobre $100.0 debe calcular exactamente $16.0")
    void testStandardVatTax() {
        double subtotal = 100.0;
        int taxPercentage = 16;

        double result = taxService.calculateTax(subtotal, taxPercentage);

        assertEquals(16.0, result, 0.001, "El 16% de $100.0 debe ser $16.0");
    }

    @Test
    @DisplayName("Tasa del 21% sobre $250.0 debe calcular exactamente $52.5")
    void testTwentyOnePercentTax() {
        double subtotal = 250.0;
        int taxPercentage = 21;

        double result = taxService.calculateTax(subtotal, taxPercentage);

        assertEquals(52.5, result, 0.001, "El 21% de $250.0 debe ser $52.5.");
    }

    @Test
    @DisplayName("Subtotal o tasa negativa debe retornar 0.0")
    void testNegativeInputs() {
        assertEquals(0.0, taxService.calculateTax(-100.0, 16), 0.001);
        assertEquals(0.0, taxService.calculateTax(100.0, -5), 0.001);
    }
}