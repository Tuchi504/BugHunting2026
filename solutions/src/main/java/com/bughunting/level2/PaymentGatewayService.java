package com.bughunting.level2;

import java.util.HashMap;
import java.util.Map;

/**
 * SOLUCIÓN - Ejercicio 2.3 (Nivel 2 - 100 pts)
 *
 * BUG ORIGINAL:
 * En el bloque `catch (IllegalArgumentException ex)`, el método retornaba `true`.
 * Esto "tragaba" la excepción de validación y reportaba como aprobado un cobro con monto
 * inválido (menor o igual a cero), quebrando la integridad transaccional.
 *
 * CORRECCIÓN:
 * Manejar adecuadamente el flujo de validación: si la validación falla (o arroja
 * IllegalArgumentException), debe rechazarse la operación retornando `false` y dejando el saldo intacto.
 */
public class PaymentGatewayService {

    private final Map<String, Double> accountBalances = new HashMap<>();

    public PaymentGatewayService() {
        accountBalances.put("ACC_001", 500.0);
        accountBalances.put("ACC_002", 50.0);
    }

    public boolean processPayment(String accountId, double amount) {
        // Corrección: Validamos el monto y rechazamos limpiamente si es <= 0
        if (amount <= 0) {
            return false;
        }

        double balance = accountBalances.getOrDefault(accountId, 0.0);
        if (balance < amount) {
            return false;
        }

        accountBalances.put(accountId, balance - amount);
        return true;
    }

    public double getBalance(String accountId) {
        return accountBalances.getOrDefault(accountId, 0.0);
    }

    public void setBalance(String accountId, double balance) {
        accountBalances.put(accountId, balance);
    }
}
