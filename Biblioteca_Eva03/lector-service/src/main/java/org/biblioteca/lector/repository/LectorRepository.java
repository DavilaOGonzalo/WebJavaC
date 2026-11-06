package org.biblioteca.lector.repository;

import org.biblioteca.lector.domain.Lector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectorRepository extends JpaRepository<Lector, Long> {
}
