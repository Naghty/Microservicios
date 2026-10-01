package cl.duocuc.bancoxyz.cuentasservice.dto;

import java.math.BigDecimal;

public record CuentaDto(
        Long cuentaId,
        String titular,
        Integer edad,
        String tipoCuenta,
        BigDecimal saldo,
        BigDecimal tasaInteres
) {
}
