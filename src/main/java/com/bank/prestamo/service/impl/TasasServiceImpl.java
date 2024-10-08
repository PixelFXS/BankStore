package com.bank.prestamo.service.impl;

import com.bank.prestamo.entity.Tasas;
import com.bank.prestamo.repository.TasasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class TasasServiceImpl {

    @Autowired
    TasasRepository tasasRepository;

    public Double tasaInteres(Long plazo) {
        List<Tasas> tasasList = tasasRepository.findTasasByPlazoMaximo(plazo);
        Optional<Tasas> tasaOpt = tasasList.stream()
                .filter(t -> t.getPlazo_minimo() <= plazo)
                .findFirst();
        if (tasaOpt.isPresent()) {
            return tasaOpt.get().getTasa_interes();
        } else {
            throw new RuntimeException("No se encontró una tasa de interés válida para el plazo: " + plazo);
        }
    }
}
