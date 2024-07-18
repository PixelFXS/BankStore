package com.bank.BankStore.controller;

import com.bank.BankStore.ResponseDTO.DatoEntrada;
import com.bank.BankStore.ResponseDTO.Pago;
import com.bank.BankStore.entity.Prestamo;
import com.bank.BankStore.service.impl.PrestamoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "PrestamoController", description = "Mapping for PrestamoController")
public class PrestamoController {

    @Autowired
    PrestamoServiceImpl prestamoService;

    @Operation(summary = "Prestamos", description = "Solicitud HTTP para obtener el listado de prestamos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/prestamos")
    public List<Prestamo> readAll(){
        return prestamoService.readAll();
    }

    @Operation(summary = "Prestamo de Cliente", description = "Solicitud HTTP para obtener el prestamo de un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/prestamo/cliente")
    public List<Prestamo> findByClient(@PathParam("cliente") String cliente){
        return prestamoService.findClient(cliente);
    }

    @Operation(summary = "Prestamos Pagos", description = "Solicitud HTTP para obtener el pago por prestamo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Éxito"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/prestamos/pagos")
    public ResponseEntity<List<Pago>> calcularPagos(@RequestBody DatoEntrada datosEntrada) {
        LocalDate fechaActual = datosEntrada.getFechaActual();
        String cliente = datosEntrada.getCliente();
        Double tasaIva = datosEntrada.getTasaIva();
        int diasAnoComercial = datosEntrada.getDiasAnoComercial();
        List<Pago> pagos = prestamoService.calcularPagos(fechaActual, cliente, tasaIva, diasAnoComercial);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }
}