# Banco XYZ - Microservicios (Exp3 S8)

## Objetivo

Microservicios del Banco XYZ con Config Server, Eureka, tolerancia a
fallos, eventos con Kafka, seguridad con OAuth2.0 (Keycloak) y todo
levantado con docker-compose.

## Estructura

```
config-server/     Config Server (puerto 8888)
eureka-server/      Eureka (puerto 8761)
cuentas-service/     API de cuentas/transacciones (puerto 8081)
eventos-service/     Consume los eventos de Kafka (puerto 8082)
keycloak/            Realm para el login OAuth2
evidencia/           Capturas
docker-compose.yaml
```

## Como correrlo

```
docker compose up --build
```

Tarda un rato la primera vez porque compila los 4 servicios. Cuando
termine, revisar:

- Eureka: http://localhost:8761 (deberian salir CUENTAS-SERVICE y EVENTOS-SERVICE)
- Keycloak: http://localhost:8080

## Pedir el token (OAuth2.0)

```
curl -X POST http://localhost:8080/realms/bancoxyz/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=client_credentials&client_id=cuentas-client&client_secret=cuentas-secret"
```

Eso devuelve un access_token que hay que mandar en el header
`Authorization: Bearer <token>` en cada endpoint.

## Endpoints

cuentas-service (8081):
- `GET /api/cuentas/{cuentaId}` (probar con 101, 108 o 111)
- `GET /api/cuentas/{cuentaId}/transacciones`
- `GET /api/cuentas/{cuentaId}/tasa-interes` (aqui actua el circuit breaker)
- `POST /api/cuentas/{cuentaId}/transacciones` con body `{"tipo": "deposito", "monto": 1000, "descripcion": "prueba"}`

eventos-service (8082):
- `GET /api/eventos/historial`

## Evidencia

Capturas de todo lo anterior funcionando en la carpeta `evidencia/`.