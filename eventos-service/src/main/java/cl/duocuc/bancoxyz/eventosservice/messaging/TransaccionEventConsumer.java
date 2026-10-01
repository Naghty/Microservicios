package cl.duocuc.bancoxyz.eventosservice.messaging;

import cl.duocuc.bancoxyz.eventosservice.model.EventoAlmacenado;
import cl.duocuc.bancoxyz.eventosservice.model.TransaccionEventRecibido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransaccionEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(TransaccionEventConsumer.class);

    private final List<EventoAlmacenado> historial = new CopyOnWriteArrayList<>();

    @KafkaListener(topics = "${app.kafka.topic.transacciones}", groupId = "${app.kafka.group-id}")
    public void escuchar(TransaccionEventRecibido evento) {
        log.info("Evento de transaccion recibido: cuenta {} tipo {} monto {}",
                evento.cuentaId(), evento.tipo(), evento.monto());

        historial.add(new EventoAlmacenado(evento, LocalDateTime.now()));
    }

    public List<EventoAlmacenado> obtenerHistorial() {
        return Collections.unmodifiableList(historial);
    }
}
