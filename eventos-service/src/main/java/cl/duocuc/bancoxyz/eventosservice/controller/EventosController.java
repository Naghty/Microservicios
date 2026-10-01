package cl.duocuc.bancoxyz.eventosservice.controller;

import cl.duocuc.bancoxyz.eventosservice.messaging.TransaccionEventConsumer;
import cl.duocuc.bancoxyz.eventosservice.model.EventoAlmacenado;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventosController {

    private final TransaccionEventConsumer transaccionEventConsumer;

    public EventosController(TransaccionEventConsumer transaccionEventConsumer) {
        this.transaccionEventConsumer = transaccionEventConsumer;
    }

    @GetMapping("/historial")
    public ResponseEntity<List<EventoAlmacenado>> historial() {
        return ResponseEntity.ok(transaccionEventConsumer.obtenerHistorial());
    }
}
