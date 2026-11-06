package org.biblioteca.libro.api;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LibroRequest(
        @NotBlank @Pattern(regexp = "[0-9Xx-]{10,17}", message = "debe tener formato ISBN-10 o ISBN-13")
        String isbn,
        @NotBlank @Size(max = 200) String titulo,
        @NotBlank @Size(max = 150) String autor,
        @NotNull @Min(0) Integer stock
) {
}
