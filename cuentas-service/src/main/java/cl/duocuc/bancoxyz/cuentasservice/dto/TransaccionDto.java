package cl.duocuc.bancoxyz.cuentasservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransaccionDto(
        Long id,
        Long cuentaId,
        LocalDate fecha,
        String tipo,
        BigDecimal monto,
        String descripcion
) {
}
