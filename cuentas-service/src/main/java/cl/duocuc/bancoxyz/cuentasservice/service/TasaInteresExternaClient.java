package cl.duocuc.bancoxyz.cuentasservice.service;

import cl.duocuc.bancoxyz.cuentasservice.dto.TasaInteresDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TasaInteresExternaClient {

    private static final Logger log = LoggerFactory.getLogger(TasaInteresExternaClient.class);

    @CircuitBreaker(name = "tasaInteresService", fallbackMethod = "tasaInteresFallback")
    @Retry(name = "tasaInteresService")
    public TasaInteresDto obtenerTasaVigente(Long cuentaId) {
        log.info("Consultando servicio externo de tasas para la cuenta {}", cuentaId);

        boolean fallaSimulada = ThreadLocalRandom.current().nextInt(100) < 50;

        if (fallaSimulada) {
            throw new RuntimeException("Servicio externo de tasas de interes no disponible");
        }

        BigDecimal tasa = BigDecimal.valueOf(0.015 + ThreadLocalRandom.current().nextDouble(0.0, 0.02));
        return new TasaInteresDto(tasa, "servicio-externo");
    }

    public TasaInteresDto tasaInteresFallback(Long cuentaId, Throwable error) {
        log.warn("Fallback activado para la cuenta {}: {}", cuentaId, error.getMessage());
        return new TasaInteresDto(BigDecimal.valueOf(0.015), "valor-por-defecto-fallback");
    }
}
