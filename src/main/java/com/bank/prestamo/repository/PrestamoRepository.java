package com.bank.prestamo.repository;

import com.bank.prestamo.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByCliente(String cliente);
    Optional<Prestamo> findFirstByCliente(String cliente);
}
