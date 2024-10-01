package com.bank.prestamo.ResponseDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DatoEntrada {
    private LocalDate fechaActual;
    private String cliente;
    private Double tasaIva;
    private int diasAnoComercial;
}
