package com.bughunting.level2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Verificación de resolución del ejercicio.
 */
public class HiddenPaymentGatewayServiceTest {

    private final PaymentGatewayService paymentGateway = new PaymentGatewayService();

    @Test
    @DisplayName("Procesa cobro exitoso con fondos suficientes")
    void testProcessPaymentSuccess() {
        // ACC_001 tiene $500.0 de saldo inicial
        boolean success = paymentGateway.processPayment("ACC_001", 100.0);

        assertTrue(success, "El pago de $100.0 debe ser aprobado.");
        assertEquals(400.0, paymentGateway.getBalance("ACC_001"), 0.001, "El saldo remanente debe ser $400.0.");
    }

    @Test
    @DisplayName("Monto negativo debe ser rechazado (retornar false) y NO alterar saldo")
    void testNegativeAmountMustBeRejected() {
        double initialBalance = paymentGateway.getBalance("ACC_001"); // 500.0

        boolean success = paymentGateway.processPayment("ACC_001", -50.0);

        assertFalse(success, "Un cobro con monto negativo jamás debe aprobarse.");
        assertEquals(initialBalance, paymentGateway.getBalance("ACC_001"), 0.001, "El saldo no debe ser modificado.");
    }

    @Test
    @DisplayName("Monto cero debe ser rechazado (retornar false)")
    void testZeroAmountMustBeRejected() {
        double initialBalance = paymentGateway.getBalance("ACC_001");

        boolean success = paymentGateway.processPayment("ACC_001", 0.0);

        assertFalse(success, "Un cobro con monto de $0.0 no debe aprobarse como transacción válida.");
        assertEquals(initialBalance, paymentGateway.getBalance("ACC_001"), 0.001);
    }

    @Test
    @DisplayName("Fondos insuficientes debe retornar false sin modificar el saldo")
    void testInsufficientFundsMustBeRejected() {
        // ACC_002 tiene $50.0 de saldo
        boolean success = paymentGateway.processPayment("ACC_002", 90.0);

        assertFalse(success, "Fondos insuficientes debe devolver false.");
        assertEquals(50.0, paymentGateway.getBalance("ACC_002"), 0.001, "El saldo debe permanecer intacto.");
    }
}