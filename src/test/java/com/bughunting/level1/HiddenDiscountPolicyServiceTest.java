package com.bughunting.level1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Verificación de resolución de ejercicio.
 */
public class HiddenDiscountPolicyServiceTest {

    private final DiscountPolicyService discountService = new DiscountPolicyService();

    @Test
    @DisplayName("Cliente VIP con cupón expirado, no debe recibir descuento.")
    void testVipWithExpiredCouponMustBeDenied() {
        boolean isVip = true;
        double subtotal = 50.0;
        boolean isCouponExpired = true;

        boolean eligible = discountService.isEligibleForDiscount(isVip, subtotal, isCouponExpired);

        assertFalse(eligible, "Ningún cupón expirado debe aplicarse, ni siquiera para clientes VIP.");
    }

    @Test
    @DisplayName("Compra mayor a 100 con cupón expirado no debe recibir descuento.")
    void testHighAmountWithExpiredCouponMustBeDenied() {
        boolean isVip = false;
        double subtotal = 250.0;
        boolean isCouponExpired = true;

        boolean eligible = discountService.isEligibleForDiscount(isVip, subtotal, isCouponExpired);

        assertFalse(eligible, "Una compra de alto valor con cupón expirado debe rechazarse.");
    }

    @Test
    @DisplayName("Cliente no VIP con subtotal >= 100 y cupón vigente debe ser aprobado.")
    void testNonVipWithHighAmountAndValidCoupon() {
        boolean isVip = false;
        double subtotal = 100.0;
        boolean isCouponExpired = false;

        boolean eligible = discountService.isEligibleForDiscount(isVip, subtotal, isCouponExpired);

        assertTrue(eligible, "Cliente regular que gasta más de $100 con cupón válido debe calificar.");
    }

    @Test
    @DisplayName("Cliente no VIP con subtotal < 100 y cupón vigente debe ser rechazado.")
    void testNonVipWithLowAmountAndValidCoupon() {
        boolean isVip = false;
        double subtotal = 99.99;
        boolean isCouponExpired = false;

        boolean eligible = discountService.isEligibleForDiscount(isVip, subtotal, isCouponExpired);

        assertFalse(eligible, "Cliente regular con menos de $100 no califica a pesar de tener cupón válido.");
    }

    @Test
    @DisplayName("Cliente VIP con cupón vigente aplica a descuento.")
    void testVipWithValidCoupon() {
        boolean isVip = true;
        double subtotal = 150.0;
        boolean isCouponExpired = false;

        boolean eligible = discountService.isEligibleForDiscount(isVip, subtotal, isCouponExpired);

        assertTrue(eligible, "Un cliente VIP con cupón vigente debe ser elegible.");
    }
}
