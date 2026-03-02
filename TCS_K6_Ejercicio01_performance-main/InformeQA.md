# Prueba de Performance — Endpoint de Login
**Proyecto:** TCS K6 Ejercicio 01
**Endpoint:** `POST https://fakestoreapi.com/auth/login`
**Herramienta:** Grafana K6 v1.5.0
**Fecha:** 2026-03-01
**Resultado:** APROBADO

---

## Objetivo

Validar que el endpoint de autenticación de FakeStoreAPI mantiene tiempos de respuesta aceptables y estabilidad bajo una carga de hasta 100 usuarios concurrentes, utilizando una estrategia de carga escalonada (ramp-up, sustain, ramp-down) durante 5 minutos.

---

## Configuración ejecutada

Se corrió un escenario de carga con 3 etapas:

| Etapa | Duración | VUs |
|---|---|---|
| Ramp-up | 1 minuto | 0 → 100 |
| Sustain | 3 minutos | 100 |
| Ramp-down | 1 minuto | 100 → 0 |

Los usuarios y contraseñas se inyectaron desde un archivo CSV con 5 registros, seleccionados aleatoriamente en cada iteración. Cada VU hace una petición y espera 1 segundo antes de la siguiente.

Los umbrales definidos para que la prueba apruebe fueron:
- Tiempo de respuesta p95 menor a 1500 ms
- Tasa de errores HTTP menor al 3%

---

## Resultados

La prueba completó **17,563 peticiones** a una tasa de **58.31 req/s**. No se registró ningún error HTTP durante toda la ejecución.

### Tiempos de respuesta (`http_req_duration`)

| Estadístico | Valor |
|---|---|
| Mínimo | 333.91 ms |
| Promedio | 369.95 ms |
| Mediana | 365.70 ms |
| p90 | 384.97 ms |
| p95 | 395.60 ms |
| Máximo | 1050 ms |

El p95 quedó en 395.6 ms, lo que representa apenas el 26% del límite permitido. El rango entre el mínimo y el máximo es de ~716 ms, con el valor máximo presentándose de forma puntual y sin afectar el rendimiento general.

### Thresholds

| Criterio | Límite | Resultado | Estado |
|---|---|---|---|
| `p(95) < 1500 ms` | 1500 ms | 395.60 ms | PASS |
| `rate < 0.03` | 3% | 0.00% | PASS |

### Checks por iteración

Cada request ejecuta 3 validaciones: código de respuesta, presencia del token y tiempo de respuesta. Las 52,689 verificaciones (17,563 × 3) resultaron exitosas al 100%.

### Red y ejecución

| Métrica | Valor |
|---|---|
| Datos recibidos | 10 MB (34 kB/s) |
| Datos enviados | 2.2 MB (7.2 kB/s) |
| Duración promedio por iteración | 1.37 s |
| Duración máxima por iteración | 2.05 s |

---

## Observaciones

**Código HTTP 201 en lugar de 200**
El endpoint devuelve `201 Created` cuando el login es exitoso, lo cual no es el comportamiento habitual para un endpoint de autenticación. En REST, `201` indica creación de un recurso; el login normalmente debería responder con `200 OK`. El script fue ajustado para validar el `201`, pero vale la pena revisarlo con el equipo de backend para confirmar si es intencional o un detalle del diseño de esta API pública.

**Estabilidad bajo carga sostenida**
Durante los 3 minutos de carga máxima con 100 VUs, el servicio mantuvo tiempos de respuesta consistentes sin ninguna degradación progresiva. El p95 se mantuvo muy por debajo del umbral, lo que indica que el servidor tiene capacidad de sobra para esta carga.

**Credenciales en el CSV**
El archivo `users.csv` incluye 5 pares de credenciales. Todas las iteraciones fueron exitosas, lo que indica que la API acepta a todos los usuarios listados, no solo `mor_2314` que era el confirmado inicialmente en la documentación.

---

## Conclusión

El servicio respondió de forma estable durante toda la prueba de 5 minutos con hasta 100 usuarios concurrentes. No hubo errores, los tiempos se mantuvieron muy por debajo del umbral (p95 = 395.6 ms vs límite de 1500 ms), y todas las respuestas incluyeron un token válido. Con 100 VUs el sistema no mostró signos de presión ni degradación, demostrando una infraestructura robusta para escenarios de alta concurrencia.
