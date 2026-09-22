package com.bughunting.level3;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio encargado de la auditoría y búsqueda de pedidos en catálogos masivos de ShopFlow.
 */
public class OrderSearchService {

    /**
     * Identifica los códigos de seguimiento (tracking codes) que aparecen duplicados en el lote de órdenes.
     * La lista devuelta no debe contener duplicados entre sí.
     *
     * @param orderCodes Lista con todos los códigos de tracking recibidos en el lote.
     * @return Lista de códigos que aparecen 2 o más veces. Si no hay duplicados o la lista es nula/vacía, retorna lista vacía.
     */
    public List<String> findDuplicateOrderCodes(List<String> orderCodes) {
        List<String> duplicates = new ArrayList<>();
        if (orderCodes == null || orderCodes.isEmpty()) {
            return duplicates;
        }

        // Comprobamos duplicados comparando cada elemento con todos los demás de la lista
        for (int i = 0; i < orderCodes.size(); i++) {
            for (int j = i + 1; j < orderCodes.size(); j++) {
                if (orderCodes.get(i) != null && orderCodes.get(i).equals(orderCodes.get(j))) {
                    if (!duplicates.contains(orderCodes.get(i))) {
                        duplicates.add(orderCodes.get(i));
                    }
                }
            }
        }

        return duplicates;
    }
}