package com.bank.prestamo.repository;

import com.bank.prestamo.entity.Tasas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TasasRepository extends JpaRepository<Tasas, Long> {
    @Query(value = "SELECT t.Id, t.plazo_minimo, t.plazo_maximo, t.tasa_interes FROM Tasas t WHERE :plazo <= t.plazo_maximo", nativeQuery = true)
    List<Tasas> findTasasByPlazoMaximo(@Param("plazo") Long plazo);
}