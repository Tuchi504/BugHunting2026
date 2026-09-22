package com.bughunting.level2;

import java.util.HashMap;
import java.util.Map;

/**
 * Servicio para consultar el nivel de lealtad y beneficios de clientes en ShopFlow.
 */
public class CustomerLoyaltyService {

    private final Map<String, Integer> customerPoints = new HashMap<>();

    public CustomerLoyaltyService() {
        // Clientes iniciales con puntos acumulados
        customerPoints.put("CUST_101", 1200);
        customerPoints.put("CUST_102", 450);
        customerPoints.put("CUST_103", 80);
    }

    /**
     * Calcula el porcentaje de descuento que le corresponde al cliente según sus puntos.
     * Puntos > 1000: 15%
     * Puntos entre 500 y 1000: 10%
     * Puntos menores a 500: 5%
     * Si el cliente es nuevo o no tiene registro, le corresponde 0% de descuento.
     *
     * @param customerId Identificador único del cliente.
     * @return Porcentaje de descuento (0, 5, 10 o 15).
     */
    public int getLoyaltyDiscountPercentage(String customerId) {
        // Como los clientes que llegan a checkout están autenticados,
        // extraemos directamente su puntaje acumulado del sistema
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