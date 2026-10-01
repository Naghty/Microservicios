package cl.duocuc.bancoxyz.cuentasservice.messaging;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransaccionEvent(
        Long transaccionId,
        Long cuentaId,
        LocalDate fecha,
        String tipo,
        BigDecimal monto,
        String descripcion
) {
}
