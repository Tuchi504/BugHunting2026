package com.bughunting.level2;

import java.util.ArrayList;
import java.util.List;

/**
 * SOLUCIÓN - Ejercicio 2.2 (Nivel 2 - 100 pts)
 *
 * BUG ORIGINAL:
 * `cartItems.get(cartItems.size() - 1)` y `cartItems.remove(cartItems.size() - 1)`
 * asumen que la lista siempre contiene elementos. Cuando el carrito está vacío (`size() == 0`),
 * se intenta acceder a la posición `-1`, arrojando `IndexOutOfBoundsException`.
 *
 * CORRECCIÓN:
 * Validar previamente con `cartItems.isEmpty()` y retornar `null` de forma segura.
 */
public class CartItemManager {

    private final List<String> cartItems = new ArrayList<>();

    public void addItem(String item) {
        if (item != null && !item.isBlank()) {
            cartItems.add(item);
        }
    }

    public String getLastAddedItem() {
        // Corrección: Verificamos si la lista está vacía antes de acceder al índice
        if (cartItems.isEmpty()) {
            return null;
        }
        return cartItems.get(cartItems.size() - 1);
    }

    public String removeLastAddedItem() {
        // Corrección: Verificamos si la lista está vacía antes de intentar remover
        if (cartItems.isEmpty()) {
            return null;
        }
        return cartItems.remove(cartItems.size() - 1);
    }

    public int getItemCount() {
        return cartItems.size();
    }

    public void clearCart() {
        cartItems.clear();
    }
}
