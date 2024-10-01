package com.bank.prestamo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @Column(name = "Id")
    private Long Id;

    @Column(name = "Cliente")
    private String cliente;

    @Column(name = "Fecha")
    private String fecha;

    @Column(name = "Monto")
    private Double monto;

    @Column(name = "Estado")
    private String estado;


}
