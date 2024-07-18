package com.bank.BankStore.service;

import com.bank.BankStore.ResponseDTO.Pago;
import com.bank.BankStore.entity.Prestamo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IPrestamoService {
    List<Pago> calcularPagos(LocalDate fechaActual, String cliente, Double tasaIva, int diasAnoComercial);
    List<Prestamo> readAll();
    List<Prestamo> findClient(String client);
}