package com.bank.prestamo.service;

import com.bank.prestamo.ResponseDTO.Pago;
import com.bank.prestamo.entity.Prestamo;

import java.time.LocalDate;
import java.util.List;

public interface IPrestamoService {
    List<Pago> calcularPagos(LocalDate fechaActual, String cliente, Double tasaIva, int diasAnoComercial);
    List<Prestamo> readAll();
    List<Prestamo> findClient(String client);
}