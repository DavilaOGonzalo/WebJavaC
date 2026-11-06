package org.biblioteca.prestamo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long lectorId;

    @Column(nullable = false)
    private Long libroId;

    @Column(nullable = false)
    private LocalDate fechaPrestamo;

    @Column(nullable = false)
    private LocalDate fechaLimite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoPrestamo estado;

    protected Prestamo() {
    }

    public Prestamo(Long lectorId, Long libroId, LocalDate fechaPrestamo, LocalDate fechaLimite) {
        this.lectorId = lectorId;
        this.libroId = libroId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimite = fechaLimite;
        this.estado = EstadoPrestamo.PRESTADO;
    }

    public void devolver() {
        this.estado = EstadoPrestamo.DEVUELTO;
    }

    public Long getId() { return id; }
    public Long getLectorId() { return lectorId; }
    public Long getLibroId() { return libroId; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaLimite() { return fechaLimite; }
    public EstadoPrestamo getEstado() { return estado; }
}
