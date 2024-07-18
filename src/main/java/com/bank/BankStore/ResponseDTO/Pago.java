package com.bank.BankStore.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pago {
    private String cliente;
    private Long plazo;
    private Double tasaInteres;
    private Double monto;
    private Double interes;
    private Double iva;
    private Double pagoTotal;

}
