package cl.duocuc.bancoxyz.cuentasservice.controller;

import cl.duocuc.bancoxyz.cuentasservice.dto.CuentaDto;
import cl.duocuc.bancoxyz.cuentasservice.dto.TasaInteresDto;
import cl.duocuc.bancoxyz.cuentasservice.model.Cuenta;
import cl.duocuc.bancoxyz.cuentasservice.repository.CuentaRepository;
import cl.duocuc.bancoxyz.cuentasservice.service.TasaInteresExternaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaRepository cuentaRepository;
    private final TasaInteresExternaClient tasaInteresExternaClient;

    public CuentaController(CuentaRepository cuentaRepository, TasaInteresExternaClient tasaInteresExternaClient) {
        this.cuentaRepository = cuentaRepository;
        this.tasaInteresExternaClient = tasaInteresExternaClient;
    }

    @GetMapping("/{cuentaId}")
    public ResponseEntity<CuentaDto> obtenerCuenta(@PathVariable Long cuentaId) {
        return cuentaRepository.findByCuentaId(cuentaId)
                .map(this::aCuentaDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{cuentaId}/tasa-interes")
    public ResponseEntity<TasaInteresDto> obtenerTasaInteres(@PathVariable Long cuentaId) {
        return ResponseEntity.ok(tasaInteresExternaClient.obtenerTasaVigente(cuentaId));
    }

    private CuentaDto aCuentaDto(Cuenta cuenta) {
        return new CuentaDto(cuenta.getCuentaId(), cuenta.getTitular(), cuenta.getEdad(),
                cuenta.getTipoCuenta(), cuenta.getSaldo(), cuenta.getTasaInteres());
    }
}
