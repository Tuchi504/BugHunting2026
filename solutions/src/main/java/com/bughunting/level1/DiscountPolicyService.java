package com.bughunting.level1;

/**
 * SOLUCIÓN - Ejercicio 1.2 (Nivel 1 - 50 pts)
 *
 * BUG ORIGINAL:
 * En Java, el operador `&&` tiene mayor precedencia que `||`.
 * La expresión `isVip || subtotal >= 100.0 && !isCouponExpired` se evaluaba como:
 * `isVip || (subtotal >= 100.0 && !isCouponExpired)`.
 * Esto provocaba que si `isVip` era `true`, el método retornaba `true` ¡incluso si el cupón ya había expirado!
 *
 * CORRECCIÓN:
 * Agrupar con paréntesis explícitos: `(isVip || subtotal >= 100.0) && !isCouponExpired`.
 * De este modo, la condición de cupón no vencido es obligatoria para cualquier cliente.
 */
public class DiscountPolicyService {

    public boolean isEligibleForDiscount(boolean isVip, double subtotal, boolean isCouponExpired) {
        // Corrección: Los paréntesis garantizan que la vigencia del cupón sea condición obligatoria
        return (isVip || subtotal >= 100.0) && !isCouponExpired;
    }
}
