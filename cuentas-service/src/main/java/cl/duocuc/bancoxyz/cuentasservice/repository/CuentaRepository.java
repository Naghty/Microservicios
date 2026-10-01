package cl.duocuc.bancoxyz.cuentasservice.repository;

import cl.duocuc.bancoxyz.cuentasservice.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Optional<Cuenta> findByCuentaId(Long cuentaId);
}
