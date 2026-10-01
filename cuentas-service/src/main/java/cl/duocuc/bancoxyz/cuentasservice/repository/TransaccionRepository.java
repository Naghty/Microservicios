package cl.duocuc.bancoxyz.cuentasservice.repository;

import cl.duocuc.bancoxyz.cuentasservice.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    List<Transaccion> findByCuentaIdOrderByFechaDesc(Long cuentaId);
}
