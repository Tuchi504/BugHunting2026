# Guía de calificaciones - Competencia Bug Hunting AEISC 2026

**Duración aproximada:** 60 minutos  
**Puntaje total máximo:** 750 puntos  
**Evaluación:** Automatizada offline con JUnit 5 y Java 17 o 21  

---

## 📊 Tabla Resumen de Puntuación y Retos

| Nivel | Ejercicio | Clase Java | Tipo de Bug | Puntos |
| :--- | :--- | :--- | :--- | :---: |
| **Nivel 1 (Fácil)** | 1.1 | `InventoryAuditService` | Off-by-one en límite de arreglo (`length - 1`) | 50 pts |
| | 1.2 | `DiscountPolicyService` | Precedencia de operadores (`&&` sobre `\|\|`) | 50 pts |
| | 1.3 | `TaxCalculationService` | Truncamiento por división entera primitiva (`int / int`) | 50 pts |
| **Nivel 2 (Medio)** | 2.1 | `CustomerLoyaltyService` | `NullPointerException` sutil por unboxing de clave ausente | 100 pts |
| | 2.2 | `CartItemManager` | `IndexOutOfBoundsException` al consultar carrito vacío | 100 pts |
| | 2.3 | `PaymentGatewayService` | Excepción silenciada/tragada que aprueba montos inválidos | 100 pts |
| **Nivel 3 (Desafiante)** | 3.1 | `OrderSearchService` | Ineficiencia algorítmica $O(N^2)$ (Timeout > 1s en 25k items) | 150 pts |
| | 3.2 | `ProductRatingAggregator` | Casos límite: división por cero (`NaN`), nulos, vacíos | 150 pts |
| **TOTAL** | | | | **750 pts** |

---

## 🔍 Desglose Técnico Didáctico por Ejercicio

### Ejercicio 1.1: `InventoryAuditService` (50 pts)
* **Archivo:** `com.bughunting.level1.InventoryAuditService.java`
* **Bug Original:** `for (int i = 0; i < stockLevels.length - 1; i++)`
* **Trampa/Distracción:** El comentario indica que se recorre el arreglo cuidando de no exceder los límites, pero se detiene en el penúltimo elemento. En arreglos de longitud 1 el bucle nunca se ejecuta, y si el producto con stock bajo está al final, es ignorado.
* **Solución:** Cambiar la condición a `i < stockLevels.length`.

---

### Ejercicio 1.2: `DiscountPolicyService` (50 pts)
* **Archivo:** `com.bughunting.level1.DiscountPolicyService.java`
* **Bug Original:** `return isVip || subtotal >= 100.0 && !isCouponExpired;`
* **Trampa/Distracción:** Como `&&` tiene mayor precedencia que `||`, la JVM agrupa `isVip || (subtotal >= 100.0 && !isCouponExpired)`. Por ende, un cliente VIP obtiene descuento **incluso si el cupón expiró hace meses**.
* **Solución:** Agrupar con paréntesis: `(isVip || subtotal >= 100.0) && !isCouponExpired`.

---

### Ejercicio 1.3: `TaxCalculationService` (50 pts)
* **Archivo:** `com.bughunting.level1.TaxCalculationService.java`
* **Bug Original:** `double factor = taxPercentage / 100;`
* **Trampa/Distracción:** Ambos operandos son enteros (`int`). Para cualquier porcentaje del 1% al 99% (ej. 16%), la división entera da `0`, por lo que el impuesto siempre se calcula como `0.0`.
* **Solución:** Forzar aritmética de punto flotante usando `100.0` o `(double) taxPercentage / 100.0`.

---

