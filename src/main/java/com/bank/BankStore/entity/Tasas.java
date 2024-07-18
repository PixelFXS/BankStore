package com.bank.BankStore.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tasas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tasas {
    @Id
    @Column(name = "Id")
    private Long id;

    @Column(name = "plazo_minimo")
    private Long plazo_minimo;

    @Column(name = "plazo_maximo")
    private Long plazo_maximo;

    @Column(name = "tasa_interes")
    private Double tasa_interes;
}
