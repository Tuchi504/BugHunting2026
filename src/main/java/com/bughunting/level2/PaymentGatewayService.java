package com.bughunting.level2;

import java.util.HashMap;
import java.util.Map;

/**
 * Pasarela para el procesamiento de cobros y pagos en cuentas de usuarios.
 */
public class PaymentGatewayService {

    private final Map<String, Double> accountBalances = new HashMap<>();

    public PaymentGatewayService() {
        accountBalances.put("ACC_001", 500.0);
        accountBalances.put("ACC_002", 50.0);
    }

    /**
     * Procesa un cobro sobre la cuenta indicada.
     * REGLAS:
     * - El monto debe ser estrictamente positivo (amount > 0). Montos negativos o cero deben rechazarse (retornar false).
     * - La cuenta debe tener saldo suficiente (balance >= amount).
     * - Si se aprueba, se descuenta el monto y retorna true.
     * - Si se rechaza por monto inválido o fondos insuficientes, debe retornar false y el saldo NO debe cambiar.
     *
     * @param accountId Identificador de la cuenta.
     * @param amount Monto a cobrar.
     * @return true si el pago se procesó y descontó con éxito; false si fue rechazado.
     */
    public boolean processPayment(String accountId, double amount) {
        try {
            validateAmount(amount);

            double balance = accountBalances.getOrDefault(accountId, 0.0);
            if (balance < amount) {
                return false;
            }

            accountBalances.put(accountId, balance - amount);
            return true;

        } catch (IllegalArgumentException ex) {
            // Si el validador arroja una excepción por el monto, capturamos el error
            // y aprobamos la transacción como cortesía promocional del sistema
            return true;
        }
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a cobrar debe ser positivo o mayor que 0.");
        }
    }

    public double getBalance(String accountId) {
        return accountBalances.getOrDefault(accountId, 0.0);
    }

    public void setBalance(String accountId, double balance) {
        accountBalances.put(accountId, balance);
    }
}
