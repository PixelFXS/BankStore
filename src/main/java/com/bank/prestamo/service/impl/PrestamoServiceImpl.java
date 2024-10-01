package com.bank.prestamo.service.impl;

import com.bank.prestamo.ResponseDTO.Pago;
import com.bank.prestamo.entity.Prestamo;
import com.bank.prestamo.repository.PrestamoRepository;
import com.bank.prestamo.service.IPrestamoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Slf4j
@Service
public class PrestamoServiceImpl implements IPrestamoService {
    @Autowired
    PrestamoRepository prestamoRepository;

    @Autowired
    TasasServiceImpl tasasService;

    @Override
    public List<Pago> calcularPagos(LocalDate fechaActual, String cliente, Double tasaIva, int diasAnoComercial) {
        List<Prestamo> prestamos = prestamoRepository.findByCliente(cliente);
        if (prestamos.isEmpty()) {
            throw new RuntimeException("No se encontraron préstamos para el cliente: " + cliente);
        }
        List<Pago> pagos = new ArrayList<>();
        for (Prestamo prestamo : prestamos) {
            LocalDate fechaPrestamo = LocalDate.parse(prestamo.getFecha());
            Long plazo = Math.abs(ChronoUnit.DAYS.between(fechaActual, fechaPrestamo));

            Double tasa = tasasService.tasaInteres(plazo);

            Double interes = round((prestamo.getMonto() * plazo * tasa) / diasAnoComercial, 2);
            Double iva = round(interes * (tasaIva / 100), 2);
            Double pagoTotal = round(prestamo.getMonto() + interes + iva, 2);
            Pago data = new Pago(prestamo.getCliente(), plazo, tasa, prestamo.getMonto(), interes, iva, pagoTotal);
            pagos.add(data);
        }
        pagos.sort(Comparator.comparingLong(Pago::getPlazo).reversed());
        return pagos;
    }

    @Override
    public List<Prestamo> readAll() {
        return prestamoRepository.findAll();
    }

    @Override
    public List<Prestamo> findClient(String cliente) {
        return prestamoRepository.findByCliente(cliente);
    }



    private Double round(Double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}