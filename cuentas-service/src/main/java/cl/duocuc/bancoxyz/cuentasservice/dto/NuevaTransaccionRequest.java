package cl.duocuc.bancoxyz.cuentasservice.dto;

import java.math.BigDecimal;

public record NuevaTransaccionRequest(String tipo, BigDecimal monto, String descripcion) {
}
