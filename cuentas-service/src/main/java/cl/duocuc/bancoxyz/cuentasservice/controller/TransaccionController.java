package cl.duocuc.bancoxyz.cuentasservice.controller;

import cl.duocuc.bancoxyz.cuentasservice.dto.NuevaTransaccionRequest;
import cl.duocuc.bancoxyz.cuentasservice.dto.TransaccionDto;
import cl.duocuc.bancoxyz.cuentasservice.messaging.TransaccionEvent;
import cl.duocuc.bancoxyz.cuentasservice.messaging.TransaccionEventProducer;
import cl.duocuc.bancoxyz.cuentasservice.model.Transaccion;
import cl.duocuc.bancoxyz.cuentasservice.repository.TransaccionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas/{cuentaId}/transacciones")
public class TransaccionController {

    private final TransaccionRepository transaccionRepository;
    private final TransaccionEventProducer transaccionEventProducer;

    public TransaccionController(TransaccionRepository transaccionRepository, TransaccionEventProducer transaccionEventProducer) {
        this.transaccionRepository = transaccionRepository;
        this.transaccionEventProducer = transaccionEventProducer;
    }

    @GetMapping
    public ResponseEntity<List<TransaccionDto>> listar(@PathVariable Long cuentaId) {
        List<TransaccionDto> transacciones = transaccionRepository.findByCuentaIdOrderByFechaDesc(cuentaId).stream()
                .map(this::aDto)
                .toList();

        return ResponseEntity.ok(transacciones);
    }

    @PostMapping
    public ResponseEntity<TransaccionDto> registrar(@PathVariable Long cuentaId, @RequestBody NuevaTransaccionRequest request) {
        Transaccion transaccion = new Transaccion();
        transaccion.setCuentaId(cuentaId);
        transaccion.setFecha(LocalDate.now());
        transaccion.setTipo(request.tipo());
        transaccion.setMonto(request.monto());
        transaccion.setDescripcion(request.descripcion());

        Transaccion guardada = transaccionRepository.save(transaccion);

        TransaccionEvent evento = new TransaccionEvent(guardada.getId(), guardada.getCuentaId(),
                guardada.getFecha(), guardada.getTipo(), guardada.getMonto(), guardada.getDescripcion());
        transaccionEventProducer.publicar(evento);

        return ResponseEntity.ok(aDto(guardada));
    }

    private TransaccionDto aDto(Transaccion transaccion) {
        return new TransaccionDto(transaccion.getId(), transaccion.getCuentaId(), transaccion.getFecha(),
                transaccion.getTipo(), transaccion.getMonto(), transaccion.getDescripcion());
    }
}
