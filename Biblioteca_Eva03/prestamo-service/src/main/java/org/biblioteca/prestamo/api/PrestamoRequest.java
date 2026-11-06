package org.biblioteca.prestamo.api;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record PrestamoRequest(
        @NotNull @Positive Long lectorId,
        @NotNull @Positive Long libroId,
        @NotNull LocalDate fechaPrestamo,
        @NotNull LocalDate fechaLimite
) {
    @AssertTrue(message = "la fecha límite debe ser igual o posterior a la fecha de préstamo")
    public boolean isRangoFechasValido() {
        return fechaPrestamo == null || fechaLimite == null || !fechaLimite.isBefore(fechaPrestamo);
    }
}