### Ejercicio 2.1: `CustomerLoyaltyService` (100 pts)
* **Archivo:** `com.bughunting.level2.CustomerLoyaltyService.java`
* **Bug Original:** `int points = customerPoints.get(customerId);`
* **Trampa/Distracción:** El comentario asume que todo cliente en checkout ya existe en la base de datos. Si un cliente nuevo o con ID `null` intenta comprar, `customerPoints.get(...)` retorna `null`. Al intentar convertir `null` a tipo primitivo `int`, la JVM lanza un `NullPointerException` fatal.
* **Solución:** Validar existencia previa: `if (customerId == null || !customerPoints.containsKey(customerId)) return 0;` o usar `customerPoints.getOrDefault(customerId, 0)`.

---

### Ejercicio 2.2: `CartItemManager` (100 pts)
* **Archivo:** `com.bughunting.level2.CartItemManager.java`
* **Bug Original:** `cartItems.get(cartItems.size() - 1);` y `cartItems.remove(cartItems.size() - 1);`
* **Trampa/Distracción:** Asume que la lista nunca está vacía. Si el carrito tiene 0 elementos, `size() - 1` evalúa a `-1`, lanzando `IndexOutOfBoundsException`.
* **Solución:** Validar con `if (cartItems.isEmpty()) return null;` antes de acceder o remover.

---

### Ejercicio 2.3: `PaymentGatewayService` (100 pts)
* **Archivo:** `com.bughunting.level2.PaymentGatewayService.java`
* **Bug Original:** `catch (IllegalArgumentException ex) { return true; }`
* **Trampa/Distracción:** El comentario engaña diciendo que ante fallos en los parámetros se "aprueba por cortesía promocional". Esto provoca que un monto negativo (ej. `-$50.0`) o `$0.0` sea aprobado como cobro exitoso.
* **Solución:** Retornar `false` si el monto es menor o igual a cero y nunca aprobar montos inválidos ni alterar el balance de la cuenta.

---

### Ejercicio 3.1: `OrderSearchService` (150 pts)
* **Archivo:** `com.bughunting.level3.OrderSearchService.java`
* **Bug Original:** Búsqueda cuadrática con bucles anidados `for` y `duplicates.contains(...)` ($O(N^2)$ a $O(N^3)$).
* **Comportamiento:** Pasa pruebas locales de 5 elementos en milisegundos. Sin embargo, en la prueba oculta con 25,000 órdenes, genera más de 300,000,000 comparaciones y excede el límite de 1 segundo impuesto por `@Timeout(value = 1, unit = TimeUnit.SECONDS)`.
* **Solución:** Usar una tabla hash:
  ```java
  Set<String> seen = new HashSet<>();
  Set<String> duplicates = new LinkedHashSet<>();
  for (String code : orderCodes) {
      if (code != null && !seen.add(code)) {
          duplicates.add(code);
      }
  }
  return new ArrayList<>(duplicates);
  ```
  Reduce la complejidad a $O(N)$ lineal, ejecutándose en menos de 25 milisegundos.

---

### Ejercicio 3.2: `ProductRatingAggregator` (150 pts)
* **Archivo:** `com.bughunting.level3.ProductRatingAggregator.java`
* **Bugs Originales:**
  1. No valida listas nulas (`NullPointerException`).
  2. Lista vacía produce `0.0 / 0` arrojando `Double.NaN`.
  3. Suma total de pesos igual a cero produce división por cero (`NaN`).
  4. Listas de diferente longitud lanzan `IndexOutOfBoundsException`.
  5. Calificaciones nulas en la lista lanzan `NullPointerException` en unboxing.
* **Solución:**
  1. Validar `ratings == null || weights == null || ratings.isEmpty() || weights.isEmpty()`.
  2. Validar `ratings.size() != weights.size()`.
  3. Filtrar valores nulos antes de sumar.
  4. Si `totalWeight <= 0`, retornar `0.0`.

---

## Instrucciones para calificar

Correr las pruebas unitarias de la carpeta test/java y verificar que las pruebas pasen.
Si no todas las pruebas pasan, el puntaje obtenido debe ser proporcional al número de pruebas que pasen.