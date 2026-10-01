package cl.duocuc.bancoxyz.eventosservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransaccionEventRecibido(
        Long transaccionId,
        Long cuentaId,
        LocalDate fecha,
        String tipo,
        BigDecimal monto,
        String descripcion
) {
}
