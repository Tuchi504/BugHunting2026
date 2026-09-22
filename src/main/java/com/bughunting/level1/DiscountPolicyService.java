package com.bughunting.level1;

/**
 * Servicio para validar la aplicación de descuentos promocionales en el checkout.
 */
public class DiscountPolicyService {

    /**
     * Determina si una orden es elegible para aplicar un cupón promocional.
     * REGLA DEL NEGOCIO:
     * Un cliente es elegible si cumple al menos una de estas condiciones:
     * (1) Es cliente VIP, O (2) El subtotal de la compra es mayor o igual a $100.0.
     * ADEMÁS, el cupón bajo NINGUNA circunstancia puede estar vencido (isCouponExpired debe ser false).
     *
     * @param isVip Indica si el comprador tiene membresía VIP.
     * @param subtotal Monto total de los artículos antes de descuentos.
     * @param isCouponExpired Indica si la fecha de validez del cupón ya expiró.
     * @return true si califica para el descuento, false en caso contrario.
     */
    public boolean isEligibleForDiscount(boolean isVip, double subtotal, boolean isCouponExpired) {
        // Un cliente califica si es VIP o supera los 100 dólares y el cupón está vigente
        return isVip || subtotal >= 100.0 && !isCouponExpired;
    }
}
