package org.biblioteca.libro.repository;

import org.biblioteca.libro.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
