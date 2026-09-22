package com.bughunting.level1;

/**
 * Servicio encargado del cálculo de impuestos fiscales sobre las ventas.
 */
public class TaxCalculationService {

    /**
     * Calcula el monto de impuesto a pagar según la tasa porcentual especificada.
     * Ejemplo: Un subtotal de $200.0 con una tasa de 16% debe generar un impuesto de $32.0.
     *
     * @param subtotal Monto base de la venta.
     * @param taxPercentage Tasa de impuesto en porcentaje entero (ej. 16 para 16%).
     * @return Monto exacto del impuesto calculado.
     */
    public double calculateTax(double subtotal, int taxPercentage) {
        if (subtotal <= 0 || taxPercentage <= 0) {
            return 0.0;
        }

        // Convertimos el porcentaje a factor decimal dividiendo entre 100
        double factor = taxPercentage / 100;

        return subtotal * factor;
    }
}