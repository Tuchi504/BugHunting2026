package com.bughunting.level2;

import java.util.HashMap;
import java.util.Map;

/**
 * SOLUCIÓN - Ejercicio 2.1 (Nivel 2 - 100 pts)
 *
 * BUG ORIGINAL:
 * `int points = customerPoints.get(customerId);`
 * Si el cliente no existe en el mapa (o `customerId` es `null`), `Map.get(...)` devuelve `null`.
 * Al asignarlo a la variable primitiva `int points`, la JVM intenta hacer auto-unboxing
 * invocando `.intValue()` sobre `null`, lanzando un `NullPointerException` fulminante.
 *
 * CORRECCIÓN:
 * Validar si el mapa contiene la clave o usar `customerPoints.getOrDefault(customerId, 0)`
 * (o verificar `Integer pointsWrapper != null`). Si no está registrado, debe retornar 0% de descuento.
 */
public class CustomerLoyaltyService {

    private final Map<String, Integer> customerPoints = new HashMap<>();

    public CustomerLoyaltyService() {
        customerPoints.put("CUST_101", 1200);
        customerPoints.put("CUST_102", 450);
        customerPoints.put("CUST_103", 80);
    }

    public int getLoyaltyDiscountPercentage(String customerId) {
        if (customerId == null || !customerPoints.containsKey(customerId)) {
            return 0; // Clientes no registrados reciben 0% por defecto
        }

        int points = customerPoints.get(customerId);

        if (points > 1000) {
            return 15;
        } else if (points >= 500) {
            return 10;
        } else {
            return 5;
        }
    }

    public void registerCustomer(String customerId, int initialPoints) {
        customerPoints.put(customerId, initialPoints);
    }
}
