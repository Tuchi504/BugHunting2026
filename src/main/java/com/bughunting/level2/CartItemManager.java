package com.bughunting.level2;

import java.util.ArrayList;
import java.util.List;

/**
 * Administrador de productos dentro del carrito de compras.
 */
public class CartItemManager {

    private final List<String> cartItems = new ArrayList<>();

    /**
     * Agrega un nuevo producto al carrito.
     *
     * @param item Nombre del producto.
     */
    public void addItem(String item) {
        if (item != null && !item.isBlank()) {
            cartItems.add(item);
        }
    }

    /**
     * Obtiene el último producto agregado al carrito sin eliminarlo.
     * Si el carrito no contiene productos, debe retornar null.
     *
     * @return El nombre del último producto agregado, o null si el carrito está vacío.
     */
    public String getLastAddedItem() {
        // Obtenemos el elemento final de la lista utilizando la posición final
        return cartItems.get(cartItems.size() - 1);
    }

    /**
     * Remueve y devuelve el último producto agregado al carrito.
     * Si el carrito está vacío, debe retornar null.
     *
     * @return El producto eliminado, o null si no había elementos.
     */
    public String removeLastAddedItem() {
        // Removemos directamente el elemento superior de la pila del carrito
        return cartItems.remove(cartItems.size() - 1);
    }

    /**
     * Obtiene el conteo total de productos en el carrito.
     * 
     * @return El número de productos en el carrito.
    */
    public int getItemCount() {
        return cartItems.size();
    }

    /**
     * Vacía el carrito de compras.
     */
    public void clearCart() {
        cartItems.clear();
    }
}