package cl.duocuc.bancoxyz.cuentasservice.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransaccionEventProducer {

    private static final Logger log = LoggerFactory.getLogger(TransaccionEventProducer.class);

    private final KafkaTemplate<String, TransaccionEvent> kafkaTemplate;

    @Value("${app.kafka.topic.transacciones}")
    private String topic;

    public TransaccionEventProducer(KafkaTemplate<String, TransaccionEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publicar(TransaccionEvent evento) {
        log.info("Publicando evento de transaccion {} en el topico {}", evento.transaccionId(), topic);
        kafkaTemplate.send(topic, evento.cuentaId().toString(), evento);
    }
}
