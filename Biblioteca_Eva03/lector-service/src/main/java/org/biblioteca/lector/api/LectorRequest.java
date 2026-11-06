package org.biblioteca.lector.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LectorRequest(
        @NotBlank @Size(max = 30) String documento,
        @NotBlank @Size(max = 160) String nombres,
        @NotBlank @Size(max = 160) String contacto
) {
}
