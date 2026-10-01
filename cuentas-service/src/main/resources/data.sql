INSERT INTO cuentas (id, cuenta_id, titular, edad, tipo_cuenta, saldo, tasa_interes) VALUES
(1, 101, 'Diana Prince', 40, 'prestamo', 12000.00, 0.018),
(2, 108, 'Jane Smith', 45, 'hipoteca', 9500.00, 0.032),
(3, 111, 'Diana Prince', 30, 'prestamo', 5000.00, 0.021);

INSERT INTO transacciones (id, cuenta_id, fecha, tipo, monto, descripcion) VALUES
(1, 101, '2024-01-07', 'deposito', 2500.00, 'Retiro parcial'),
(2, 101, '2024-02-19', 'compra', 2000.00, 'Retiro parcial'),
(3, 108, '2024-02-15', 'compra', 3000.00, 'Ingreso mensual'),
(4, 111, '2024-02-15', 'retiro', 2500.00, 'Compra en tienda');
