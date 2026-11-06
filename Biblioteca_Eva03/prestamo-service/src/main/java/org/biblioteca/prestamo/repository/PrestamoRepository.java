package org.biblioteca.prestamo.repository;

import org.biblioteca.prestamo.domain.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}
