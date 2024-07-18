package com.bank.BankStore.ResponseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DatoEntrada {
    private LocalDate fechaActual;
    private String cliente;
    private Double tasaIva;
    private int diasAnoComercial;
}
