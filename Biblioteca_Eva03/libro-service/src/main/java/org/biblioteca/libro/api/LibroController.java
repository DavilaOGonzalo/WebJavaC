package org.biblioteca.libro.api;

import jakarta.validation.Valid;
import org.biblioteca.libro.domain.Libro;
import org.biblioteca.libro.service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    private final LibroService service;

    public LibroController(LibroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Libro> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Libro obtener(@PathVariable("id") Long id) { return service.obtener(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Libro crear(@Valid @RequestBody LibroRequest request) { return service.crear(request); }

    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable("id") Long id, @Valid @RequestBody LibroRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable("id") Long id) { service.eliminar(id); }

    @PostMapping("/{id}/reservas")
    public Libro reservar(@PathVariable("id") Long id) { return service.reservar(id); }

    @PostMapping("/{id}/liberaciones")
    public Libro liberar(@PathVariable("id") Long id) { return service.liberar(id); }
}
