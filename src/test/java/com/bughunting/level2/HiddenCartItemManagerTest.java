package com.bughunting.level2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Verificación de resolución del ejercicio.
 */
public class HiddenCartItemManagerTest {

    @Test
    @DisplayName("Consulta el último producto agregado con artículos presentes")
    void testGetLastAddedItemNormal() {
        CartItemManager cart = new CartItemManager();
        cart.addItem("Mechanical Keyboard");
        cart.addItem("Wireless Mouse");

        String lastItem = cart.getLastAddedItem();

        assertEquals("Wireless Mouse", lastItem, "El último producto debe ser el Wireless Mouse.");
        assertEquals(2, cart.getItemCount(), "El carrito debe conservar 2 artículos.");
    }

    @Test
    @DisplayName("Carrito vacío al invocar getLastAddedItem debe retornar null sin lanzar excepción")
    void testGetLastAddedItemOnEmptyCart() {
        CartItemManager cart = new CartItemManager();

        String item = assertDoesNotThrow(
                cart::getLastAddedItem,
                "Consultar el último artículo en un carrito vacío no debe lanzar IndexOutOfBoundsException.");

        assertNull(item, "El resultado de consultar carrito vacío debe ser null.");
    }

    @Test
    @DisplayName("Carrito vacío al invocar removeLastAddedItem debe retornar null sin lanzar excepción")
    void testRemoveLastAddedItemOnEmptyCart() {
        CartItemManager cart = new CartItemManager();

        String removed = assertDoesNotThrow(
                cart::removeLastAddedItem,
                "Remover el último elemento en carrito vacío no debe lanzar IndexOutOfBoundsException.");

        assertNull(removed, "El resultado de remover de un carrito vacío debe ser null.");
    }

    @Test
    @DisplayName("Carrito vaciado con clearCart debe responder limpiamente con null")
    void testCartAfterClear() {
        CartItemManager cart = new CartItemManager();
        cart.addItem("Headphones");
        cart.clearCart();

        assertNull(cart.getLastAddedItem(), "Tras vaciar el carrito, debe retornar null.");
        assertNull(cart.removeLastAddedItem(), "Tras vaciar el carrito, remover debe retornar null.");
    }
}
