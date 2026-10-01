# Evidencia de ejecucion

Capturas sugeridas:

1. `docker compose up --build` corriendo, mostrando los 7 contenedores
   levantados sin errores.
2. Panel de Eureka (http://localhost:8761) con cuentas-service y
   eventos-service registrados.
3. Config server respondiendo la configuracion de cuentas-service
   (http://localhost:8888/cuentas-service/default).
4. Peticion a Keycloak obteniendo un access_token (Postman o curl).
5. Un GET exitoso a /api/cuentas/101 con el token (200 OK).
6. El mismo GET sin token o con token invalido (401/403).
7. Varias llamadas seguidas a /api/cuentas/101/tasa-interes mostrando
   que a veces responde con origen "servicio-externo" y otras con
   "valor-por-defecto-fallback" (circuit breaker/fallback en accion).
8. Un POST a /api/cuentas/101/transacciones y despues un GET a
   /api/eventos/historial mostrando que el evento llego por Kafka.
