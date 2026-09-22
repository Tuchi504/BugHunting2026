# Bug Hunting Competition 2026

¡Bienvenido a la competencia individual de **Bug Hunting**!

Formas parte del equipo de ingeniería de una plataforma de comercio electrónico. El equipo de desarrollo anterior cometió varios errores sutiles (bugs de lógica, excepciones imprevistas y algoritmos lentos) que están afectando el sistema. 

Tu misión como **Bug Hunter** es inspeccionar, depurar y corregir cada una de las clases afectadas para que el sistema sea 100% confiable y eficiente.

---

## Reglas de la Competencia
* **Tiempo Límite:** 60 minutos.
* **Puntaje Total Máximo:** 750 puntos.
* **Evaluación:** Offline mediante suites de pruebas automatizadas con **JUnit 5**.

### Reglas Importantes de Entrega:
1. **NO modifiques las firmas de los métodos ni las clases** (nombres, modificadores de acceso, tipos de retorno y parámetros deben permanecer idénticos).
2. **NO modifiques los archivos de prueba** ubicados en `src/test/java/`.
3. Solo debes editar el código dentro de `src/main/java/com/bughunting/`.
4. Los comentarios en el código fueron escritos por programadores confiados: **¡no te fíes de ellos a ciegas!**

---

## Mapa de Retos y Puntuación

### Nivel 1: Lógica Básica (50 pts c/u | Total: 150 pts)
* **Ejercicio 1.1:** `InventoryAuditService.java` (50 pts)
  * *Misión:* Asegurar que la auditoría de niveles de stock en almacén recorra adecuadamente los límites.
* **Ejercicio 1.2:** `DiscountPolicyService.java` (50 pts)
  * *Misión:* Validar rigurosamente las condiciones para aplicar cupones promocionales.
* **Ejercicio 1.3:** `TaxCalculationService.java` (50 pts)
  * *Misión:* Garantizar la precisión en el cálculo porcentual de impuestos en ventas.

### Nivel 2: Estructuras de Datos y Excepciones (100 pts c/u | Total: 300 pts)
* **Ejercicio 2.1:** `CustomerLoyaltyService.java` (100 pts)
  * *Misión:* Prevenir fallos al consultar niveles de lealtad de clientes nuevos o ausentes en el mapa.
* **Ejercicio 2.2:** `CartItemManager.java` (100 pts)
  * *Misión:* Proteger las operaciones de consulta y remoción del carrito ante listas vacías.
* **Ejercicio 2.3:** `PaymentGatewayService.java` (100 pts)
  * *Misión:* Manejar transacciones y montos inválidos sin comprometer el saldo ni aprobar cobros ilegales.

### Nivel 3: Algoritmos y Casos Límite (150 pts c/u | Total: 300 pts)
* **Ejercicio 3.1:** `OrderSearchService.java` (150 pts)
  * *Misión:* Optimizar el algoritmo de detección de duplicados para que procese lotes masivos de 25,000 pedidos en **menos de 1 segundo**.
* **Ejercicio 3.2:** `ProductRatingAggregator.java` (150 pts)
  * *Misión:* Blindar el cálculo de calificaciones promedio contra entradas nulas, vacías, división por cero y listas asimétricas.

---

## ¿Cómo Probar tu Código?

### Desde tu IDE Favorito (IntelliJ IDEA / VS Code / Eclipse / NetBeans)
Abre esta carpeta como un proyecto **Maven** existente (usará el archivo `pom.xml`):
1. Navega a `src/test/java/com/bughunting/`.
2. Haz clic derecho en la carpeta de tests o en cualquier archivo de prueba y selecciona **Run Tests** (botón verde de Play).