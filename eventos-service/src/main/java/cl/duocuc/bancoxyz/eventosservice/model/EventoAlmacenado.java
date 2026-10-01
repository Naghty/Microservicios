package cl.duocuc.bancoxyz.eventosservice.model;

import java.time.LocalDateTime;

public record EventoAlmacenado(TransaccionEventRecibido evento, LocalDateTime recibidoEn) {
}
